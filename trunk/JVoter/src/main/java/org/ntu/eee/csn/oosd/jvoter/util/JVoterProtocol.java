package org.ntu.eee.csn.oosd.jvoter.util;

public interface JVoterProtocol {
	
	
	int USER_ON_LINE =0;
	
	int USER_OFF_LINE=1;
	
	int  RERESH =2;
	String ss=""; 
    int multicastPort = 6789;
	
	int unicastListenPort = 7789;
	
	int unicastSendPort = 8781;

}
