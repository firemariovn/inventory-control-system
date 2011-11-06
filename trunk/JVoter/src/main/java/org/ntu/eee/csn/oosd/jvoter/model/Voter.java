package org.ntu.eee.csn.oosd.jvoter.model;


import java.net.InetAddress;
import java.net.*;
import org.apache.log4j.Logger;
import org.ntu.eee.csn.oosd.jvoter.util.JVoterProtocol;

/**
 * 
 * 
 * @author WangDing
 * 
 */
public class Voter implements JVoterProtocol{
	
	/**
	 * Please use LOGGER to records the vote the user has initiated and 
     * participated.
	 */
	private static Logger LOGGER = Logger.getLogger(Voter.class);

	//private String hostAddress;

	private String inetAddress;
    
	private String hostname;
	private String guid;
	
	
	
	
	public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
	//public String getHostAddress() {
	//	return hostAddress;
	//}
	
	

	//public void setHostAddress(String hostAddress) {
		
		//this.hostAddress = hostAddress;
	//}


	public String getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(String inetAddress) {
		this.inetAddress = inetAddress;
	}

	public void setHostName(String hostname) {
		this.hostname = hostname;
	}
	public String getHostName(){
		return this.hostname;
	}
	public Voter(){
		
		
	}
	
	public boolean equals(Object obj){
	    Voter v= (Voter)obj;
	    
	    if(guid.equals(v.guid))
		return true;
	    else
	    return false;
	}
	
}
