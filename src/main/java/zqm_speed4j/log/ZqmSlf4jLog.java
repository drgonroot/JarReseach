package zqm_speed4j.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zqm_speed4j.ZqmStopWatch;

/**
 * Created by useheart on 2019-03-17
 */
public class ZqmSlf4jLog extends ZqmLog {

    protected Logger m_log;

    public void setSlf4jLogname(String logName) {
        if (logName.length() == 0) {
            m_log = null;
        } else {
            m_log = LoggerFactory.getLogger(logName);
        }
    }

    @Override
    public void log(ZqmStopWatch sw) {
        if (m_log != null && m_log.isInfoEnabled()) {
            m_log.info(sw.toString());
        }
    }
}
