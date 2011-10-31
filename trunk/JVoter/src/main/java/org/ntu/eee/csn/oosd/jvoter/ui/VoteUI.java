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

public class VoteUI extends JPanel{
	private JTextField tfVoteName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public VoteUI() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 35, 133, 509);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
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
		
		textField_5 = new JTextField();
		panel.add(textField_5, "6, 4, fill, default");
		textField_5.setColumns(10);
		
		JLabel lblOption = new JLabel("Option 1:");
		panel.add(lblOption, "2, 6");
		
		textField = new JTextField();
		panel.add(textField, "6, 6, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Option 2:");
		panel.add(lblNewLabel, "2, 8");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "6, 8, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Option 3:");
		panel.add(lblNewLabel_1, "2, 10");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "6, 10, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblOption_1 = new JLabel("Option 4:");
		panel.add(lblOption_1, "2, 12");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "6, 12, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblOption_2 = new JLabel("Option 5:");
		panel.add(lblOption_2, "2, 14");
		
		textField_4 = new JTextField();
		panel.add(textField_4, "6, 14, fill, default");
		textField_4.setColumns(10);
		
		JLabel lblVoteForm = new JLabel("Vote Form:");
		lblVoteForm.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblVoteForm.setBounds(16, 6, 133, 26);
		add(lblVoteForm);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonPanel.setBounds(6, 280, 300, 43);
		add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Send");
		buttonPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		buttonPanel.add(btnNewButton_1);
	}
}
