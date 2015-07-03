package com.app.user.frame;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.manager.MUserManager;
import com.tool.security.md5.MD5;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MUserLoginDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //ÏÔÊ¾ÆÁµÄ¸ß
	private JTextField id_textField;
	private JPasswordField passwordField;
	
	public MUserLoginDialog(){
		setTitle("\u7528\u6237\u767B\u5F55");
		this.setSize(262, 177);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		this.setModal(true);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel.setBounds(35, 28, 44, 15);
		getContentPane().add(lblNewLabel);
		
		id_textField = new JTextField();
		id_textField.setBounds(79, 25, 142, 21);
		getContentPane().add(id_textField);
		id_textField.setColumns(10);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setBounds(35, 66, 44, 15);
		getContentPane().add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(79, 64, 142, 18);
		getContentPane().add(passwordField);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MUserManager.getUserManager().login(id_textField.getText()
						, MD5.getMD5Str(new String(passwordField.getPassword()))) != null){
					dispose();
				}
			}
		});
		button.setBounds(85, 102, 93, 23);
		getContentPane().add(button);
		this.setVisible(true);
	}
}
