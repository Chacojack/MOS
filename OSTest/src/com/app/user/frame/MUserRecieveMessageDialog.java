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

public class MUserRecieveMessageDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //ÏÔÊ¾ÆÁµÄ¸ß
	
	private JTextArea send_textArea;
	private MMessage message ;
	
	public MUserRecieveMessageDialog(JFrame owner,MMessage message ) {
		this.message = message;
		MUserManager.getUserManager().getForegroundUser().removeMessage(message);
		setTitle(MUserManager.getUserManager().getName(message.getSourceTag()));
		this.setSize(352, 344);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		
		this.setResizable(false);
		this.setModal(true);
		getContentPane().setLayout(null);
		
		JLabel back_label = new JLabel("\u56DE\u590D\uFF1A");
		back_label.setBounds(10, 156, 88, 15);
		getContentPane().add(back_label);
		
		send_textArea = new JTextArea();
		send_textArea.setBounds(10, 181, 326, 92);
		send_textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		getContentPane().add(send_textArea);
		
		JButton send_button = new JButton("\u53D1\u9001");
		send_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MMessage message = new MMessage();
				message.setTargetTag(MUserRecieveMessageDialog.this.message.getSourceTag());
				message.setObject(send_textArea.getText());
				MUserManager.getUserManager().getForegroundUser().sendMessage(message);
				dispose();
			}
		});
		send_button.setBounds(248, 283, 88, 23);
		getContentPane().add(send_button);
		
		JLabel source_label = new JLabel(MUserManager.getUserManager().getName(message.getSourceTag())+":");
		source_label.setBounds(10, 10, 326, 15);
		getContentPane().add(source_label);
		
		JTextArea recieve_textArea = new JTextArea();
		recieve_textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		recieve_textArea.setBounds(10, 35, 326, 111);
		recieve_textArea.setText((String)message.object);
		getContentPane().add(recieve_textArea);
		this.setVisible(true);
	}
}
