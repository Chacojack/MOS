package com.app.user.frame;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import com.manager.MUser.UserType;
import com.manager.MUserManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUserDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //ÏÔÊ¾ÆÁµÄ¸ß
	private JTextField id_textField;
	private JTextField name_textField;
	private JPasswordField passwordField;
	private JComboBox<Object> type_comboBox;
	
	public CreateUserDialog(JFrame owner){
		setTitle("\u6DFB\u52A0\u7528\u6237");
		this.setSize(262, 203);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		this.setModal(true);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setBounds(31, 27, 42, 15);
		getContentPane().add(label);
		
		id_textField = new JTextField();
		id_textField.setBounds(74, 24, 147, 21);
		getContentPane().add(id_textField);
		id_textField.setColumns(10);
		
		name_textField = new JTextField();
		name_textField.setColumns(10);
		name_textField.setBounds(74, 52, 147, 21);
		getContentPane().add(name_textField);
		
		JLabel label_1 = new JLabel("\u6635\u540D\uFF1A");
		label_1.setBounds(31, 55, 42, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setBounds(31, 86, 42, 15);
		getContentPane().add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(74, 83, 147, 21);
		getContentPane().add(passwordField);
		
		JLabel label_3 = new JLabel("\u8EAB\u4EFD\uFF1A");
		label_3.setBounds(31, 114, 42, 15);
		getContentPane().add(label_3);
		
		type_comboBox = new JComboBox<Object>();
		type_comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"administrator", "user"}));
		type_comboBox.setBounds(74, 111, 147, 21);
		getContentPane().add(type_comboBox);
		
		JButton yes_button = new JButton("\u786E\u8BA4");
		yes_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserType level ;
				switch (type_comboBox.getSelectedIndex()) {
				case 0:
					level = UserType.administrator;
					break;
				case 1:
					level = UserType.user;
					break;
				default:
					return ;
				}
				
				if(MUserManager.getUserManager().createUser(id_textField.getText()
						, name_textField.getText()
						, level
						, new String(passwordField.getPassword()))){
					dispose();
				}
			}
		});
		yes_button.setBounds(84, 142, 93, 23);
		getContentPane().add(yes_button);
		
		this.setVisible(true);
	}
	
	
	
	
	

}
