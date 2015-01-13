package cn.mingyuan.demo.jmx;

import java.io.IOException;
import java.util.Set;

import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class HelloWorldClient {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ReflectionException
	 * @throws MBeanException
	 * @throws InvalidAttributeValueException
	 * @throws AttributeNotFoundException
	 * @throws InstanceNotFoundException
	 * @throws NullPointerException
	 * @throws MalformedObjectNameException
	 * @throws IntrospectionException
	 */
	public static void main(String[] args) throws IOException, InstanceNotFoundException, AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException,
			MalformedObjectNameException, NullPointerException, IntrospectionException {
		String jmxUrl = "service:jmx:rmi:///jndi/rmi://159.226.182.121:8083/wahaha";
		JMXServiceURL url = new JMXServiceURL(jmxUrl);
		JMXConnector connector = JMXConnectorFactory.connect(url);
		MBeanServerConnection serverConnection = connector.getMBeanServerConnection();
		String[] domains = serverConnection.getDomains();
		System.out.println("domains:");
		if (domains != null && domains.length > 0) {
			for (String domain : domains) {
				System.out.println(domain);
			}
		}
		System.out.println("\n\n");
		System.out.println("total mbean=" + serverConnection.getMBeanCount());
		ObjectName objectName = new ObjectName("spider:name=HelloWorld");
		System.out.println("\n\n");

		// 通过字段名操作
		System.out.println("通过字段名操作");
		serverConnection.setAttribute(objectName, new Attribute("HelloString", "AAA"));
		System.out.println("HelloString Name=" + serverConnection.getAttribute(objectName, "HelloString"));
		System.out.println("\n\n");

		// 得到代理后远程调用
		System.out.println("得到代理后远程调用");
		HelloWorldMBean proxy = (HelloWorldMBean) MBeanServerInvocationHandler.newProxyInstance(serverConnection, objectName, HelloWorldMBean.class, false);
		proxy.setHelloString("HHHH");
		proxy.getHelloStringHAHAHA();
		System.out.println("\n\n");

		// 直接远程调用
		System.out.println("直接远程调用");
		serverConnection.invoke(objectName, "printHello", null, null);
		System.out.println("\n\n");

		// 得到mbean信息
		System.out.println("得到mbean信息");
		MBeanInfo mBeanInfo = serverConnection.getMBeanInfo(objectName);
		System.out.println("class name=" + mBeanInfo.getClassName());
		// 得到所有mbean的objectName
		System.out.println("all objectName");

		Set<ObjectInstance> queryMBeans = serverConnection.queryMBeans(null, null);
		for (ObjectInstance mb : queryMBeans) {
			System.out.println(mb.getObjectName());
		}

		connector.close();
	}
}
