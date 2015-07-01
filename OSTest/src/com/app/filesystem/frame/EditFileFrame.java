package com.app.filesystem.frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import com.hardware.MDisk;
import com.hardware.MFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditFileFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height; // ÏÔÊ¾ÆÁµÄ¸ß
	
	private JTextArea file_textArea;
	
	public EditFileFrame(final MFile mFile) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\WorkSpace\\EclipseWorkSpace\\OSTest\\res\\filesystem\\draw\\txt_48.png"));
		setTitle("\u8BB0\u4E8B\u672C");
		this.setSize(Screen_width / 2, Screen_height / 2);
		this.setLocation((Screen_width - this.getWidth()) / 2,
				(Screen_height - this.getHeight()) / 2);

		this.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file_menu = new JMenu("\u6587\u4EF6");
		menuBar.add(file_menu);
		
		JMenuItem save_menuItem = new JMenuItem("\u4FDD\u5B58");
		save_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mFile.setData(file_textArea.getText().toString());
				MDisk.getDisk().saveDisk();
			}
		});
		file_menu.add(save_menuItem);
		file_textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(file_textArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.setVisible(true);
		file_textArea.setText(mFile.getData());
	}

}
