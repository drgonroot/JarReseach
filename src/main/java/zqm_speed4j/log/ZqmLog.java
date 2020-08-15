package zqm_speed4j.log;

import zqm_speed4j.ZqmStopWatch;

/**
 * Created by useheart on 2019-03-17
 */
public abstract class ZqmLog {

    private static final String DEFAULT_NAME = "UndefinedLog";
    private boolean m_enable = true;
    private String m_name = DEFAULT_NAME;

    public void setEnable(boolean value) {
        m_enable = value;
    }

    public boolean isEnable() {
        return m_enable;
    }


    public void setName(String name) {
        m_name = name;
    }

    public String getName() {
        return m_name;
    }

    public abstract void log(ZqmStopWatch sw);

    public void shutdown() {
        // Default implementation does nothing
    }
}
