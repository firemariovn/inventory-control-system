package org.ntu.eee.csn.oosd.jvoter.model;

import java.net.InetAddress;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author WangDing
 * 
 */
public class Voter {
	
	/**
	 * Please use LOGGER to records the vote the user has initiated and 
     * participated.
	 */
	private static Logger LOGGER = Logger.getLogger(Voter.class);

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
