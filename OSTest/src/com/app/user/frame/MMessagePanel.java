package com.app.user.frame;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import com.app.launcher.frame.MOSFrame;
import com.manager.MMessage;
import com.manager.MUserManager;

public class MMessagePanel extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	private MMessageListFrame userListFrame ;
	
	private MMessage message ;
	
	private MOSFrame owner ;
	
	/**
	 * 
	 * @param userId
	 * @param message
	 * @param owner
	 * @param userListFrame
	 */
	public MMessagePanel(MMessage message, MOSFrame owner , MMessageListFrame userListFrame){
		this.userListFrame = userListFrame;
		this.owner = owner;
		this.message = message;
		this.setSize(230,50);
		this.addMouseListener(this);
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel name_label = new JLabel("");
		name_label.setFont(new Font("微软雅黑", Font.BOLD, 12));
		name_label.setBounds(20, 10, 151, 16);
		add(name_label);
		name_label.setText(MUserManager.getUserManager().getName(message.getSourceTag()));
		
		JLabel mail_label = new JLabel(new ImageIcon("res\\user\\draw\\mail_16.png")
		,JLabel.CENTER);
		mail_label.setBounds(181, 10, 39, 30);
		add(mail_label);
		
		JLabel message_label = new JLabel(message.object.toString());
		message_label.setForeground(Color.DARK_GRAY);
		message_label.setFont(new Font("微软雅黑", Font.BOLD, 10));
		message_label.setBounds(20, 28, 151, 12);
		add(message_label);
	}
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
			//TODO 添加查看消息的窗口
			new MUserRecieveMessageDialog(userListFrame, message);
			userListFrame.initMessageList();
			owner.initForegroundUser();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBackground(Color.GRAY);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBackground(Color.LIGHT_GRAY);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
