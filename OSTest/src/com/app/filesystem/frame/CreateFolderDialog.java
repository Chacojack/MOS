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

public class CreateFolderDialog extends JDialog {
	private JTextField floder_textField;

	public CreateFolderDialog(final FileSystemFrame owner) {
		super(owner);
		setTitle("\u65B0\u5EFA\u6587\u4EF6\u5939");
		this.setSize(193, 136);
		this.setLocation(owner.getLocation().x+(owner.getSize().width-this.getSize().width)/2,
				owner.getLocation().y+(owner.getSize().height-this.getSize().height)/2
				);
		this.setModal(true);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u6587\u4EF6\u5939\u540D\uFF1A");
		label.setBounds(10, 10, 156, 15);
		getContentPane().add(label);
		
		floder_textField = new JTextField();
		floder_textField.setBounds(10, 35, 156, 21);
		getContentPane().add(floder_textField);
		floder_textField.setColumns(10);
		
		JButton yes_button = new JButton("\u786E\u8BA4");
		yes_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rootDisk ;
				if(owner.getmFile().getType()==FileType.disk){
					rootDisk = owner.getmFile().getName();
				}else{
					rootDisk = owner.getmFile().getRootDisk();
				}
				String name = floder_textField.getText().toString();
				MFile mFile = new MFile(name,rootDisk,FileType.directory);
				MDiskManager.getDiskManager().createFloder(owner.getmFile(), mFile);
				dispose();
			}
		});
		yes_button.setBounds(43, 66, 93, 23);
		getContentPane().add(yes_button);
		
		this.setVisible(true);
	}
}
