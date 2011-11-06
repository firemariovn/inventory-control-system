package org.ntu.eee.csn.oosd.jvoter.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import org.ntu.eee.csn.oosd.jvoter.model.Vote;
import org.ntu.eee.csn.oosd.jvoter.model.VoteReply;
import org.ntu.eee.csn.oosd.jvoter.model.Voter;
import org.ntu.eee.csn.oosd.jvoter.ui.VoteInitiationUI;
import org.ntu.eee.csn.oosd.jvoter.ui.VoteListUI;
import org.ntu.eee.csn.oosd.jvoter.util.DBUtil;
import org.ntu.eee.csn.oosd.jvoter.util.JVoterProtocol;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.CremeCoffeeSkin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * This is the Main Frame of JVoter in UI Layer
 *  
 * @author WangDing
 *
 */
public class MainUI implements JVoterProtocol {

	private JList onlineUserList;
	private JLabel lblOnlineUsers;
	private JFrame frame;
	private MulticastSocket ds;
	private JButton unRepliedVotesButton;
    private final String guid = DBUtil.generateGUID();
    private Map<String, Voter> userTable = new Hashtable<String, Voter>();
    private int usernum=0;
    private ArrayList<Voter> voters;
    private DatagramSocket da;
    private DatagramSocket db;
    private ArrayList<Vote> votes= new ArrayList<Vote>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	//	JFrame.setDefaultLookAndFeelDecorated(true);
	//	JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			//	SubstanceLookAndFeel.setSkin(new CremeCoffeeSkin());
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
		unicastlisten();
		Multicastlisten();// 监听收到的消息
        broadcast(USER_ON_LINE);// 广播上线
        System.out.println("hello!");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				broadcast(USER_OFF_LINE);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				broadcast(USER_OFF_LINE);
			}
		});
		frame.setBounds(100, 100, 449, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 47, 437, 412);
		frame.getContentPane().add(scrollPane);
		
		
		DefaultListModel voterlist=new DefaultListModel();
	    onlineUserList = new JList(voterlist);
		scrollPane.setViewportView(onlineUserList);
		
		 voters = new ArrayList<Voter>();
		
		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(6, 461, 437, 102);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton initialiteVoteButton = new JButton("Initiate a Vote");
		initialiteVoteButton.addActionListener(bl);
		panel.add(initialiteVoteButton);
		
		unRepliedVotesButton = new JButton("Unreplied Votes[0]");
		unRepliedVotesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VoteListUI vlUI = new  VoteListUI(votes,da,unRepliedVotesButton);
	             JFrame jfVI = new JFrame();
	             jfVI.getContentPane().add(vlUI);
	             jfVI.setBounds(0, 0, 465,520);
	             jfVI.setResizable(false);
	             jfVI.setVisible(true);
			}
		});
		panel.add(unRepliedVotesButton);
		
		JButton viewAllButton = new JButton("View All Votes");
		panel.add(viewAllButton);
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topPanel.setBounds(6, 6, 437, 39);
		frame.getContentPane().add(topPanel);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
	    lblOnlineUsers = new JLabel("Online Users:");
		topPanel.add(lblOnlineUsers);
	
	
	}
	
	/*
	 * background functions*/
	 public void broadcast(final int flag) {
		    
		    	System.out.println("1");
	        try {
	                //da=new DatagramSocket(8789);
	                byte[] data = (flag + guid ).getBytes("UTF-8");
	                //ds.joinGroup(InetAddress.getByName("230.0.0.2"));
	                da.send(new DatagramPacket(data, 0, data.length, InetAddress.getByName("230.0.0.2"), MULTICAST_PORT));
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		    
		 
		    }
	 public void Multicastlisten(){
		 System.out.println("2");
	    	try {
	            ds = new MulticastSocket(MULTICAST_PORT);
	            //da=new DatagramSocket(7788);
	            ds.joinGroup(InetAddress.getByName("230.0.0.2"));
	        } catch (Exception e) {
	            System.out.println("port is unavailable!!");
	            return;
	        }
	    	
	    	 new Thread() {
	             public void run() {
	            	 
	            	
	                 while (true) {
	                     try {
	                         DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
	                         //ds.joinGroup(InetAddress.getByName("230.0.0.2"));
	                         ds.receive(dp);

	                         
	                         String msg = new String(dp.getData(), 0, dp.getLength(), "UTF-8");

	                         if (msg != null && msg.length() > 0) {

	                             int msgType = Integer.parseInt(msg.substring(0, 1));
	                             String uid = msg.substring(1, 33);

	                             switch (msgType) {
	                             case 0:
	                                 if (!userTable.containsKey(uid)) {
	                                	 Voter user = new Voter();
	                                     user.setGuid(uid);
	                                     user.setInetAddress(dp.getAddress().getHostAddress());
	                                     //user.setInetAddress(dp.getAddress().toString());
	                                	// user.setHostAddress(dp.getAddress().getHostAddress());
	                                	 try{ 
	                                	 String name=dp.getAddress().getCanonicalHostName();
	                                	 
	                                	 //String username=name.substring(0, name.indexOf('.'));
                                         user.setHostName(name);
	                                	 }catch(Exception e){
	                                		 System.out.println(e.toString());
	                                		 //String username=user.getInetAddress().substring(0, user.getInetAddress().indexOf('.'));
	                                		 //user.setHostName(username);
	                                	 }
	                                     addUser(user);
	                                      System.out.println("新用户上线");
	                                      System.out.println(user.getGuid());
	                                      System.out.println(guid+"aa");
	                                      //sleep(3000);
	                                 
	                                     byte[] data = (USER_ON_LINE +"|"+ guid).getBytes("UTF-8");
	                                     DatagramPacket p = new DatagramPacket(data, data.length, dp.getAddress(), UNICAST_LISTEN_PORT);
	                                     
	                                     da.send(p);
	                                     //ds.close();
	                                 }
	                                else{
	                                 	System.out.println("已经存在111");
	                                 }
	                                 break;
	                             case 1:
	                                 //removeUser(uid);
	                            	 Voter user = new Voter();
                                     user.setGuid(uid);
                                     user.setInetAddress(dp.getAddress().getHostAddress());
                                     //user.setInetAddress(dp.getAddress().toString());
                                	 //user.setHostAddress(dp.getAddress().getHostAddress());
                                     try{ 
	                                	 String name=dp.getAddress().getCanonicalHostName();
	                                	 
	                                	 //String username=name.substring(0, name.indexOf('.'));
                                         user.setHostName(name);
	                                	 }catch(Exception e){
	                                		 System.out.println(e.toString());
	                                		 //String username=user.getInetAddress().substring(0, user.getInetAddress().indexOf('.'));
	                                		 //user.setHostName(username);
	                                	 }
                                	 removeUser(user);
	                                 break;
	                             case 2:
	                                 if (!guid.equals(uid)) {
	                                     
	                                     byte[] data = (USER_ON_LINE + guid ).getBytes("UTF-8");
	                                     DatagramPacket p = new DatagramPacket(data, data.length, dp.getAddress(), 5413);
	                                     ds.send(p);
	                                 }
	                                 break;
	                             }
	                         }
	                     } catch (Exception e) {
	                         e.printStackTrace();
	                     }
	                 }
	             }
	         }.start();
	    }
	    public void addUser(Voter user) {
	        userTable.put(user.getGuid(), user);
	        usernum++;
	        DefaultListModel voterlist=(DefaultListModel)onlineUserList.getModel();
	        String msg=user.getHostName()+"/"+user.getInetAddress();
	        voterlist.insertElementAt(msg,0);
	        lblOnlineUsers.setText("Online Users:"+usernum);
	        voters.add(user);
	        
	    }
	    public void removeUser(Voter user){
	    	userTable.remove(user.getGuid());
	    	usernum--;
	    	DefaultListModel voterlist=(DefaultListModel)onlineUserList.getModel();
	        String msg=user.getHostName()+"/"+user.getInetAddress();
	        voterlist.removeElement(msg);
	        
	        lblOnlineUsers.setText("Online Users:"+usernum);
	        voters.remove(user);
	    }
	    public void unicastlisten(){
	    	  try {
	                da=new DatagramSocket(UNICAST_SEND_PORT);
	                db=new DatagramSocket(UNICAST_LISTEN_PORT); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	  new Thread(){
	    		  public void run(){
	    			  while (true) {
		                     try {
		                         DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
		                         //ds.joinGroup(InetAddress.getByName("230.0.0.2"));
		                         db.receive(dp);
		                         

					                String data = new String(dp.getData(),0, dp.getLength(), "UTF-8");
					                System.out.println(data);
					                
					                String[] seg =data.split("\\|"); //split the message received to get the flag
					                System.out.println(seg[0]);
					                int flag = Integer.parseInt(seg[0]);
					                
					                switch(flag)
					                {
					                	case JVoterProtocol.NEW_VOTE:  //vote invitation message
					                		ArrayList<String> op = new ArrayList<String>();
					                		op.add(seg[3]);
					                		op.add(seg[4]);
					                		op.add(seg[5]);
					                		op.add(seg[6]);
					                		Date date = new Date(seg[7]);		
					                		Vote v = new Vote(seg[1],seg[2],op,date,seg[8],false,false);
					                		votes.add(v);
							                unRepliedVotesButton.setText("Unreplied Votes["+votes.size()+"]");
							                JOptionPane.showMessageDialog(null, 
					        		                "You have been invited to a new vote", "New Vote",JOptionPane.INFORMATION_MESSAGE);
					                		break;
					                		
					                	case JVoterProtocol.REPLY_VOTE:  //vote reply message 
					                		VoteReply vr = new VoteReply(seg[1],Integer.valueOf(seg[2]),seg[3]);
					                		
					                		JOptionPane.showMessageDialog(null, 
					        		                "A reply from "+vr.getReplierHost()+" has been received", "New Reply",JOptionPane.INFORMATION_MESSAGE);
					                		break;
					                		
					                	case 0:
					                		String uid=seg[1].toString();
			                                 if (!userTable.containsKey(uid)) {
			                                	 Voter user = new Voter();
			                                     user.setGuid(uid);
			                                     user.setInetAddress(dp.getAddress().getHostAddress());
			                                     //user.setInetAddress(dp.getAddress().toString());
			                                	// user.setHostAddress(dp.getAddress().getHostAddress());
			                                	 //String name=dp.getAddress().getCanonicalHostName();
			                                	 //String username=name.substring(0, name.indexOf('.'));
		                                         //user.setHostName(username);
			                                	 
			                                     try{ 
				                                	 String name=dp.getAddress().getCanonicalHostName();
				                                	 
				                                	 //String username=name.substring(0, name.indexOf('.'));
			                                         user.setHostName(name);
				                                	 }catch(Exception e){
				                                		 System.out.println(e.toString());
				                                		 //String username=user.getInetAddress().substring(0, user.getInetAddress().indexOf('.'));
				                                		 //user.setHostName(username);
				                                	 }
			                                	 
			                                     addUser(user);
			                                      System.out.println("新用户上线");
			                                      System.out.println(user.getGuid());
			                                      System.out.println(guid+"aa");
			                                      //sleep(3000);
			                                 
			                                 
			                                     //ds.close();
			                                 }
			                                else{
			                                 	System.out.println("已经存在111");
			                                 }
			                                 break;
					                	default:
					                		System.out.println("no");
					                		break;
					                		
					                }

		                     } 
		                     catch (Exception e) {
		                         e.printStackTrace();
		                     }
		                 }
		             }
	    		  
	    	  }.start();
	    }
	    
	    //OPen a new windows to initiate a new vote
		//@Author: LU Mukai G1101045F
		 private ActionListener bl = new ActionListener()
		 {
			 
			 public void actionPerformed(ActionEvent e)
	         {
	             //Execute when button is pressed
	             VoteInitiationUI viUI = new VoteInitiationUI(voters,da);
	             JFrame jfVI = new JFrame();
	             jfVI.getContentPane().add(viUI);
	             jfVI.setBounds(0, 0, 585,510);
	             jfVI.setResizable(false);
	             
	             jfVI.setVisible(true);
	         }
		 };
	    
}
