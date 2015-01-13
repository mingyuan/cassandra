package cn.mingyuan.demo.jmx;

public interface HelloWorldMBean {
	public String getHelloString();

	public void setHelloString(String helloString);

	public void printHello();

	public void printHelloTo(String who);

	public String getHelloStringHAHAHA();
}
