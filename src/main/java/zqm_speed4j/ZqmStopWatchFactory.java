package zqm_speed4j;

import com.ecyrd.speed4j.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zqm_speed4j.log.ZqmLog;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 单例
 * Created by useheart on 2019-03-17
 */
public class ZqmStopWatchFactory {

    private static final Logger log = LoggerFactory.getLogger(ZqmStopWatchFactory.class);
    private static final String PROPERTY_PREFIX = "speed4j";
    private static Properties c_config = new Properties();
    private static final String PROPERTYFILENAME = "speed4j.properties";

    /**
     * You may set the property file name also from the command line using
     * a system property.  For example
     *
     * <pre>
     *    java -Dspeed4j.properties=myapplication_speed4j.properties com.mycompany.myapplication.Main
     *  </pre>
     */
    public static final String SYSTEM_PROPERTY = "speed4j.properties";

    private static Map<String, ZqmStopWatchFactory> c_factories;

    private ZqmLog m_log;

    public ZqmStopWatch getStopWatch() {
        return getStopWatch(null, null);
    }

    public ZqmStopWatch getStopWatch(String tag, String msg) {
        return new ZqmLoggingStopWatch(m_log, tag, msg);
    }

    public ZqmStopWatch getStopWatch(String tag) {
        return getStopWatch(tag, null);
    }

    private ZqmLog getLog() {
        return m_log;
    }

    public ZqmStopWatchFactory(ZqmLog logger) {
        m_log = logger;
    }

    private static synchronized Map<String, ZqmStopWatchFactory> getFactories() {
        if (c_factories == null)
            configure();
        return c_factories;
    }

    private static void configure() {
        String propertyFile = System.getProperty(SYSTEM_PROPERTY, PROPERTYFILENAME);
        InputStream in = findConfigFile(propertyFile, "/default_speed4j.properties");

        configure(in);
    }

    private static synchronized void configure(InputStream in) {
        if (c_factories == null) {
            c_factories = new HashMap<>();
        }
        try {
            c_config.load(in);
        } catch (IOException e2) {
            throw new ZqmConfigurationException(e2);
        }

        for (Enumeration<String> e = (Enumeration<String>) c_config.propertyNames(); e.hasMoreElements(); ) {
            String key = e.nextElement();

            String[] components = key.split("\\.");
            if (components.length < 2 || !components[0].equals(PROPERTY_PREFIX)) continue;

            String logger = components[1];

            ZqmStopWatchFactory swf = c_factories.get(logger);
            // 第一配置需要写 Logger
            if (swf == null) {
                swf = new ZqmStopWatchFactory(instantiateLog(logger));
            }

            //
            //  Call the respective setXXX() methods of the logger
            //  based on the configuration.
            //
        }
    }

    private static ZqmLog instantiateLog(String logger) {
        String className = c_config.getProperty(PROPERTY_PREFIX + "." + logger);
        try {
            @SuppressWarnings("unchecked")
            Class<ZqmLog> swfClass = (Class<ZqmLog>) Class.forName(className);
            ZqmLog lg = swfClass.newInstance();
            lg.setName(logger);
            return lg;
        } catch (IllegalAccessException e) {
            log.error("Configuration problem: I was unable to instantiate class {}, defined for logger {}", className, logger);
            throw new ConfigurationException(e);
        } catch (ClassNotFoundException e1) {
            log.error("Configuration problem: I was unable to locate class {}, defined for logger {}", className, logger);
            throw new ConfigurationException(e1);
        } catch (InstantiationException e) {
            log.error("Configuration problem: I was unable to instantiate class {}, defined for logger {}", className, logger);
            throw new ConfigurationException(e);
        }

    }

    private static InputStream findConfigFile(String... alternatives) {
        for (String name : alternatives) {
            InputStream in = ZqmStopWatchFactory.class.getResourceAsStream(name);

            if (in == null)
                in = ZqmStopWatchFactory.class.getResourceAsStream("/" + name);

            if (in == null)
                in = ZqmStopWatchFactory.class.getResourceAsStream("/WEB-INF/" + name);

            if (in != null)
                return in;
        }

        return null;
    }

    /**
     * 静态方法 获取实例
     */
    public static ZqmStopWatchFactory getInstance(ZqmLog logger) {
        return new ZqmStopWatchFactory(logger);
    }

    public static ZqmStopWatchFactory getInstance(String configFileName, String loggerName) {
        ZqmStopWatchFactory swf = getFactories().get(loggerName);
        if (swf == null) {
            configure(findConfigFile(configFileName));
            swf = getFactories().get(loggerName);
            if (swf == null) {
                throw new ConfigurationException("No logger by name" + loggerName + " found.");
            }
        }

        return swf;
    }

}
