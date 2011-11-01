package org.ntu.eee.csn.oosd.jvoter.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

/**
 * This is the Main Frame of JVoter in UI Layer
 *  
 * @author WangDing
 *
 */
public class MainUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 449, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 47, 437, 412);
		frame.getContentPane().add(scrollPane);
		
		JList onlineUserList = new JList();
		scrollPane.setViewportView(onlineUserList);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(6, 461, 437, 102);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton initialiteVoteButton = new JButton("Initiate a Vote");
		panel.add(initialiteVoteButton);
		
		JButton unRepliedVotesButton = new JButton("Unreplied Votes[0]");
		panel.add(unRepliedVotesButton);
		
		JButton viewAllButton = new JButton("View All Votes");
		panel.add(viewAllButton);
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topPanel.setBounds(6, 6, 437, 39);
		frame.getContentPane().add(topPanel);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblOnlineUsers = new JLabel("Online Users:");
		topPanel.add(lblOnlineUsers);
	
	
	}
}
