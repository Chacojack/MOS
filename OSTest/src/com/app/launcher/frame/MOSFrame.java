package com.app.launcher.frame;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.app.filesystem.frame.FileSystemFrame;
import com.app.process.frame.ProcessSystemFrame;
import com.app.user.frame.MMessageListFrame;
import com.app.user.frame.MUserInfoFrame;
import com.app.user.frame.MUserListFrame;
import com.manager.MUserManager;

import my.swing.MyJButton;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;


public class MOSFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //显示屏的高
	
	private JLabel users_label;
	private JLabel foregroundUser_label;
	private JLabel unreadNum_label;
	private JLabel mail_label;
	
	
	public MOSFrame() {
		this.setSize(Screen_width, Screen_height);
		this.setLocation(0,0);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setResizable(false);                   //设置为不可更改窗口大小
		getContentPane().setLayout(null);
		
		JPanel main_panel = new JPanel(){
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("res/launcher/draw/background.png");
				g.drawImage(img.getImage(), 0, 0, this.getWidth(),this.getHeight(),  null);
			}
		};
		main_panel.setBounds(0, 0, 1366, 768);
		getContentPane().add(main_panel);
		main_panel.setLayout(null);
		
		ImageIcon img = new ImageIcon("res/launcher/draw/my_computer_72.png");
		MyJButton myComputer_button = new MyJButton(img);
		myComputer_button.setBounds(10, 10, 72, 72);
		myComputer_button.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					new FileSystemFrame();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});
		main_panel.add(myComputer_button);
		
		ImageIcon img1 = new ImageIcon("res/launcher/draw/my_process_72.png");
		MyJButton myProcess_button = new MyJButton(img1);
		myProcess_button.setBounds(10, 140, 72, 72);
		myProcess_button.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					new ProcessSystemFrame();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});;
		main_panel.add(myProcess_button);
		
		users_label = new JLabel("");
		users_label.setHorizontalAlignment(SwingConstants.CENTER);
		users_label.setIcon(new ImageIcon("res\\launcher\\draw\\users_out_16.png"));
		users_label.setBounds(1334, 0, 32, 32);
		main_panel.add(users_label);
		users_label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				users_label.setIcon(new ImageIcon("res\\launcher\\draw\\users_out_16.png"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				users_label.setIcon(new ImageIcon("res\\launcher\\draw\\users_in_16.png"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new MUserListFrame(MOSFrame.this);
			}
		});
		
		
		foregroundUser_label = new JLabel();
		foregroundUser_label.setHorizontalAlignment(SwingConstants.RIGHT);
		foregroundUser_label.setFont(new Font("微软雅黑", Font.BOLD, 16));
		foregroundUser_label.setBounds(1141, 0, 130, 32);
		main_panel.add(foregroundUser_label);
		foregroundUser_label.setText(MUserManager.getUserManager().getForegroundUser().getUser().getUserName());
		
		mail_label = new JLabel("");
		mail_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mail_label.setIcon(new ImageIcon("res\\launcher\\draw\\email_in_16.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mail_label.setIcon(new ImageIcon("res\\launcher\\draw\\email_out_16.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new MMessageListFrame(MOSFrame.this);
			}
		});
		mail_label.setHorizontalAlignment(SwingConstants.CENTER);
		mail_label.setIcon(new ImageIcon("res\\launcher\\draw\\email_out_16.png"));
		mail_label.setBounds(1294, 0, 45, 32);
		main_panel.add(mail_label);
		
		unreadNum_label = new JLabel("");
		unreadNum_label.setFont(new Font("微软雅黑", Font.BOLD, 16));
		unreadNum_label.setHorizontalAlignment(SwingConstants.RIGHT);
		unreadNum_label.setForeground(new Color(0xD5,0,0));
		unreadNum_label.setBounds(1273, 0, 24, 32);
		main_panel.add(unreadNum_label);
		
		
		foregroundUser_label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				foregroundUser_label.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				foregroundUser_label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new MUserInfoFrame(MUserManager.getUserManager().getForegroundUser().getUser());
			}
		});
		initForegroundUser();
		this.setVisible(true);
	}
	
	/**
	 * 显示前台用户的信息
	 */
	public void initForegroundUser(){
		foregroundUser_label.setText(MUserManager.getUserManager().getForegroundUser().getUser().getUserName());
		unreadNum_label.setText(MUserManager.getUserManager().getForegroundUser().getMessageList().size()+"");
	}
	
	
	
}
