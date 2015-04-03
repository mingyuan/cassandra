package casia.isiteam.datagroup.cassandra.main;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Test {

	public static void main(String[] args) {
		Cluster cluster=Cluster.builder().addContactPoint("192.168.6.156").build();
	}

}
