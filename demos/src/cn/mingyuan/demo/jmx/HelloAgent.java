package cn.mingyuan.demo.jmx;

import java.rmi.registry.LocateRegistry;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent {
	public static void main(String[] args) throws Exception {
		MBeanServer server = MBeanServerFactory.createMBeanServer();
		HelloWorld helloWorld = new HelloWorld();
		server.registerMBean(helloWorld, new ObjectName("spider:name=HelloWorld"));
		HtmlAdaptorServer htmlAdapter = new HtmlAdaptorServer();
		server.registerMBean(htmlAdapter, new ObjectName("spiderAgent:name=htmladapter,port=8082"));
		htmlAdapter.start();
		int port = 10000;// Integer.parseInt(args[1]);
		LocateRegistry.createRegistry(port);
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + port + "/wahaha");
		// JMXServiceURL url = new
		// JMXServiceURL("service:jmx:rmi:///jndi/rmi://10.16.100.98:8083/wahaha");
		JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
		cs.start();

		System.out.println("start...");
		while (true) {
			System.out.println(helloWorld.getHelloString());
			TimeUnit.SECONDS.sleep(2);
		}
	}
}
