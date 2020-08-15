package zqm_speed4j.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zqm_speed4j.ZqmStopWatch;

import java.util.Date;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 定时log
 * 1. 数据
 * 2. 定时器
 * 3. log
 * Created by useheart on 2019-03-17
 */
public class ZqmPeriodicalLog extends ZqmSlf4jLog {

    private static final int DEFAULT_MAX_QUEUE_SIZE = 300000;

    private int m_tagWidth = 60;

    private static Logger log = LoggerFactory.getLogger(ZqmPeriodicalLog.class);

    private LinkedBlockingDeque<ZqmStopWatch> m_queue = new LinkedBlockingDeque<>(DEFAULT_MAX_QUEUE_SIZE);

    private AtomicLong m_rejectedStopWatches = new AtomicLong();

    // 双检锁单例 lazy 创建
    private volatile CollectorThread m_collectorThread; // 定时器 收集器
    private boolean m_stopCollector = false;
    private int m_periodSeconds = 30;
    private double[] m_percentiles = {95};
    private Map<String, ZqmCollectedStatistics> m_stats = new TreeMap<>();

    // 写日志 触发相关定时日志器
    @Override
    public void log(ZqmStopWatch sw) {
        if (!m_queue.offer(sw.freeze())) m_rejectedStopWatches.getAndIncrement();

        // 双检锁
        if (m_collectorThread == null) {
            synchronized (this) {
                if (m_collectorThread == null) {
                    m_collectorThread = new CollectorThread();
                    m_collectorThread.setName("Speed4J PeriodicalLog Collector Thread");
                    m_collectorThread.setDaemon(true);
                    m_collectorThread.start();
                }
            }
        }
    }

    /**
     * 清空StopWatch队列, 构建统计
     */
    private boolean emptyQueue(long finalMoment) {
        ZqmStopWatch sw;
        try {
            while (null != (sw = m_queue.poll(100, TimeUnit.MILLISECONDS))) {
                // Ignore faulty 无法后续进行统计
                if (sw.getTag() == null) continue;
                if (sw.getCreation() > finalMoment) {
                    // 在这一瞬间前的过
                    m_queue.addFirst(sw);
                    return true;
                }
                ZqmCollectedStatistics cs = m_stats.get(sw.getTag());
                if (cs == null) {
                    cs = new ZqmCollectedStatistics();
                    m_stats.put(sw.getTag(), cs);
                }
                //进行统计
                cs.add(sw);

            }
        } catch (InterruptedException e) {
        } // Just return immediately
        return false;
    }

    /**
     * 写日志
     */
    private void doLog(long lastRun, long finalMoment) {
        // Do logging, if requested
        double[] percentilenames = m_percentiles;
        if (m_log != null && m_log.isInfoEnabled()) {
            // 写日志
            StringBuilder percString = new StringBuilder();
            for (int i = 0; i < percentilenames.length; i++) {
                percString.append(String.format("%6sth", saneDoubleToString(percentilenames[i])));
            }
            printf("Statistics from %tc to %tc", new Date(lastRun), new Date(finalMoment));

            printf("%-" + m_tagWidth + "s %8s %8s %8s %8s%s%8s", "Tag", "Avg(ms)", "Min", "Max", "Std Dev", percString, "Count");
            for (Map.Entry<String, ZqmCollectedStatistics> e : m_stats.entrySet()) {
                ZqmCollectedStatistics cs = e.getValue();
                StringBuilder sb = new StringBuilder();

                sb.append(String.format("%-" + m_tagWidth + "s %8.2f %8.2f %8.2f %8.2f", e.getKey(), cs.getAverageMS(), cs.getMin(), cs.getMax(), cs.getStdDev()));

                for (int i = 0; i < percentilenames.length; i++) {
                    sb.append(String.format(" %8.2f", cs.getPercentile(percentilenames[i])));
                }

                sb.append(String.format("%8d", cs.getInvocations()));
                m_log.info(sb.toString());
            }
            m_log.info("");
        }
    }

    private void printf(String pattern, Object... args) {
        if (m_log != null) {
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb);

            try {
                formatter.format(pattern, args);

                m_log.info(sb.toString());
            } finally {
                formatter.close();
            }
        }
    }

    private String saneDoubleToString(double pp) {
        String perTitle = Double.toString(pp);
        if (perTitle.endsWith(".0")) perTitle = perTitle.substring(0, perTitle.length() - 2);
        return perTitle;
    }

    private class CollectorThread extends Thread {
        long m_lastRun = System.currentTimeMillis();
        long m_nextWeakup;

        void nextPeriod() {
            long now = System.currentTimeMillis();
            long periodMillis = m_periodSeconds * 1000L;
            // (now / periodMillis) * periodMillis 取整倍数
            m_nextWeakup = (now / periodMillis) * periodMillis + periodMillis;
        }

        @Override
        public void run() {
            nextPeriod();
            while (!m_stopCollector) {
                try {
                    // 将m_nextWeakup前数据清空 准备写日志
                    if (emptyQueue(m_nextWeakup)) {
                        doLog(m_lastRun, m_nextWeakup);
                        m_lastRun = m_nextWeakup;
                        nextPeriod();
                    }
                } catch (Throwable t) {
                    // 无论发生什么都继续执行
                    log.warn("Problem while doing logging; continuing nevertheless...", t);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }

            // 停止前 随后执行一遍
            long now = System.currentTimeMillis();
            emptyQueue(now);
            doLog(m_lastRun, now);
        }
    }

    public void setPeroid(int periodSeconds) {
        m_periodSeconds = periodSeconds;
        if (m_collectorThread != null) m_collectorThread.nextPeriod();
    }
}
