package com.app.filesystem.frame;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.hardware.MDisk;
import com.hardware.MFile;
import com.hardware.MFile.FileType;
import com.manager.MDiskManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

public class CreateFileDialog extends JDialog {
	private JTextField file_textField;
	private JComboBox sizeType_comboBox;
	private JSpinner fileSize_spinner;

	public CreateFileDialog(final FileSystemFrame owner) {
		super(owner);
		setTitle("\u65B0\u5EFA\u6587\u4EF6");
		this.setSize(193, 213);
		this.setLocation(owner.getLocation().x+(owner.getSize().width-this.getSize().width)/2,
				owner.getLocation().y+(owner.getSize().height-this.getSize().height)/2
				);
		this.setModal(true);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u6587\u4EF6\u540D\uFF1A");
		label.setBounds(10, 10, 156, 15);
		getContentPane().add(label);
		
		file_textField = new JTextField();
		file_textField.setBounds(10, 35, 156, 21);
		getContentPane().add(file_textField);
		file_textField.setColumns(10);
		
		JButton yes_button = new JButton("\u786E\u8BA4");
		yes_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rootDisk ;
				if(owner.getmFile().getType()==FileType.disk){
					rootDisk = owner.getmFile().getName();
				}else{
					rootDisk = owner.getmFile().getRootDisk();
				}
				String name = file_textField.getText().toString();
				int index = sizeType_comboBox.getSelectedIndex();
				double size ; 
				switch (index) {
				case 0:
					size = (int)fileSize_spinner.getValue();
					break;
				case 1:
					size = (int)fileSize_spinner.getValue()*MDisk.KB;
					break;
				case 2:
					size = (int)fileSize_spinner.getValue()*MDisk.MB;
					break;
				case 3:
					size = (int)fileSize_spinner.getValue()*MDisk.GB;
					break;
				default:
					return ;
				}
				MFile mFile = new MFile(name,rootDisk,size,FileType.file);
				MDiskManager.getDiskManager().distribute(owner.getmFile(),mFile);
				dispose();
			}
		});
		yes_button.setBounds(42, 137, 93, 23);
		getContentPane().add(yes_button);
		
		JLabel label_1 = new JLabel("\u8BF7\u8BBE\u7F6E\u6587\u4EF6\u5927\u5C0F\uFF1A");
		label_1.setBounds(10, 66, 156, 15);
		getContentPane().add(label_1);
		
		fileSize_spinner = new JSpinner();
		fileSize_spinner.setModel(new SpinnerNumberModel(1, 1, 1024, 1));
		fileSize_spinner.setBounds(10, 91, 68, 22);
		getContentPane().add(fileSize_spinner);
		
		sizeType_comboBox = new JComboBox();
		sizeType_comboBox.setModel(new DefaultComboBoxModel(new String[] {"B", "KB", "MB", "GB"}));
		sizeType_comboBox.setBounds(98, 91, 68, 21);
		getContentPane().add(sizeType_comboBox);
		
		this.setVisible(true);
	}
}
