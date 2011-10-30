import java.io.*;
import java.net.*;
import java.util.*;

public class JVoterDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		MulticastThread multicast = new MulticastThread();
		multicast.start();
		DatagramSocket socket = new DatagramSocket(7899);
		InetAddress group = InetAddress.getByName("230.0.0.2");
		//byte[] buf = new byte[256];
		hello h1 = new hello("send by object");
		ByteArrayOutputStream buff = new ByteArrayOutputStream();
		ObjectOutputStream os =new ObjectOutputStream(buff);
		os.writeObject(h1);
		//String s ="Hello";
		//buf=s.getBytes();
        DatagramPacket packet = new DatagramPacket(buff.toByteArray(), buff.toByteArray().length,group,7788);
        os.close();
        
        
        socket.send(packet);
        System.out.println("start");
        System.out.println(buff.toByteArray().length);
        
  

	}

}

 class MulticastThread extends Thread {

	
    protected BufferedReader in = null;
    private MulticastSocket socket = new MulticastSocket(7788);
    private InetAddress address = InetAddress.getByName("230.0.0.2");
    private DatagramPacket packet;
    private long FIVE_SECONDS = 5000;
    
 

    public MulticastThread() throws IOException {
        this("MulticastServerThread");
    }
    
    public MulticastThread(String name) throws IOException {
        super(name);
        socket.joinGroup(address);
        
    }

    public void run() {
    	
    		try{

    			System.out.println("startListen");
    	        byte[] buf = new byte[10000000];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("packet");

                ByteArrayInputStream buff = new ByteArrayInputStream(packet.getData());
                ObjectInputStream oi = new ObjectInputStream(buff);
                hello h2 = (hello)oi.readObject();
                //String received = new String(packet.getData(), 0, packet.getLength());
                //if(received=="Hello")
                InetAddress addr = packet.getAddress();
                System.out.println("New coming user: "+addr.getHostAddress()+" ("+h2.getMess()+")");
               // try {
                  //  sleep((long)(Math.random() * FIVE_SECONDS));
                //} catch (InterruptedException e) { }
    		}
    		catch(Exception e)
    		{
    		
    		}
    	

    	try{
    	socket.leaveGroup(address);
    	socket.close();
    	}
		catch(IOException e)
		{
		
		}
    }
    
    
}
 
 class hello  implements java.io.Serializable 
 {
	 private String mess;
	 
	 public hello(String mes)
	 {
		 mess = mes;
	 }
	 
	 public String getMess()
	 {
		 return mess;
	 }
 
 }