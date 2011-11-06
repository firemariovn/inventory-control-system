package org.ntu.eee.csn.oosd.jvoter.ui;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JList;

import org.ntu.eee.csn.oosd.jvoter.model.VoteResult;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JButton;

public class VoteView extends JFrame {
	
	private DefaultListModel lItemsInitiated= new DefaultListModel();
	private DefaultListModel lItemsParticipated= new DefaultListModel();
	private ArrayList<VoteResult> voteInitiated;
	private ArrayList<VoteResult> voteParticipated;
	private JList listparticipated;
	private JTabbedPane tabbedPane;
	private JList listInitiated;
	
	public VoteView(ArrayList<VoteResult> voteInitiated,ArrayList<VoteResult> voteParticipated)
	{
		this();
		this.voteInitiated=voteInitiated;
		this.voteParticipated=voteParticipated;
		
		for(int i = 0; i<voteInitiated.size();i++) //display all the unanswered votes
		{
			  lItemsInitiated.addElement(voteInitiated.get(i));	
	    }
		
		for(int i = 0; i<voteParticipated.size();i++) //display all the unanswered votes
		{
			  lItemsParticipated.addElement(voteParticipated.get(i));	
	    }
	
	}
	
	public VoteView() {
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		listInitiated = new JList(lItemsInitiated);
		listInitiated.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listInitiated.setCellRenderer(new CheckListRenderForVotResult());
		
		tabbedPane.addTab("Votes initiated by me", null, listInitiated, null);
		
		listparticipated = new JList(lItemsParticipated);
		listparticipated.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listparticipated.setCellRenderer(new CheckListRenderForVotResult());
		
		tabbedPane.addTab("Votes I have  participated  in", null, listparticipated, null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnViewIt = new JButton("View it");
		btnViewIt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				 
				 switch(tabbedPane.getSelectedIndex())
				 { 
				 case 0:
					 VoteResult v =(VoteResult)listInitiated.getModel().getElementAt(listInitiated.getSelectedIndex());
					 VoteResultChartUI chart = new VoteResultChartUI(v);
					 chart.pack();
					 chart.setVisible(true);
					 break;
					 
				 case 1:
					  VoteResult v2 =(VoteResult)listparticipated.getModel().getElementAt(listparticipated.getSelectedIndex());
					  VoteResultSelectionUI vlUI = new VoteResultSelectionUI(v2);
		              JFrame jfVI = new JFrame();
		              jfVI.getContentPane().add(vlUI);
		              jfVI.setBounds(0, 0, 465,450);
		              jfVI.setResizable(false);
		              jfVI.setVisible(true);
		              break;
		              default:
		            	  break;
				
				}
				
				
			}
		});
		panel.add(btnViewIt);
		
		JButton btnCancel = new JButton("Cancel");
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
		panel.add(btnCancel);
	}

}

class CheckListRenderForVotResult extends JCheckBox  implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
        VoteResult item = (VoteResult)value;
        
        this.setText(item.getInitiatorIP()+": "+item.getName());
        this.setFont(list.getFont());
        this.setSelected(isSelected);
        return this;
	}

}
