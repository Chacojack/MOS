package com.app.launcher.frame;

import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import com.manager.MUser;
import com.manager.MUserManager;
import com.tool.security.md5.MD5;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserLoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //显示屏的高
	private JTextField id_textField;
	private JPasswordField passwordField;
	
	public UserLoginFrame() {
		this.setSize(Screen_width, Screen_height);
		this.setLocation(0,0);
		this.setUndecorated(true);
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
		
		JLabel id_label = new JLabel("\u8D26\u53F7\uFF1A");
		id_label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		id_label.setBounds(590, 414, 54, 15);
		main_panel.add(id_label);
		
		id_textField = new JTextField();
		id_textField.setBounds(637, 411, 130, 21);
		id_textField.setBackground(null);
		id_textField.setText("administrator");
		main_panel.add(id_textField);
		id_textField.setColumns(10);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(590, 452, 54, 15);
		main_panel.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(null);
		passwordField.setBounds(637, 449, 130, 21);
		passwordField.requestFocus();
		main_panel.add(passwordField);
		
		JButton login_button = new JButton("\u767B\u5F55");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MUser user = MUserManager.getUserManager().login(id_textField.getText().toString()
						,MD5.getMD5Str(new String( passwordField.getPassword())));
				if(user!=null){
					System.out.println(user.getUserName()+"登录成功！");
					// TODO 登录成功 进入主界面入口处  加TODO为了查找
					new MOSFrame();
					UserLoginFrame.this.dispose();
				}else{
					System.out.println("登录失败！");
				}
			}
		});
		login_button.setForeground(Color.BLACK);
		login_button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		login_button.setBounds(590, 488, 177, 23);
		main_panel.add(login_button);
		
		this.setVisible(true);
	}
}
