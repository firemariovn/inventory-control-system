package org.ntu.eee.csn.oosd.jvoter.util;

/**
 * 
 *@author WangDing G1101030A
 *
 */
public interface JVoterProtocol {
	
	
	int USER_ON_LINE =0;
	
	int USER_OFF_LINE=1;
	
	int  RERESH =2;
	
    int MULTICAST_PORT = 6789;
	
	int UNICAST_LISTEN_PORT = 7789;
	
	int UNICAST_SEND_PORT = 8781;
	
    int NEW_VOTE = 5;
	
    int REPLY_VOTE = 6;


}
