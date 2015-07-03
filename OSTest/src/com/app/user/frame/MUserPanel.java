package com.app.user.frame;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import com.app.launcher.frame.MOSFrame;
import com.manager.IMessageRecieveListener;
import com.manager.MMessage;
import com.manager.MUserManager;
import com.manager.MUserProcess;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;

public class MUserPanel extends JPanel implements IMessageRecieveListener,MouseListener{

	private static final long serialVersionUID = 1L;
	
	private MUserProcess userProcess ;
	private JLabel unreadMessage_label;
	
	public MUserPanel(MUserProcess userProcess , final MOSFrame owner , final MUserListFrame userListFrame ){
		this.userProcess = userProcess;
		userProcess.addMessageRecieveListener(this);
		this.setSize(230,50);
		this.addMouseListener(this);
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel name_label = new JLabel("");
		name_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2){
					MUserManager.getUserManager().setForegroundUser(MUserPanel.this.userProcess.getUser().getUserId());
					owner.initForegroundUser();
				}
			}
		});
		name_label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 17));
		name_label.setBounds(20, 10, 151, 30);
		add(name_label);
		name_label.setText(userProcess.getUser().getUserName());
		
		JLabel mail_label = new JLabel(new ImageIcon("res\\user\\draw\\mail_16.png")
		,JLabel.CENTER);
		mail_label.setBounds(181, 10, 39, 30);
		add(mail_label);
		mail_label.addMouseListener(new MouseAdapter() { 
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new MUserSendMessageDialog(owner,MUserPanel.this.userProcess.getUser());
				userListFrame.initUserList();
			}
		});
		
		unreadMessage_label = new JLabel();
		unreadMessage_label.setForeground(Color.RED);
		unreadMessage_label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		unreadMessage_label.setHorizontalAlignment(SwingConstants.RIGHT);
		unreadMessage_label.setBounds(139, 10, 39, 30);
		add(unreadMessage_label);
		setUnreadMessageNumber(userProcess.getMessageList().size());
	}
	
	public void setUnreadMessageNumber(int num){
		unreadMessage_label.setText(num+"");
	}
	
	

	@Override
	public void onRecieveMessage(MMessage message) {
		this.setUnreadMessageNumber(userProcess.getMessageList().size());
	}

	public MUserProcess getUserProcess() {
		return userProcess;
	}

	public void setUserProcess(MUserProcess userProcess) {
		this.userProcess = userProcess;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
