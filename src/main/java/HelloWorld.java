import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by useheart on 2019-03-16
 */
public class HelloWorld {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.info("Hello World{}", args);

        // 打印内部的状态
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        // ch.qos.logback.classic.Logger 可以设置日志的级别
        // 获取一个名为 "com.foo" 的 logger 实例
        ch.qos.logback.classic.Logger loggerClassic =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
        // 设置 logger 的级别为 INFO
        loggerClassic.setLevel(Level.INFO);

        // 这条日志可以打印，因为 WARN >= INFO
        loggerClassic.warn("警告信息");
        // 这条日志不会打印，因为 DEBUG < INFO
        loggerClassic.debug("调试信息");

        // "com.foo.bar" 会继承 "com.foo" 的有效级别
        Logger barLogger = LoggerFactory.getLogger("com.foo.bar");
        // 这条日志会打印，因为 INFO >= INFO
        barLogger.info("子级信息");
        // 这条日志不会打印，因为 DEBUG < INFO
        barLogger.debug("子级调试信息");
    }
}
