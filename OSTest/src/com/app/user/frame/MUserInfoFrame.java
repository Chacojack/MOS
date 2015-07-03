package com.app.user.frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import com.manager.MUser;
import com.manager.MUserManager;
import com.manager.MUser.UserType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MUserInfoFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //显示屏的高
	private JTextField id_textField;
	private JTextField name_textField;
	private JComboBox<String> type_comboBox;
	
	private MUser user ;
	
	public MUserInfoFrame(MUser muser) {
		this.user = muser;
		setTitle("\u5F53\u524D\u7528\u6237\u4FE1\u606F");
		this.setSize(281, 165);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		
		this.setResizable(false);                   //设置为不可更改窗口大小
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setBounds(10, 10, 44, 15);
		getContentPane().add(label);
		
		id_textField = new JTextField();
		id_textField.setEnabled(false);
		id_textField.setBounds(57, 7, 135, 21);
		getContentPane().add(id_textField);
		id_textField.setColumns(10);
		id_textField.setText(muser.getUserId());
		
		JLabel label_1 = new JLabel("\u6635\u540D\uFF1A");
		label_1.setBounds(10, 38, 44, 15);
		getContentPane().add(label_1);
		
		name_textField = new JTextField();
		name_textField.setEnabled(false);
		name_textField.setColumns(10);
		name_textField.setBounds(57, 35, 135, 21);
		getContentPane().add(name_textField);
		name_textField.setText(muser.getUserName());
		
		JLabel label_2 = new JLabel("\u8EAB\u4EFD\uFF1A");
		label_2.setBounds(10, 69, 44, 15);
		getContentPane().add(label_2);
		
		type_comboBox = new JComboBox<String>();
		type_comboBox.setEnabled(false);
		type_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"administrator", "user"}));
		type_comboBox.setBounds(57, 66, 135, 21);
		getContentPane().add(type_comboBox);
		switch (muser.getLevel()) {
		case administrator:
			type_comboBox.setSelectedIndex(0);
			break;
		case user:
			type_comboBox.setSelectedIndex(1);
			break;
		default:
			break;
		}
		
		
		JButton nameUpdate_button = new JButton("\u4FEE\u6539");
		nameUpdate_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name_textField.setEnabled(true);
			}
		});
		nameUpdate_button.setBounds(202, 34, 65, 23);
		getContentPane().add(nameUpdate_button);
		
		JButton typeUpdate_button = new JButton("\u4FEE\u6539");
		typeUpdate_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type_comboBox.setEnabled(true);
			}
		});
		typeUpdate_button.setBounds(202, 65, 65, 23);
		getContentPane().add(typeUpdate_button);
		
		JButton yes_button = new JButton("\u786E\u8BA4");
		yes_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MUserManager.getUserManager().updateName(user.getUserId(), name_textField.getText());
				switch (type_comboBox.getSelectedIndex()) {
				case 0:
					MUserManager.getUserManager().updateType(user.getUserId(), UserType.administrator);
					break;
				case 1:
					MUserManager.getUserManager().updateType(user.getUserId(), UserType.user);
					break;
				default:
					break;
				}
			}
		});
		yes_button.setBounds(99, 105, 93, 23);
		getContentPane().add(yes_button);
		
		
		
		this.setVisible(true);
	}
}
