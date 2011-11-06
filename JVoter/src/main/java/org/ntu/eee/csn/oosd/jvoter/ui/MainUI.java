package org.ntu.eee.csn.oosd.jvoter.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import org.ntu.eee.csn.oosd.jvoter.model.VoteResult;
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
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Panel;
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
    private Timer timer;
	ImageIcon ic = new ImageIcon();
	
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
		Multicastlisten();
        broadcast(USER_ON_LINE);
        System.out.println("hello!");
        votes= Vote.getUnAnsweredVotes();
        
        timer = new Timer();
        startTimer();
        
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		
		frame.setTitle("JVoter MainFrame");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
				/*
				Object[] options = { "Yes", "No" };
			    JOptionPane jp = new JOptionPane("All the votes which haven't been replied will be canceled, still wanna close?", JOptionPane.QUESTION_MESSAGE,
			        JOptionPane.YES_NO_OPTION, null, options, options[1]);
			    JDialog dialog =jp.createDialog("Warning!");
			    dialog.setVisible(true);
			    Object selectedValue = jp.getValue();
			    if (selectedValue == null || selectedValue == options[1]) 
			    {
			    	
			        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			    } 
			    else if (selectedValue == options[0])
			    {
			    	broadcast(USER_OFF_LINE);
			    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    }
			    */
			
	           
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Object[] options = { "Yes", "No" };
			    JOptionPane jp = new JOptionPane("All the votes which haven't been replied will be canceled, still wanna close?", JOptionPane.QUESTION_MESSAGE,
			        JOptionPane.YES_NO_OPTION, null, options, options[1]);
			    JDialog dialog =jp.createDialog("Warning!");
			    dialog.setVisible(true);
			    Object selectedValue = jp.getValue();
			    if (selectedValue == null || selectedValue == options[1]) 
			    {
			    	
			        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			    } 
			    else if (selectedValue == options[0])
			    {
			    	broadcast(USER_OFF_LINE);
			    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    }
			}
		});
		frame.setBounds(100, 100, 285, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 47, 257, 412);
		frame.getContentPane().add(scrollPane);
		
		
		DefaultListModel voterlist=new DefaultListModel();
	    onlineUserList = new JList(voterlist);
		scrollPane.setViewportView(onlineUserList);
		
		 voters = new ArrayList<Voter>();
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		topPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topPanel.setBounds(6, 6, 257, 30);
		frame.getContentPane().add(topPanel);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
	    lblOnlineUsers = new JLabel("Online Users:");
		topPanel.add(lblOnlineUsers);
		
		JButton initialiteVoteButton = new JButton("Initiate a Vote");
		initialiteVoteButton.setVerticalAlignment(SwingConstants.TOP);
		initialiteVoteButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		initialiteVoteButton.setForeground(Color.BLACK);
		initialiteVoteButton.setBackground(Color.WHITE);
		initialiteVoteButton.setBounds(16, 470, 114, 23);
		frame.getContentPane().add(initialiteVoteButton);
		
		unRepliedVotesButton = new JButton("Unreplied Votes[0]");
		unRepliedVotesButton.setForeground(Color.BLACK);
		unRepliedVotesButton.setVerticalAlignment(SwingConstants.TOP);
		unRepliedVotesButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		unRepliedVotesButton.setBackground(Color.WHITE);
		unRepliedVotesButton.setBounds(26, 504, 221, 23);
		frame.getContentPane().add(unRepliedVotesButton);
		
		JButton viewAllButton = new JButton("View All Votes");
		viewAllButton.setForeground(Color.BLACK);
		viewAllButton.setVerticalAlignment(SwingConstants.TOP);
		viewAllButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		viewAllButton.setBackground(Color.WHITE);
		viewAllButton.setBounds(140, 470, 119, 23);
		frame.getContentPane().add(viewAllButton);
		viewAllButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				ArrayList<VoteResult> vrInit = VoteResult.getAllVoteResult();
				
				ArrayList<VoteResult> vrPart = VoteResult.getAllJoinedVotes();
				
				
				try {
					VoteView vv = new VoteView(vrInit,vrPart);
					vv.setBounds(0, 0, 400,520);
		            vv.setResizable(false);
					vv.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		unRepliedVotesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VoteListUI vlUI = new  VoteListUI(votes,da,unRepliedVotesButton);
	             JFrame jfVI = new JFrame();
	             jfVI.getContentPane().add(vlUI);
	             jfVI.setBounds(0, 0, 450,520);
	             jfVI.setResizable(false);
	             jfVI.setVisible(true);
			}
		});
		initialiteVoteButton.addActionListener(bl);
	
	
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
	                                      System.out.println("New User Get Online");
	                                      System.out.println(user.getGuid());
	                                      System.out.println(guid+"aa");
	                                      //sleep(3000);
	                                 
	                                     byte[] data = (USER_ON_LINE +"|"+ guid).getBytes("UTF-8");
	                                     DatagramPacket p = new DatagramPacket(data, data.length, dp.getAddress(), UNICAST_LISTEN_PORT);
	                                     
	                                     da.send(p);
	                                     //ds.close();
	                                 }
	                                else{
	                                 	System.out.println("isExisted");
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
	        System.out.println(msg);
	        voterlist.removeElement(msg);
	        
	        lblOnlineUsers.setText("Online Users:"+usernum);
	        voters.remove(user);
	        for(int i =0; i<votes.size();i++)
	        {
	        	if(votes.get(i).getInitiatorIP().equals(user.getInetAddress()))
	        	{
	        		votes.get(i).delete();
	        		votes.remove(i);
	        	}
	        }
	        unRepliedVotesButton.setText("Unreplied Votes["+votes.size()+"]");
	        JOptionPane.showMessageDialog(null, 
	                user.getHostName()+" has left, all votes initiated by him/her are canceled.", "Cancel Vote",JOptionPane.INFORMATION_MESSAGE);
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
					                		v.setReply(false);
					                		v.setInitiator(InetAddress.getByName(seg[8]).getCanonicalHostName() );
					                		votes.add(v);
							                unRepliedVotesButton.setText("Unreplied Votes["+votes.size()+"]");
							                JOptionPane.showMessageDialog(null, 
					        		                "You have been invited to a new vote", "New Vote",JOptionPane.INFORMATION_MESSAGE);
							                v.add();//add v to database
							                break;
					                		
					                	case JVoterProtocol.REPLY_VOTE:  //vote reply message 
					                		VoteReply vr = new VoteReply(seg[1],Integer.valueOf(seg[2]),seg[3]);
					                		
					                		JOptionPane.showMessageDialog(null, 
					        		                "A reply from "+vr.getReplierHost()+" has been received", "New Reply",JOptionPane.INFORMATION_MESSAGE);
					                		vr.add();
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
			                                      System.out.println("New user gets online");
			                                      System.out.println(user.getGuid());
			                                      System.out.println(guid+"aa");
			                                      //sleep(3000);
			                                 
			                                 
			                                     //ds.close();
			                                 }
			                                else{
			                                 	System.out.println("isExisted");
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
		 
		 public void startTimer()
		 {
			 
			 timer.schedule(new TimerTask() { 
				 
		           public void run() { 
		        	   Date date = new Date();
		        	   for(int i = 0; i<votes.size();i++)
		        	   {
		        		   
		        		   System.out.println(date.toString());
		        		   if(!votes.get(i).getDeadline().after(date))
		        		   {
		        			   try
		       				   {
		       				    
		       				    DatagramPacket packet;
		       				    InetAddress ip = InetAddress.getByName(votes.get(i).getInitiatorIP());//get the Initiator's IP
		       		            String flag =String.valueOf( JVoterProtocol.REPLY_VOTE); //set the flag
		       		            String op =String.valueOf(0); //get the choice index from the JList
		       		            String data = flag+"|"+votes.get(i).getVoteID()+"|"+op +"|"+InetAddress.getLocalHost().getHostAddress();
		       					byte[] buff = data.getBytes();
		       					packet = new DatagramPacket(buff, buff.length,ip,JVoterProtocol.UNICAST_LISTEN_PORT);
		       			        da.send(packet);
		       			        
		       			        VoteReply vr = new VoteReply(votes.get(i).getVoteID(),0, InetAddress.getLocalHost().getHostAddress());
		       			        vr.add();
		       			        voters.remove(i);
		       			        unRepliedVotesButton.setText("Unreplied Votes["+votes.size()+"]");
		       				   }
		       				   catch(Exception e)
		       				   {
		       					    System.out.println(e.toString());
		       				   }
		        			   
		        			   System.out.println("timer:expired");
		        		   }
		        		   else
		        		   {
		        			   System.out.println("timer:not expired");
		        		   }
		        	   }
		        	   
		               
		            } 
		         
		       }, 5000,600000);
		 }
		
		 
		
         
}
