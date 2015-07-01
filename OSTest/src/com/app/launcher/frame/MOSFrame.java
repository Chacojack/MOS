package com.app.launcher.frame;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.app.filesystem.frame.FileSystemFrame;
import com.hardware.MDisk;

import my.swing.MyJButton;


public class MOSFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //显示屏的高
	
	
	public MOSFrame() {
		this.setSize(Screen_width, Screen_height);
		this.setLocation(0,0);
		this.setUndecorated(true);
		
		this.setResizable(false);                   //设置为不可更改窗口大小
		getContentPane().setLayout(null);
		
		JPanel main_panel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("res/launcher/draw/background.png");
				g.drawImage(img.getImage(), 0, 0, this.getWidth(),this.getHeight(),  null);
			}
		};
		main_panel.setBounds(0, 0, 1366, 768);
		getContentPane().add(main_panel);
		main_panel.setLayout(null);
		ImageIcon img = new ImageIcon("res/launcher/draw/my_computer_72.png");
		MyJButton myComputer_button = new MyJButton(img);
		myComputer_button.setBounds(10, 10, 72, 72);
		myComputer_button.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					new FileSystemFrame();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});;
		main_panel.add(myComputer_button);
		this.setVisible(true);
		
	}
	
	
	
}
