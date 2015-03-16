log4j-hessian is a log4j appender and a [Hessian](http://hessian.caucho.com/) service (and a hosting webapp) to be used in the same manner as log4j's [SocketAppender](http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/net/SocketAppender.html) and [SimpleSocketServer](http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/net/SimpleSocketServer.html).

The appender can be used as any other appender as shown in the following `log4j.xml` example:

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE log4j:configuration SYSTEM "http://logging.apache.org/log4j/docs/api/org/apache/log4j/xml/log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
	<appender name="console" class="org.apache.log4j.ConsoleAppender">
		<param name="target" value="System.out" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%d{HH:mm:ss,SSS} %t %-5p %c - %m%n" />
		</layout>
	</appender>

	<appender name="hessian" class="org.googlecode.log4j_hessian.Log4JHessianAppender">
		<param name="url" value="http://localhost:8080/log4j-hessian-service-webapp/" />
		<param name="locationInfo" value="true" />
		<param name="application" value="hessian-log4-appender-test" />
	</appender>

	<appender name="async" class="org.apache.log4j.AsyncAppender">
		<appender-ref ref="hessian" />
	</appender>

	<root>
		<priority value="ALL" />
		<appender-ref ref="async" />
                <appender-ref ref="console" />
	</root>
</log4j:configuration>
```

The example above uses an [AsyncAppender](http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/AsyncAppender.html) wrapping the Log4JHessianAppender, which will allow remote logging without hindering the performance of the application due to the combination of synchronous and remote logging.

The appender accepts the following parameters:

| **parameter** | **description** |
|:--------------|:----------------|
| url | URL to the Hessian service |
| locationInfo | Whether to include the event location information |
| application | An identifier for the remote application logging into the Hessian service |