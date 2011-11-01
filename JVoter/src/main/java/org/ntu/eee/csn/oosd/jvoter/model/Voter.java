package org.ntu.eee.csn.oosd.jvoter.model;

import java.net.InetAddress;

/**
 * 
 * 
 * @author WangDing
 * 
 */
public class Voter {

	private String hostAddress;

	private InetAddress inetAddress;

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public InetAddress getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}

}
