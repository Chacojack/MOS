package com.app.user.frame;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import com.manager.MMessage;
import com.manager.MUser;
import com.manager.MUserManager;
import com.manager.MUserProcess;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MUserSendMessageDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //ÏÔÊ¾ÆÁµÄ¸ß
	
	private MUser userProcess ;
	private JTextArea message_textArea;
	
	public MUserSendMessageDialog(JFrame owner,MUser process) {
		this.userProcess = process;
		setTitle("\u53D1\u9001\u6D88\u606F");
		this.setSize(240, 248);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		
		this.setResizable(false);
		this.setModal(true);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165\u8981\u53D1\u9001\u7684\u6D88\u606F\uFF1A");
		label.setBounds(10, 10, 177, 15);
		getContentPane().add(label);
		
		message_textArea = new JTextArea();
		message_textArea.setBounds(10, 35, 214, 143);
		message_textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		getContentPane().add(message_textArea);
		
		JButton send_button = new JButton("\u53D1\u9001");
		send_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MMessage message = new MMessage();
				message.setTargetTag(userProcess.getUserId());
				message.setObject(message_textArea.getText());
				MUserManager.getUserManager().getForegroundUser().sendMessage(message);
				dispose();
			}
		});
		send_button.setBounds(131, 188, 93, 23);
		getContentPane().add(send_button);
		this.setVisible(true);
	}
}
