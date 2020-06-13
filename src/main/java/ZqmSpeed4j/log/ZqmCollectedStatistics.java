package ZqmSpeed4j.log;

import ZqmSpeed4j.ZqmStopWatch;
import ZqmSpeed4j.util.ZqmPercentile;

/**
 * 搜集了所有性能数据统计
 * Created by useheart on 2019-03-17
 */
public class ZqmCollectedStatistics {

    private DoubleList m_times = new DoubleList();
    private double m_min = Double.MAX_VALUE; // 运行时间最小值
    private double m_max = 0.0; // 运行时间最大值
    private double MICROS_IN_MILLIS = 1e3;

    public synchronized void add(ZqmStopWatch sw) {
        double timeInMs = sw.getTimeMicros() / MICROS_IN_MILLIS;

        for (int i = 0; i < sw.getCount(); i++) {
            m_times.add(timeInMs / sw.getCount());
        }

        if (timeInMs < m_min) m_min = timeInMs;
        if (timeInMs > m_max) m_max = timeInMs;
    }

    public synchronized int getInvocations() {
        return m_times.size();
    }


    public synchronized double getAverageMS() {
        double result = 0.0;
        for (Double d : m_times.m_values) {
            result += d;
        }
        return result / m_times.size();
    }

    public synchronized double getStdDev() {
        return Math.sqrt(variance());
    }

    public synchronized double variance() {
        long n = 0;
        double mean = 0;
        double s = 0.0;
        for (double x : m_times.m_values) {
            n++;
            double delta = x - mean;
            mean += delta / n;
            s += delta * (x - mean);
        }

        return (s / n);
    }

    public synchronized double getPercentile(int percentile) {
        return getPercentile((double) percentile);
    }

    public double getPercentile(double percentile) {
        ZqmPercentile p = new ZqmPercentile();

        return p.evaluate(m_times.m_values, 0, m_times.size(), percentile);
    }

    public synchronized double getMin() {
        return m_min;
    }

    public synchronized double getMax() {
        return m_max;
    }

    private static final class DoubleList {
        public double[] m_values = new double[256]; // 数组
        public int m_size; //真实数据大小 默认值是0

        public void add(double d) {
            ensureCapacity(m_size + 1);
            m_values[m_size++] = d;
        }

        public int size() {
            return m_size;
        }

        private void ensureCapacity(int capacity) {
            // 需要容量 大于 数组大小， 则需要扩容
            if (capacity > m_values.length) {
                // 扩大1.5倍
                int newsize = ((m_values.length * 3) / 2) + 1;
                double[] olddata = m_values;
                // 需要容量
                m_values = new double[newsize < capacity ? capacity : newsize];
                System.arraycopy(olddata, 0, m_values, 0, m_size);
            }
        }
    }
}
