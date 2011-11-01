package test;
import java.net.*;
public class User {

	/**
	 * @param args
	 */
	 private String guid;
     private String userName;
     private String hostAddress;
     private InetAddress inetAddress;

     public String getGuid() {
         return guid;
     }

     public void setGuid(String guid) {
         this.guid = guid;
     }

     public String getUserName() {
         return userName;
     }

     public void setUserName(String userName) {
         this.userName = userName;
     }

     public String getHostAddress() {
         return hostAddress;
     }

     public InetAddress getInetAddress() {
         return inetAddress;
     }

     public void setInetAddress(InetAddress inetAddress) {
         this.inetAddress = inetAddress;
         this.hostAddress = inetAddress.getHostAddress();
     }

     public String toString() {
         return userName + "(" + hostAddress + ")";
     }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Messenger();
	}

}
