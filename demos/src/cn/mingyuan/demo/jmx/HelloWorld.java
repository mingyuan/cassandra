package cn.mingyuan.demo.jmx;

public class HelloWorld implements HelloWorldMBean {
	private String helloString;

	public String getHelloString() {
		return helloString;
	}

	public void setHelloString(String helloString) {
		this.helloString = helloString;
	}

	public void printHello() {

		System.out.println("hello ," + helloString);
	}

	public void printHelloTo(String who) {

		System.out.println("hello ," + who);
		this.helloString = who;
	}

	public String getHelloStringHAHAHA() {
		System.out.println("hello ," + helloString);
		return "hello ," + helloString;
	}

}
