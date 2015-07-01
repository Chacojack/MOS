package com.app.filesystem.frame;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.hardware.MDisk;
import com.hardware.MDisk.DiskState;
import com.hardware.MFile;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileSystemFrame extends JFrame implements WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //显示屏的高
	
	
	
	private JPanel fileSystem_panel;
	
	
	public FileSystemFrame() {
		setTitle("\u6211\u7684\u7535\u8111");
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\filesystem\\draw\\my_computer_72.png"));
		this.setSize(Screen_width/2, Screen_height/2);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		
		
		this.setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar fileSystem_menuBar = new JMenuBar();
		getContentPane().add(fileSystem_menuBar,BorderLayout.NORTH);
		
		JMenu file_menu = new JMenu("\u6587\u4EF6");
		fileSystem_menuBar.add(file_menu);
		
		JMenuItem newDirectory_menuItem = new JMenuItem("\u65B0\u5EFA\u6587\u4EF6\u5939");
		file_menu.add(newDirectory_menuItem);
		
		JMenuItem newFile_menuItem = new JMenuItem("\u65B0\u5EFA\u6587\u6863");
		file_menu.add(newFile_menuItem);
		
		JMenu disk_menu = new JMenu("\u78C1\u76D8");
		fileSystem_menuBar.add(disk_menu);
		
		JMenuItem format_menuItem = new JMenuItem("\u683C\u5F0F\u5316");
		disk_menu.add(format_menuItem);
		
		JMenuItem divide_menuItem = new JMenuItem("\u5206\u533A");
		divide_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DivideDialog(FileSystemFrame.this);
			}
		});
		disk_menu.add(divide_menuItem);
		
		JMenu check_menu = new JMenu("\u67E5\u770B");
		fileSystem_menuBar.add(check_menu);
		
		JMenuItem search_menuItem = new JMenuItem("\u67E5\u627E");
		check_menu.add(search_menuItem);
		
		JMenu menu = new JMenu("\u8DF3\u8F6C");
		fileSystem_menuBar.add(menu);
		
		JMenuItem root_menuItem = new JMenuItem("\u6839\u76EE\u5F55");
		root_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MFile mFile = MDisk.getDisk();
				addFileButton(mFile);
			}
		});
		menu.add(root_menuItem);
		
		fileSystem_panel = new JPanel();
		getContentPane().add(fileSystem_panel, BorderLayout.CENTER);
		fileSystem_panel.setLayout(null);
		
		this.setVisible(true);
		addFileButton(MDisk.getDisk());
	}
	
	public void addFileButton(final MFile mFile){
		cleanup();
		int count = 0;
		Map<String, MFile> map = mFile.getChildFile();
		if(map==null){
			return ;
		}
		Iterator<Entry<String, MFile>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, MFile> entry = iterator.next();
			if (entry.getValue().getType()==MFile.FileType.file) {
				FileButton fileButton = new FileButton(entry.getKey()){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionWhenClickDouble(FileButton fileButton) {
						super.actionWhenClickDouble(fileButton);
						
					}
				};
				fileButton.setIcon(new ImageIcon("res\\filesystem\\draw\\txt_72.png"));
				fileButton.setLocation(calculateLocationPoint(count));
				fileSystem_panel.add(fileButton);
			}else if (entry.getValue().getType()==MFile.FileType.directory) {
				FileButton fileButton = new FileButton(entry.getKey()){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionWhenClickDouble(FileButton fileButton) {
						super.actionWhenClickDouble(fileButton);
						cleanup();
						addFileButton(mFile.getChildFile().get(fileButton.getName()));
					}
				};
				fileButton.setIcon(new ImageIcon("res\\filesystem\\draw\\folder_72.png"));
				fileButton.setLocation(calculateLocationPoint(count));
				fileSystem_panel.add(fileButton);
			}else if (entry.getValue().getType()==MFile.FileType.disk) {
				FileButton fileButton = new FileButton(entry.getKey()){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionWhenClickDouble(FileButton fileButton) {
						super.actionWhenClickDouble(fileButton);
						cleanup();
						addFileButton(mFile.getChildFile().get(fileButton.getName()));
					}
				};
				fileButton.setIcon(new ImageIcon("res\\filesystem\\draw\\harddisk_72.png"));
				fileButton.setLocation(calculateLocationPoint(count));
				fileSystem_panel.add(fileButton);
			}else {
				continue;
			}
			count++;
		}
	}
	
	public Point calculateLocationPoint(int count){
		Point point = new Point();
		int cols = this.getWidth()/ (FileButton.WIDTH+10);     //获得窗口中容纳的列数
		int row,col;
		
		row = count/cols;               //计算当前所属行
		col = count%cols;
		int x = col*(FileButton.WIDTH+40)+40;
		int y = row*(FileButton.HIEGHT+20)+20;
		
		point.setLocation(x, y);
		return point;
		
	}

	public void cleanup(){
		fileSystem_panel.removeAll();
		fileSystem_panel.repaint();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		MDisk.getDisk().saveDisk();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MDisk.getDisk().saveDisk();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
