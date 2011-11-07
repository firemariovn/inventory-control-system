package org.ntu.eee.csn.oosd.jvoter.ui;

//This class is used by users to view all the unanswered votes
//@Author: LU Mukai G1101045F

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.border.EtchedBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.ntu.eee.csn.oosd.jvoter.model.Vote;
import org.ntu.eee.csn.oosd.jvoter.model.VoteReply;
import org.ntu.eee.csn.oosd.jvoter.model.VoteResult;
import org.ntu.eee.csn.oosd.jvoter.util.JVoterProtocol;
import java.awt.SystemColor;

public class VoteResultSelectionUI extends JPanel{
	
	private static Logger LOGGER = Logger.getLogger(VoteResultSelectionUI.class);
	
	private DatagramSocket socket;
	private DefaultListModel lItems= new DefaultListModel();
	private JLabel lblVoteName;
	private JLabel lblInitiator;
	private JLabel lblDeadline;
	private JTextArea tAreaDiscription;
	private JList listOptions;
	private Vote vote;
	private DefaultListModel items;
	private int index;
	private JButton unRepliedVotesButton;
	private ArrayList<Vote> votes;
	private JPanel panel_1;
	
	public  VoteResultSelectionUI(VoteResult v)
	{
		this();
		lblVoteName.setText(v.getName());
		
		lblInitiator.setText(v.getInitiator()+"/"+v.getInitiatorIP());
		tAreaDiscription.setText(v.getDesc());
		
		ArrayList<String> ops= v.getOptions(); //display the options
		for(int i =0; i<ops.size();i++)
		{
			lItems.addElement(ops.get(i));
		}
		lblDeadline.setText(v.getDeadline().toString());
		panel_1.setVisible(false);
		listOptions.setEnabled(false);
		
		ArrayList<Integer> result=v.getResult();
		
		for(int i =0; i<result.size();i++)
		{
			System.out.println("luke: "+result.get(i));
			if(result.get(i)==1)
			{
				
				listOptions.setSelectedIndex(i);
			}
		}
	}
	
	public  VoteResultSelectionUI(Vote v, DatagramSocket socket,DefaultListModel items,int index,JButton unRepliedVotesButton,ArrayList<Vote> votes)
	{
		this();
		this.socket = socket;
		this.vote=v;
		this.items=items;
		this.index=index; //get the index which is selected in VoteListUI and also is the index of this vote in the ArrayList
		this.unRepliedVotesButton =unRepliedVotesButton;
		this.votes=votes;
		
		lblVoteName.setText(v.getName());
		lblInitiator.setText(v.getInitiator()+"/"+v.getInitiatorIP());
		tAreaDiscription.setText(v.getDesc());
		
		ArrayList<String> ops= v.getOptions(); //display the options
		for(int i =0; i<ops.size();i++)
		{
			lItems.addElement(ops.get(i));
		}
		lblDeadline.setText(v.getDeadline().toString());
		
		
		
	}
	
	public VoteResultSelectionUI() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(6, 6, 438, 46);
		add(panel);
		
		lblVoteName = new JLabel("Vote Name: NTU or NUS ?");
		panel.add(lblVoteName);
		lblVoteName.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(20, 401, 424, 37);
		add(panel_1);
		
		JButton voteButton = new JButton("Vote it!");
		voteButton.setBackground(Color.WHITE);
		voteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
					if(votes.get(index).getVoteID().equals(vote.getVoteID()))
					{						
						try
						{
						    
						    DatagramPacket packet;
						    InetAddress ip = InetAddress.getByName(vote.getInitiatorIP());//get the Initiator's IP
				            String flag =String.valueOf( JVoterProtocol.REPLY_VOTE); //set the flag
				            String op =String.valueOf( listOptions.getSelectedIndex()); //get the choice index from the JList
				            String data = flag+"|"+vote.getVoteID()+"|"+op +"|"+InetAddress.getLocalHost().getHostAddress();
							byte[] buff = data.getBytes();
							packet = new DatagramPacket(buff, buff.length,ip,JVoterProtocol.UNICAST_LISTEN_PORT);
					        socket.send(packet);
					        vote.updateReply();
					        VoteReply vr = new VoteReply(vote.getVoteID(),listOptions.getSelectedIndex(), InetAddress.getLocalHost().getHostAddress());
					        vr.add();
						}
						catch(Exception e)
						{
							System.out.println(e.toString());
						}
						
						System.out.println("sent");
						
						JOptionPane.showMessageDialog(null, 
				                "You have successfully replied a vote!", "Congradulations!",JOptionPane.INFORMATION_MESSAGE);
						  
						items.remove(index); //remove the item from the VoteListUI after successfully replying it
						votes.remove(index); //remove the item from the ArrayList after successfully replying it
						unRepliedVotesButton.setText("Unreplied Votes["+votes.size()+"]"); //reset the text of the unRepliedVotesButton in MainUI after successfully replying it
						LOGGER.info("The JVoter has repied a vote named: "+vote.getName()+" to "+vote.getInitiatorIP());
						
						Component cmp= arg0.getComponent(); //close the windows
						  while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
						  cmp = cmp.getParent();
						  }
						((JFrame)cmp).dispose();
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, 
				                "This vote has already been canceld!", "Canceled!",JOptionPane.INFORMATION_MESSAGE);
					}
					
				
			
				
				
				
			}
		}
				
		);
		panel_1.add(voteButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.WHITE);
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Component cmp= arg0.getComponent();
				  while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
				  cmp = cmp.getParent();
				  }
				((JFrame)cmp).dispose();
			}
		});
		panel_1.add(cancelButton);
		
		JLabel lbl1 = new JLabel("Initiator:");
		lbl1.setBounds(16, 63, 46, 14);
		add(lbl1);
		
		JLabel lblDiscription = new JLabel("Discription:");
		lblDiscription.setBounds(16, 88, 64, 14);
		add(lblDiscription);
		
		lblInitiator = new JLabel("Host/192.168.1.1");
		lblInitiator.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInitiator.setBounds(72, 63, 338, 14);
		add(lblInitiator);
		
		tAreaDiscription = new JTextArea();
		tAreaDiscription.setBackground(Color.WHITE);
		tAreaDiscription.setEditable(false);
		tAreaDiscription.setBounds(16, 109, 424, 81);
		add(tAreaDiscription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 202, 424, 163);
		add(scrollPane);
		
		listOptions = new JList(lItems);
		listOptions.setBackground(Color.WHITE);
		listOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOptions.setCellRenderer(new CheckListRender());
		scrollPane.setViewportView(listOptions);
		
		lblDeadline = new JLabel("Host/192.168.1.1");
		lblDeadline.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeadline.setBounds(100, 376, 310, 14);
		add(lblDeadline);
		
		JLabel lbl2 = new JLabel("Deadline:");
		lbl2.setBounds(16, 376, 74, 14);
		add(lbl2);
		
		
		
		
		
		
		
		
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}

class CheckListRender extends JCheckBox  implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

        this.setText(value.toString());
        this.setFont(list.getFont());
        this.setSelected(isSelected);
        return this;
	}

}
