package org.ntu.eee.csn.oosd.jvoter.ui;

//This class is used by users to view all the unanswered votes
//@Author: LU Mukai G1101045F

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Font;
import java.net.DatagramSocket;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JList;

import org.ntu.eee.csn.oosd.jvoter.model.Vote;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;

public class VoteListUI extends JPanel{
	
	private ArrayList<Vote> votes;
	private DefaultListModel lItems= new DefaultListModel();
	private JList voteList;
	private DatagramSocket socket;
	private JButton unRepliedVotesButton;
	
	public VoteListUI(ArrayList<Vote> v,DatagramSocket socket,JButton unRepliedVotesButton)
	{
		this();
		this.socket=socket;
		this.unRepliedVotesButton =unRepliedVotesButton;
	     votes = v;
	    for(int i = 0; i<votes.size();i++) //display all the unanswered votes
		{
			lItems.addElement(votes.get(i));
			
		}
	}
	public VoteListUI() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblVoteList = new JLabel("Vote List :");
		lblVoteList.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblVoteList.setHorizontalAlignment(SwingConstants.LEFT);
		lblVoteList.setBounds(6, 6, 438, 26);
		add(lblVoteList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 44, 434, 401);
		add(scrollPane);
		
		voteList = new JList(lItems);
		voteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		voteList.setCellRenderer(new CheckListItemRender());
		
		scrollPane.setViewportView(voteList);
		
		JButton btnReply = new JButton("Reply");
		btnReply.setBackground(Color.WHITE);
		btnReply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 int index = voteList.getSelectedIndex();
				 Vote v =(Vote)voteList.getModel().getElementAt(index);
				 
						if(votes.get(index).getVoteID().equals(v.getVoteID()))
						{
							  
				              VoteResultSelectionUI vlUI = new VoteResultSelectionUI(v,socket,lItems,index,unRepliedVotesButton,votes);
	                          JFrame jfVI = new JFrame();
	                          jfVI.getContentPane().add(vlUI);
	                          jfVI.setBounds(0, 0, 465,520);
	                          jfVI.setResizable(false);
	                          jfVI.setVisible(true);
						}
					
						else
					    {
						JOptionPane.showMessageDialog(null, 
				                "This vote has already been canceld!", "Canceled!",JOptionPane.INFORMATION_MESSAGE);
					   }
					
				
			}
		});
		btnReply.setBounds(228, 456, 89, 23);
		add(btnReply);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.WHITE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Component cmp= arg0.getComponent();
				  while(!(cmp instanceof JFrame ) || cmp.getParent() !=null ){
				  cmp = cmp.getParent();
				  }
				((JFrame)cmp).dispose();
			}
		});
		btnCancel.setBounds(327, 456, 89, 23);
		add(btnCancel);
	}
}


//Convert the item of JList into checkBox
class CheckListItemRender extends JCheckBox  implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
        Vote item = (Vote)value;
        
        this.setText(item.getInitiatorIP()+": "+item.getName());
        this.setFont(list.getFont());
        this.setSelected(isSelected);
        return this;
	}

}