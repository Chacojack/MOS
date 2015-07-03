package com.app.user.frame;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JButton;

import com.app.launcher.frame.MOSFrame;
import com.manager.MMessageQueueManager;
import com.manager.MProcess;
import com.manager.MUser;
import com.manager.MUserManager;
import com.manager.MUserProcess;

import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;

public class MUserListFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //ÏÔÊ¾ÆÁµÄ¸ß
	
	private JPanel panel;
	
	private MOSFrame oFrame;

	public MUserListFrame(MOSFrame owner){
		oFrame = owner;
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\launcher\\draw\\users_in_32.png"));
		setTitle("\u7528\u6237\u5217\u8868");
		this.setSize(240, 382);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		
		this.setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu menu = new JMenu("\u64CD\u4F5C");
		menuBar.add(menu);
		
		JMenuItem add_menuItem = new JMenuItem("\u6DFB\u52A0\u7528\u6237");
		add_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateUserDialog(MUserListFrame.this);
				initUserList();
			}
		});
		menu.add(add_menuItem);
		
		JMenuItem delete_menuItem = new JMenuItem("\u5220\u9664\u7528\u6237");
		menu.add(delete_menuItem);
		
		JMenuItem login_menuItem = new JMenuItem("\u767B\u5F55\u7528\u6237");
		login_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MUserLoginDialog();
				oFrame.initForegroundUser();
				initUserList();
			}
		});
		menu.add(login_menuItem);
		
		panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		/*JPanel panel_1 = new JPanel();
		panel_1.setSize(230,50);
		panel.add(panel_1);*/
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		initUserList();
		this.setVisible(true);
	}
	
	
	public void initUserList(){
		panel.removeAll();
		Iterator<Entry<String, MUserProcess>> iterator = MUserManager.getUserManager().getOnLineUsers().entrySet().iterator();
		int count = 0;
		int height = 0;
		while(iterator.hasNext()){
			Entry<String, MUserProcess> entry = iterator.next();
			MUserPanel mUserPanel = new MUserPanel(entry.getValue(),oFrame,this);
			mUserPanel.setLocation(0,mUserPanel.getHeight()*count);
			height = mUserPanel.getHeight();
			System.out.println(entry.getValue().getUser().getUserName());
			panel.add(mUserPanel);
			count++;
		}
		panel.setSize(panel.getWidth(), height*count);
		panel.repaint();
		
	}
	
	
}













