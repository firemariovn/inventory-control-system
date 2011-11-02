package org.ntu.eee.csn.oosd.jvoter.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class VoteListUI extends JPanel{
	public VoteListUI() {
		setLayout(null);
		
		JLabel lblVoteList = new JLabel("Vote List :");
		lblVoteList.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblVoteList.setHorizontalAlignment(SwingConstants.LEFT);
		lblVoteList.setBounds(6, 6, 438, 26);
		add(lblVoteList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 44, 418, 453);
		add(scrollPane);
		
		JList voteList = new JList();
		scrollPane.setViewportView(voteList);
	}
}
