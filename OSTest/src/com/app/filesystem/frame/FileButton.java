package com.app.filesystem.frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import my.swing.MyJButton;

public class FileButton extends JPanel implements MouseListener{
	
	public final static int WIDTH = 76, HIEGHT = 100;
	
	private MyJButton myJButton = new MyJButton();
	
	private String name = "" ;
	
	private JLabel name_label ;
	
	public FileButton(String name){
		this.setBackground(null);
		this.setSize(WIDTH,HIEGHT);
		this.addMouseListener(this);
		this.myJButton.addMouseListener(this);
		this.myJButton.setSize(WIDTH, WIDTH);
		this.myJButton.setLocation(0, 0);
		this.add(myJButton);
		this.name = name ;
		name_label = new JLabel(name, JLabel.CENTER);
		name_label.setSize(WIDTH, HIEGHT-WIDTH);
		name_label.setLocation(0, WIDTH);
		name_label.setBorder(null);
		this.add(name_label);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.name_label.setText(name);
	}
	
	public void setIcon(Icon icon){
		this.myJButton.setIcon(icon);
	}
	
	public void actionWhenClickDouble(FileButton fileButton){
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount()==2) {
			actionWhenClickDouble(this);
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
	
	
	

}
