package com.app.user.frame;

import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

import com.app.launcher.frame.MOSFrame;
import com.manager.MMessage;
import com.manager.MUserManager;
import java.util.List;
import javax.swing.ScrollPaneConstants;

public class MMessageListFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //ÏÔÊ¾ÆÁµÄ¸ß
	
	private JPanel panel;
	
	private MOSFrame oFrame;

	public MMessageListFrame(MOSFrame owner){
		oFrame = owner;
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\launcher\\draw\\email_in_16.png"));
		setTitle("\u6D88\u606F\u5217\u8868");
		this.setSize(240, 382);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		
		this.setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		initMessageList();
		this.setVisible(true);
	}
	
	
	public void initMessageList(){
		panel.removeAll();
		List<MMessage> messageList = MUserManager.getUserManager().getForegroundUser().getMessageList();
		int height = 0;
		for(int i=0;i<messageList.size();i++){
			MMessagePanel mUserPanel = new MMessagePanel(messageList.get(i), oFrame, MMessageListFrame.this);
			mUserPanel.setLocation(0,mUserPanel.getHeight()*i);
			height = mUserPanel.getHeight();
			panel.add(mUserPanel);
		}
		panel.setSize(panel.getWidth(), height*messageList.size());
		panel.repaint();
	}
	
	
}













