### logback 日志学习
参考资料:   
[Logback 中文文档
](https://www.docs4dev.com/docs/zh/logback/1.3.0-alpha4/reference/introduction.html#%E7%AC%AC-1-%E7%AB%A0logback-%E7%AE%80%E4%BB%8B)   
[Logback 官方文档](http://logback.qos.ch/setup.html#ide)   

需要导入Jar
```xml
        
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.12</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <scope>compile</scope>
         </dependency>
```
logback-classic 这个jar其实还引用了logback-core和slf4j-api。

StaticLoggerBinder是slf4j日志输出器实现