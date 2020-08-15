package zqm_speed4j;

/**
 * 1.创建 2.开始 3.停止
 * * 1.增加其该stopwatch的标识性
 * Created by useheart on 2019-03-17
 */
public class ZqmStopWatch {

    private static final String DEFAULT_TAG = "[default]";

    private long m_creation;
    private long m_startNanos;
    private long m_stopNanos;
    private int m_count;
    private String m_tag;
    private String m_message;

    private static final long NANOS_IN_SECOND = 1000 * 1000 * 1000;

    public ZqmStopWatch() {
        this(null, null);
    }

    public ZqmStopWatch(String tag) {
        this(tag, null);
    }

    public ZqmStopWatch(String tag, String message) {
        m_tag = tag;
        m_message = message;
        m_creation = System.currentTimeMillis();
        start();
    }

    public ZqmStopWatch start() {
        m_startNanos = System.nanoTime();
        return this;
    }

    protected void internalStop() {
        m_stopNanos = System.nanoTime();
    }

    public ZqmStopWatch stop() {
        internalStop();
        return this;
    }

    public ZqmStopWatch stop(int count) {
        m_count = count;
        stop();
        return this;
    }

    public ZqmStopWatch stop(String tag) {
        m_tag = tag;
        stop();
        return this;
    }

    public ZqmStopWatch stop(String tag, int count) {
        m_tag = tag;
        stop(count);
        return this;
    }

    public ZqmStopWatch stop(String tag, String message) {
        m_tag = tag;
        m_message = message;
        stop();
        return this;
    }

    public ZqmStopWatch stop(String tag, String message, int count) {
        m_tag = tag;
        m_message = message;
        stop(count);
        return this;
    }

    public ZqmStopWatch lap() {
        stop();
        start();
        return this;
    }

    private long getTimeNanos() {
        if (m_stopNanos != 0) {
            return m_stopNanos - m_startNanos;
        }

        return System.nanoTime() - m_startNanos;
    }

    public long getTimeMicros() {
        return getTimeNanos() / 1000;
    }

    public long getElapsedTime() {
        return getTimeNanos() / (1000 * 1000);
    }

    public long getCreation() {
        return m_creation;
    }

    public String getMessage() {
        return m_message;
    }

    public void setMessage(String msg) {
        m_message = msg;
    }

    public String getTag() {
        return m_tag;
    }

    public void setTag(String tag) {
        m_tag = tag;
    }

    public long getCount() {
        return m_count;
    }

    @Override
    public String toString() {
        return (m_tag != null ? m_tag : DEFAULT_TAG).concat(": ").concat(getReadableTime()).concat((m_message != null ? " " + m_message : ""));
    }

    private String getReadableTime() {
        long ns = getTimeNanos();
        if (ns < 50L * 1000) {
            return ns + "ns";
        }

        if (ns < 50L * 1000 * 1000) {
            return (ns / 1000) + "us";
        }

        if (ns < 50L * 1000 * 1000 * 1000) {
            return (ns / (1000 * 1000)) + "ms";
        }

        return ns / NANOS_IN_SECOND + "s";
    }

    /**
     * The returned StopWatch is automatically stopped.
     */
    public ZqmStopWatch freeze() {
        ZqmStopWatch sw = new ZqmStopWatch(m_tag, m_message);
        sw.m_startNanos = m_startNanos;
        sw.m_stopNanos = m_stopNanos != 0 ? m_stopNanos : System.nanoTime();
        sw.m_creation = m_creation;
        sw.m_count = m_count;
        return sw;
    }
}
