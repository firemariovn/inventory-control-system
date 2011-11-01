package org.ntu.eee.csn.oosd.jvoter.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class VoteResultSelectionUI extends JPanel{
	public VoteResultSelectionUI() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(6, 6, 438, 46);
		add(panel);
		
		JLabel lblVoteName = new JLabel("Vote Name: NTU or NUS ?");
		panel.add(lblVoteName);
		lblVoteName.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(6, 457, 438, 66);
		add(panel_1);
		
		JButton voteButton = new JButton("Vote it!");
		panel_1.add(voteButton);
		
		JButton cancelButton = new JButton("Cancel");
		panel_1.add(cancelButton);
		
		JLabel lblNewLabel = new JLabel("Description:");
		lblNewLabel.setBounds(67, 77, 162, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Accroding to a vote, generate option items dynamically");
		lblNewLabel_1.setBounds(67, 299, 356, 16);
		add(lblNewLabel_1);
		
		JTextArea txtrAGreatGlobal = new JTextArea();
		txtrAGreatGlobal.setWrapStyleWord(true);
		txtrAGreatGlobal.setLineWrap(true);
		txtrAGreatGlobal.setText("A great global university founded on science and technology. Nurturing creative and entrepreneurial leaders through a broad education in diverse disciplines.");
		txtrAGreatGlobal.setBounds(6, 112, 438, 132);
		add(txtrAGreatGlobal);
	}
}
