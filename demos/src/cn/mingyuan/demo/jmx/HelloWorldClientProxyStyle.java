package cn.mingyuan.demo.jmx;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class HelloWorldClientProxyStyle {

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
		// String server = args[0];
		// String port = args[1];
		String server = "localhost";
		String port = "10000";
		String jmxUrl = "service:jmx:rmi:///jndi/rmi://" + server + ":" + port + "/wahaha";
		JMXServiceURL url = new JMXServiceURL(jmxUrl);
		JMXConnector connector = JMXConnectorFactory.connect(url);
		MBeanServerConnection serverConnection = connector.getMBeanServerConnection();
		ObjectName objectName = new ObjectName("spider:name=HelloWorld");

		// 得到代理后远程调用
		System.out.println("得到代理后远程调用");
		HelloWorldMBean proxy = (HelloWorldMBean) MBeanServerInvocationHandler.newProxyInstance(serverConnection, objectName, HelloWorldMBean.class, false);
		proxy.setHelloString("HHHH333----33333333");
		String helloStringHAHAHA = proxy.getHelloStringHAHAHA();
		System.out.println(helloStringHAHAHA);

		connector.close();

		// 124.205.145.243 9001
	}
}
