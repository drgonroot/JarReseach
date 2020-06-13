package ZqmSpeed4j;

import ZqmSpeed4j.log.ZqmLog;

/**
 * Created by useheart on 2019-03-17
 */
public class ZqmLoggingStopWatch extends ZqmStopWatch {

    private ZqmLog m_log;

    protected ZqmLoggingStopWatch(ZqmLog log, String tag, String msg) {
        super(tag, msg);
        m_log = log;
    }

    @Override
    protected void internalStop() {
        super.internalStop();
        // Do the logging here
        if (m_log != null && m_log.isEnable()) {
            m_log.log(this);
        }
    }
}
