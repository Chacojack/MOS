package com.app.filesystem.frame;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.hardware.MDisk;
import com.hardware.MDisk.DiskState;
import com.hardware.MFile;
import com.hardware.MFile.FileType;
import com.manager.MDiskManager;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class FileSystemFrame extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height; // 显示屏的高

	private MFile mFile = MDisk.getDisk() , selectFile ;

	private JPanel fileSystem_panel;
	private JToolBar toolBar;

	public FileSystemFrame() {
		setTitle("\u6211\u7684\u7535\u8111");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"res\\filesystem\\draw\\my_computer_72.png"));
		this.setSize(Screen_width / 2, Screen_height / 2);
		this.setLocation((Screen_width - this.getWidth()) / 2,
				(Screen_height - this.getHeight()) / 2);
		// this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.setResizable(false);
		this.addWindowListener(this);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JMenuBar fileSystem_menuBar = new JMenuBar();
		getContentPane().add(fileSystem_menuBar, BorderLayout.NORTH);

		JMenu file_menu = new JMenu("\u6587\u4EF6");
		fileSystem_menuBar.add(file_menu);

		JMenuItem newDirectory_menuItem = new JMenuItem(
				"\u65B0\u5EFA\u6587\u4EF6\u5939");
		newDirectory_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CreateFolderDialog(FileSystemFrame.this);
				addFileButton(mFile);
			}
		});
		file_menu.add(newDirectory_menuItem);

		JMenuItem newFile_menuItem = new JMenuItem("\u65B0\u5EFA\u6587\u6863");
		newFile_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CreateFileDialog(FileSystemFrame.this);
				addFileButton(mFile);
			}
		});
		file_menu.add(newFile_menuItem);
		
		JMenuItem delete_menuItem = new JMenuItem("\u5220\u9664\u5F53\u524D\u9009\u4E2D\u6587\u4EF6");
		delete_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MDiskManager.getDiskManager().delete(mFile, selectFile);
				addFileButton(mFile);
				
			}
		});
		file_menu.add(delete_menuItem);

		JMenu disk_menu = new JMenu("\u78C1\u76D8");
		fileSystem_menuBar.add(disk_menu);

		JMenuItem format_menuItem = new JMenuItem("\u683C\u5F0F\u5316");
		format_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("格式化操作！");
				if(mFile.getType()==FileType.disk){  
					int option = JOptionPane.showConfirmDialog(null, "确认格式化当前磁盘？",
							"格式化", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE, null);
					switch (option) {
					case JOptionPane.YES_NO_OPTION: {
						MDiskManager.getDiskManager().format(mFile);
						addFileButton(mFile);
						break;
					}
					case JOptionPane.NO_OPTION:
						System.exit(0);
					}
				}else{
					System.out.println("当前文件不是磁盘。");
				}
			}
		});
		disk_menu.add(format_menuItem);

		JMenuItem divide_menuItem = new JMenuItem("\u5206\u533A");
		divide_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DivideDialog(FileSystemFrame.this);
				addFileButton(MDisk.getDisk());
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
				mFile = MDisk.getDisk();
				addFileButton(mFile);
			}
		});
		menu.add(root_menuItem);

		fileSystem_panel = new JPanel();
		getContentPane().add(fileSystem_panel, BorderLayout.CENTER);
		fileSystem_panel.setLayout(null);

		toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.SOUTH);

		JLabel statu_label = new JLabel("\u72B6\u6001\u680F ");
		toolBar.add(statu_label);

		this.setVisible(true);
		addFileButton(MDisk.getDisk());
	}

	public void addFileButton(final MFile mfile) {
		cleanup();
		int count = 0;
		Map<String, MFile> map = mfile.getChildFile();
		if (map == null) {
			return;
		}
		Iterator<Entry<String, MFile>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, MFile> entry = iterator.next();
			if (entry.getValue().getType() == MFile.FileType.file) {
				FileButton fileButton = new FileButton(entry.getKey()) {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionWhenClickDouble(FileButton fileButton) {
						super.actionWhenClickDouble(fileButton);
						MFile mfile = mFile.getChildFile().get(fileButton.getName());
						new EditFileFrame(mfile);
					}
					@Override
					public void actionWhenClickSingle(FileButton fileButton) {
						super.actionWhenClickSingle(fileButton);
						selectFile = mfile.getChildFile().get(fileButton.getName());
					}
				};
				fileButton.setIcon(new ImageIcon(
						"res\\filesystem\\draw\\txt_48.png"));
				fileButton.setLocation(calculateLocationPoint(count));
				fileSystem_panel.add(fileButton);
			} else if (entry.getValue().getType() == MFile.FileType.directory) {
				FileButton fileButton = new FileButton(entry.getKey()) {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionWhenClickDouble(FileButton fileButton) {
						super.actionWhenClickDouble(fileButton);
						mFile = mfile.getChildFile().get(fileButton.getName());
						addFileButton(mfile.getChildFile().get(
								fileButton.getName()));
					}
					
					@Override
					public void actionWhenClickSingle(FileButton fileButton) {
						super.actionWhenClickSingle(fileButton);
						selectFile = mfile.getChildFile().get(fileButton.getName());
					}
				};
				fileButton.setIcon(new ImageIcon(
						"res\\filesystem\\draw\\floder_48.png"));
				fileButton.setLocation(calculateLocationPoint(count));
				fileSystem_panel.add(fileButton);
			} else if (entry.getValue().getType() == MFile.FileType.disk) {
				FileButton fileButton = new FileButton(entry.getKey()) {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionWhenClickDouble(FileButton fileButton) {
						super.actionWhenClickDouble(fileButton);
						mFile = mfile.getChildFile().get(fileButton.getName());
						addFileButton(mfile.getChildFile().get(
								fileButton.getName()));
					}

					@Override
					public void actionWhenClickSingle(FileButton fileButton) {
						super.actionWhenClickSingle(fileButton);
						selectFile = mfile.getChildFile().get(fileButton.getName());
						// TODO 添加展示磁盘空间的操作
					}
				};
				fileButton.setIcon(new ImageIcon(
						"res\\filesystem\\draw\\harddisk_48.png"));
				fileButton.setLocation(calculateLocationPoint(count));
				fileSystem_panel.add(fileButton);
			} else {
				continue;
			}
			count++;
		}
	}
	
	

	public MFile getmFile() {
		return mFile;
	}

	public void setmFile(MFile mFile) {
		this.mFile = mFile;
	}

	public Point calculateLocationPoint(int count) {
		Point point = new Point();
		int cols = this.getWidth() / (FileButton.WIDTH + 10); // 获得窗口中容纳的列数
		int row, col;

		row = count / cols; // 计算当前所属行
		col = count % cols;
		int x = col * (FileButton.WIDTH + 40) + 40;
		int y = row * (FileButton.HIEGHT + 20) + 20;

		point.setLocation(x, y);
		return point;

	}

	public void cleanup() {
		fileSystem_panel.removeAll();
		fileSystem_panel.repaint();
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MDisk.getDisk().saveDisk();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}
}
