package org.ntu.eee.csn.oosd.jvoter.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class VoteInitiationUI extends JPanel{
	private JTextField tfVoteName;
	private JTextField tfOption1;
	private JTextField tfOption2;
	private JTextField tfOption3;
	private JTextField tfOption4;
	private JTextField tfOption5;
	private JTextField tfDesc;
	public VoteInitiationUI() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 35, 133, 509);
		add(scrollPane);
		
		JList onlineUserlist = new JList();
		scrollPane.setViewportView(onlineUserlist);
		
		JLabel lblOnlineUsers = new JLabel("Online Users:");
		lblOnlineUsers.setBounds(317, 6, 114, 26);
		add(lblOnlineUsers);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(6, 36, 300, 238);
		add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblVoteName = new JLabel("Vote Name:");
		panel.add(lblVoteName, "2, 2");
		
		tfVoteName = new JTextField();
		panel.add(tfVoteName, "6, 2, fill, default");
		tfVoteName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		panel.add(lblDescription, "2, 4");
		
		tfDesc = new JTextField();
		panel.add(tfDesc, "6, 4, fill, default");
		tfDesc.setColumns(10);
		
		JLabel lblOption1 = new JLabel("Option 1:");
		panel.add(lblOption1, "2, 6");
		
		tfOption1 = new JTextField();
		panel.add(tfOption1, "6, 6, fill, default");
		tfOption1.setColumns(10);
		
		JLabel lblOpion2 = new JLabel("Option 2:");
		panel.add(lblOpion2, "2, 8");
		
		tfOption2 = new JTextField();
		panel.add(tfOption2, "6, 8, fill, default");
		tfOption2.setColumns(10);
		
		JLabel lblOption3 = new JLabel("Option 3:");
		panel.add(lblOption3, "2, 10");
		
		tfOption3 = new JTextField();
		panel.add(tfOption3, "6, 10, fill, default");
		tfOption3.setColumns(10);
		
		JLabel lblOption4 = new JLabel("Option 4:");
		panel.add(lblOption4, "2, 12");
		
		tfOption4 = new JTextField();
		panel.add(tfOption4, "6, 12, fill, default");
		tfOption4.setColumns(10);
		
		JLabel lblOption5 = new JLabel("Option 5:");
		panel.add(lblOption5, "2, 14");
		
		tfOption5 = new JTextField();
		panel.add(tfOption5, "6, 14, fill, default");
		tfOption5.setColumns(10);
		
		JLabel lblVoteForm = new JLabel("Initiate A Vote:");
		lblVoteForm.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblVoteForm.setBounds(16, 6, 147, 26);
		add(lblVoteForm);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonPanel.setBounds(6, 280, 300, 43);
		add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton sendButton = new JButton("Send");
		buttonPanel.add(sendButton);
		
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
	}
}
