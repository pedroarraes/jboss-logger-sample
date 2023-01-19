# How to use native JBoss Log for Jakarta EE applications
This smart-start describes how create a simple configuration in Jakarta EE project to runs LOG application on Red Hat JBoss EAP 7.4.

## Requeriments
* OpenJDK 1.8
* JBoss EAP 7.4
* Apache Maven 3.8.5
* VSCode or any IDE maven supportable

## Summary
* [Building an application](#building-an-application)
* [Running JBoss EAP](#running-jboss-eap)
    * [Deploying this example](#deploying-this-example)
    * [Inspecting application logs](#inspecting-application-logs)
* [Understanding LOG configuration](#understanding-log-configuration)
    * [Servlet Example](#servlet-example)
    * [File logging.properties](#file-logging.properties)
    * [Using JBoss EAP System Properties](#using-jboss-eap-system-properties)

## Building an application
To build the example application type the fallow command in the main repository directory.
```bash
$ mvn clean package
```
```console
Ommited
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.119 s
[INFO] Finished at: 2023-01-19T11:41:39-03:00
[INFO] ------------------------------------------------------------------------
```

## Running JBoss EAP
In your jboss directory home execute the fallow command.
```bash
$ ${JBOSS_HOME}/bin/.standalone.sh -c standalone.xml
```
```console
Ommited
11:48:13,988 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-7) WFLYSRV0027: Starting deployment of "jboss-logger-sample.war" (runtime-name: "jboss-logger-sample.war")
11:48:13,999 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-2) WFLYUT0006: Undertow HTTPS listener https listening on 127.0.0.1:8443
11:48:14,057 INFO  [org.jboss.ws.common.management] (MSC service thread 1-4) JBWS022052: Starting JBossWS 5.4.4.Final-redhat-00001 (Apache CXF 3.3.13.redhat-00001) 
11:48:14,569 INFO  [org.infinispan.CONTAINER] (ServerService Thread Pool -- 72) ISPN000128: Infinispan version: Infinispan 'Corona Extra' 11.0.15.Final-redhat-00001
11:48:14,601 INFO  [org.infinispan.CONFIG] (MSC service thread 1-5) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
11:48:14,602 INFO  [org.infinispan.CONFIG] (MSC service thread 1-5) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
11:48:14,662 INFO  [org.infinispan.PERSISTENCE] (ServerService Thread Pool -- 72) ISPN000556: Starting user marshaller 'org.wildfly.clustering.infinispan.spi.marshalling.InfinispanProtoStreamMarshaller'
11:48:14,779 INFO  [org.jboss.as.clustering.infinispan] (ServerService Thread Pool -- 72) WFLYCLINF0002: Started http-remoting-connector cache from ejb container
11:48:14,846 INFO  [org.wildfly.extension.undertow] (ServerService Thread Pool -- 72) WFLYUT0021: Registered web context: '/jboss-logger-sample' for server 'default-server'
11:48:14,883 INFO  [org.jboss.as.server] (Controller Boot Thread) WFLYSRV0010: Deployed "jboss-logger-sample.war" (runtime-name : "jboss-logger-sample.war")
11:48:14,909 INFO  [org.jboss.as.server] (Controller Boot Thread) WFLYSRV0212: Resuming server
11:48:14,911 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: JBoss EAP 7.4.7.GA (WildFly Core 15.0.18.Final-redhat-00001) started in 2752ms - Started 422 of 624 services (348 services are lazy, passive or on-demand)
11:48:14,913 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0060: Http management interface listening on http://127.0.0.1:9990/management
11:48:14,913 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0051: Admin console listening on http://127.0.0.1:9990
```

### Deploying this example
To deploy this application you can just copy target/jboss-logger-sample.war to jboss deployment folder ${JBOSS_HOME}/standalone/deployments or use maven wildfly plugin.
```bash
$ mvn wildfly:deploy
```
Maven deploy output.
```console
Ommited
[INFO] Building war: /home/parraes/jboss-logger-sample/target/jboss-logger-sample.war
[INFO] 
[INFO] <<< wildfly-maven-plugin:1.0.2.Final:deploy (default-cli) < package @ jboss-logger-sample <<<
[INFO] 
[INFO] 
[INFO] --- wildfly-maven-plugin:1.0.2.Final:deploy (default-cli) @ jboss-logger-sample ---
Jan 19, 2023 1:37:37 PM org.xnio.Xnio <clinit>
INFO: XNIO version 3.2.2.Final
Jan 19, 2023 1:37:37 PM org.xnio.nio.NioXnio <clinit>
INFO: XNIO NIO Implementation Version 3.2.2.Final
Jan 19, 2023 1:37:37 PM org.jboss.remoting3.EndpointImpl <clinit>
INFO: JBoss Remoting version 4.0.3.Final
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.831 s
[INFO] Finished at: 2023-01-19T13:37:38-03:00
[INFO] ------------------------------------------------------------------------
```
### Inspecting application logs
The JBoss EAP LOG shows application deploy in server log. Application logs does not apresented in this log. Next session we'll inspect application log.
```console[INFO] Scanning for projects...
Ommited
13:35:08,527 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: JBoss EAP 7.4.7.GA (WildFly Core 15.0.18.Final-redhat-00001) started in 3389ms - Started 422 of 624 services (348 services are lazy, passive or on-demand)
13:35:08,530 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0060: Http management interface listening on http://127.0.0.1:9990/management
13:35:08,530 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0051: Admin console listening on http://127.0.0.1:9990
13:37:37,959 INFO  [org.jboss.as.repository] (management-handler-thread - 1) WFLYDR0001: Content added at location /opt/jboss-eap-7.4/standalone/data/content/83/6d5bae745f0b1f76cc762c07b75c78055897fa/content
13:37:37,965 INFO  [org.wildfly.extension.undertow] (ServerService Thread Pool -- 73) WFLYUT0022: Unregistered web context: '/jboss-logger-sample' from server 'default-server'
13:37:37,983 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-1) WFLYSRV0028: Stopped deployment jboss-logger-sample.war (runtime-name: jboss-logger-sample.war) in 20ms
13:37:37,987 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-8) WFLYSRV0027: Starting deployment of "jboss-logger-sample.war" (runtime-name: "jboss-logger-sample.war")
13:37:38,044 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-8) WFLYSRV0028: Stopped deployment jboss-logger-sample.war (runtime-name: jboss-logger-sample.war) in 56ms
13:37:38,046 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-5) WFLYSRV0027: Starting deployment of "jboss-logger-sample.war" (runtime-name: "jboss-logger-sample.war")
13:37:38,125 INFO  [org.wildfly.extension.undertow] (ServerService Thread Pool -- 73) WFLYUT0021: Registered web context: '/jboss-logger-sample' for server 'default-server'
13:37:38,166 INFO  [org.jboss.as.server] (management-handler-thread - 1) WFLYSRV0013: Redeployed "jboss-logger-sample.war"
13:37:38,166 INFO  [org.jboss.as.server] (management-handler-thread - 1) WFLYSRV0016: Replaced deployment "jboss-logger-sample.war" with deployment "jboss-logger-sample.war"
13:37:38,168 INFO  [org.jboss.as.repository] (management-handler-thread - 1) WFLYDR0002: Content removed from location /opt/jboss-eap-7.4/standalone/data/content/de/15f1e5fe9f099f80211ab40035f75953f27537/content
```
### Inspecting application logs
To inspect the logs, first we have to execute de application, for this you can use the web browser o use curl command, example:
```bash
$ curl http://localhost:8080/jboss-logger-sample/hello
```
Output
```console
<h1>Hello World from Acme Organizations!</h1>
```
## Understanding LOG configuration
This section presents application source code, log properties and jboss eap system properties.

### Servlet Example
Below JAVA Class to invoke a servlet and print application log.
```java
package org.acme;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

@WebServlet(name = "hello", urlPatterns = { "/hello" }) (1)
public class Hello extends HttpServlet {

	private static final long serialVersionUID = -5032053177353366934L;
	
	private static final Logger LOGGER = Logger.getLogger(Hello.class) (2);

	public Hello() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.debug("This is an example of a DEBUG log!"); (3)
		LOGGER.error("This is an example of an ERROR log!");
		LOGGER.info("This is an example of an INFO log!");
		LOGGER.warn("This is an example of a WARN log!");
		LOGGER.trace("This is an example of a TRACE log!");
				
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<h1>Hello World from Acme Organizations!</h1>");
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
```
(1) Jakarta EE Servlet implementation, mapped by http://localhost:8080/jboss-logger-sample/hello.
(2) Global object for the class to writes log file.
(3) Examples of logs type.

### File logging.properties
Below logging.properties, witch defines application log.
```code
```
