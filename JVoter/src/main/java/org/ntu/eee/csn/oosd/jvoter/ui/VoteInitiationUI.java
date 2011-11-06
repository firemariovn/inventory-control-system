package org.ntu.eee.csn.oosd.jvoter.ui;

//This class is used by users to initiate a new vote
//@Author: LU Mukai G1101045F


import java.io.Serializable;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.ntu.eee.csn.oosd.jvoter.model.*;
import org.ntu.eee.csn.oosd.jvoter.util.JVoterProtocol;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;;

public class VoteInitiationUI extends JPanel{
	
	/**
	 * Please use LOGGER to records the vote the user has initiated and 
     * participated.
	 */
	private static Logger LOGGER = Logger.getLogger(VoteInitiationUI.class);
	private JTextField tfVoteName;
	private JTextField tfOption1;
	private JTextField tfOption2;
	private JTextField tfOption3;
	private JTextField tfOption4;
	private JTextArea tfDesc;
	private JRadioButton rdbtnAfterHalfHr;
	private JRadioButton rdbtnAfterOneHr;
	private JRadioButton rdbtnAfterTwoHr;
	private JLabel lblCurrentTime;
	private JList onlineUserlist;
	
	private DefaultListModel lItems= new DefaultListModel();//for the JList	
	private DatagramSocket socket; 
	private Date date; //store the current time
	private ArrayList<Voter> voters; //store all the online user info
	

	public VoteInitiationUI(ArrayList<Voter> v,DatagramSocket socket)
	{
		
		this();
		this.voters= v; 
		this.socket=socket; //get the socket from the mainUI for sending new vote
		
		for(int i = 0; i<voters.size();i++) //scan all user info from the arraylist and add them to the defaultListModel used by JList
		{
			String s = voters.get(i).getHostName();
			String sn = voters.get(i).getInetAddress();
			ListItem item = new ListItem(false,sn,s);
			lItems.addElement(item);

		}
		date =  new Date();
	    lblCurrentTime.setText(date.toString());
	
	}
	
	
	
	public VoteInitiationUI() {
		
		
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(385, 35, 185, 432);
		add(scrollPane);
		
		onlineUserlist = new JList(lItems); //Convert the items of Jlist to checkbox
		MyListCellRenderer mlcr = new MyListCellRenderer();
		onlineUserlist.setCellRenderer(mlcr);
		onlineUserlist.addMouseListener(new CheckListMouseListener()); //add a listener to JList to realize the multiple select without pressing Ctrl
		scrollPane.setViewportView(onlineUserlist);
		
		JLabel lblOnlineUsers = new JLabel("Online Users:");
		lblOnlineUsers.setBounds(385, 8, 114, 26);
		add(lblOnlineUsers);
		
		JLabel lblVoteForm = new JLabel("Initiate A Vote:");
		lblVoteForm.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblVoteForm.setBounds(16, 6, 147, 26);
		add(lblVoteForm);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonPanel.setBounds(6, 424, 369, 43);
		add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton sendButton = new JButton("Send");
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("clicked send");
				
				try
				{
					ArrayList<String> op = new ArrayList<String>();
					op.add(tfOption1.getText());
					op.add(tfOption2.getText());
					op.add(tfOption3.getText());
					op.add(tfOption4.getText());
					Date dt = new Date();
					
					if(rdbtnAfterHalfHr.isSelected()) //add 30 min to the current time
					{
						dt.setTime(date.getTime()+1800000);
						
					}
					else if(rdbtnAfterOneHr.isSelected()) //add 1 hr to the current time
					{
						dt.setTime(date.getTime()+3600000);
					}
					else if(rdbtnAfterTwoHr.isSelected()) //add 2 hr to the current time
					{
						dt.setTime(date.getTime()+7200000);
					}
					System.out.println(dt.toString());
					
					Vote v = new Vote(tfVoteName.getText(),tfDesc.getText(),op,dt,InetAddress.getLocalHost().getHostAddress(),true,false);
					System.out.println("ready to send");
					
					String flag = String.valueOf(JVoterProtocol.NEW_VOTE);
					String data = flag+"|"+v.getName()+"|"+v.getDesc()+"|"+v.getOptions().get(0)+"|"+v.getOptions().get(1)+"|"+v.getOptions().get(2)+"|"+v.getOptions().get(3)+"|"+v.getDeadline().toGMTString()+"|"+v.getInitiatorIP();
					byte[] buff = data.getBytes();
					
		            DatagramPacket packet;
		            InetAddress ip;
		            
		            
				    for(int i =0; i<onlineUserlist.getModel().getSize();i++)
			    	{
				    	ListItem item =(ListItem)onlineUserlist.getModel().getElementAt(i); //get all the selected users' info
				    	if(item.getCheck())
				    	{
					       ip = InetAddress.getByName(item.getIp());
					       System.out.println(ip);
					
					       packet = new DatagramPacket(buff, buff.length,ip,JVoterProtocol.UNICAST_LISTEN_PORT);
			        
			               
					       socket.send(packet);
					       
					       System.out.println(ip);
				     	}
				    }
				    LOGGER.info("The JVoter initiates a vote named:"+v.getName());
				}
				    catch(Exception e)
					{
						System.out.println(e.toString());
					}	
			
				System.out.println("sent");
				
				JOptionPane.showMessageDialog(null, 
		                "You have successfully initiated a vote!", "Congradulations!",JOptionPane.INFORMATION_MESSAGE);  
				
				Component cmp= arg0.getComponent();  //close this windows
				  while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
				  cmp = cmp.getParent();
				  }
				((JFrame)cmp).dispose();
				
				}
				
				
				
				
			
		});
		buttonPanel.add(sendButton);
		
		JButton cancelButton = new JButton("Cancel");  //close this windows
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//socket.close();
				Component cmp= arg0.getComponent();
				  while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
				  cmp = cmp.getParent();
				  }
				((JFrame)cmp).dispose();
			}
		});
		buttonPanel.add(cancelButton);
		
		tfDesc = new JTextArea();
		tfDesc.setBounds(92, 82, 251, 114);
		add(tfDesc);
		tfDesc.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(16, 87, 77, 14);
		add(lblDescription);
		
		tfOption1 = new JTextField();
		tfOption1.setBounds(92, 209, 179, 20);
		add(tfOption1);
		tfOption1.setColumns(10);
		
		tfOption2 = new JTextField();
		tfOption2.setBounds(92, 240, 179, 20);
		add(tfOption2);
		tfOption2.setColumns(10);
		
		tfOption3 = new JTextField();
		tfOption3.setBounds(92, 271, 179, 20);
		add(tfOption3);
		tfOption3.setColumns(10);
		
		tfOption4 = new JTextField();
		tfOption4.setBounds(92, 302, 179, 20);
		add(tfOption4);
		tfOption4.setColumns(10);
		
		
		tfVoteName = new JTextField();
		tfVoteName.setBounds(92, 49, 251, 20);
		add(tfVoteName);
		tfVoteName.setColumns(10);
		
		JLabel lblOption1 = new JLabel("Option 1:");
		lblOption1.setBounds(16, 212, 77, 14);
		add(lblOption1);
		
		JLabel lblOpion2 = new JLabel("Option 2:");
		lblOpion2.setBounds(16, 243, 77, 14);
		add(lblOpion2);
		
		JLabel lblOption3 = new JLabel("Option 3:");
		lblOption3.setBounds(16, 274, 77, 14);
		add(lblOption3);
		
		JLabel lblOption4 = new JLabel("Option 4:");
		lblOption4.setBounds(16, 305, 77, 14);
		add(lblOption4);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(16, 375, 77, 14);
		add(lblDeadline);
		
		JLabel lblVoteName = new JLabel("Vote Name:");
		lblVoteName.setBounds(16, 52, 77, 14);
		add(lblVoteName);
		
		rdbtnAfterHalfHr = new JRadioButton("After 30 min");
		rdbtnAfterHalfHr.setSelected(true);
		rdbtnAfterHalfHr.setBounds(92, 371, 100, 23);
		add(rdbtnAfterHalfHr);
		
		
		rdbtnAfterOneHr = new JRadioButton("After 1 hr");
		rdbtnAfterOneHr.setBounds(194, 371, 91, 23);
		add(rdbtnAfterOneHr);
		
		rdbtnAfterTwoHr = new JRadioButton("After 2 hr");
		rdbtnAfterTwoHr.setBounds(281, 371, 84, 23);
		add(rdbtnAfterTwoHr);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnAfterHalfHr);
		bg.add(rdbtnAfterOneHr);
		bg.add(rdbtnAfterTwoHr);
		
		lblCurrentTime = new JLabel("CurrentTime");
		lblCurrentTime.setBounds(102, 350, 241, 14);
		
		add(lblCurrentTime);
		
		JLabel lblC2 = new JLabel("Current Time:");
		lblC2.setBounds(16, 350, 77, 14);
		add(lblC2);
		
		
	}
}

//Convert the item of JList into checkBox
 class MyListCellRenderer extends JCheckBox implements ListCellRenderer {  
	  
    //private static final long serialVersionUID = -2575287177726702542L;  
  
    public MyListCellRenderer() {  
        super();  
        setOpaque(true);  
    }  
  
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {  
       
        ListItem item = (ListItem)value;
    	setText(item.getHostname()+'/'+item.getIp());  //set the text of checkbox to hostname/IP
        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());  
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());  
        this.setSelected(item.getCheck());  //using the property of the ListItem to store the status of check, thereby realizing the multiple select 
        return this;  
    }  
  
} 
 
class ListItem {
		private boolean check;  //using the property of the ListItem to store the status of check, thereby realizing the multiple select 
		private String ip;
		private String hostname;
		
		public ListItem()
		{
			
		}
		
		public ListItem(boolean check, String add,String nm) {
			this.check = check;
			this.ip = add;
			this.hostname=nm;
		}
		

		public boolean getCheck() {
			return check;
		}
		
		public void setCheck(boolean check) {
			this.check = check;
		}
		
		public String getIp() {
			return ip;
		}
		
		public void setIp(String text) {
			this.ip = text;
		}
		
		public String getHostname() {
			return hostname;
		}
		
		public void setHostname(String text) {
			this.hostname = text;
		}
	}
 
 class CheckListMouseListener extends MouseAdapter { 
    public void mousePressed(MouseEvent e) {
        JList list = (JList) e.getSource();
        int index = list.locationToIndex(e.getPoint());
        ListItem item = (ListItem)list.getModel().getElementAt(index);
        item.setCheck(! item.getCheck()); 
        Rectangle rect = list.getCellBounds(index, index); //repaint the JList and get the check status of Checkbox from its object's property 
        list.repaint(rect);
    }
}
