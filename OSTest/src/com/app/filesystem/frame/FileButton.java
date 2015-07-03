package com.app.filesystem.frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import my.swing.MyJButton;

public class FileButton extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static int WIDTH = 74, HIEGHT = 78;
	
	private MyJButton myJButton = new MyJButton();
	
	private String name = "" ;
	
	private JLabel name_label ;
	
	public FileButton(String name){
		this.name = name ;
		this.setBackground(null);
		this.setSize(74,78);
		this.addMouseListener(this);
		this.setLayout(null);
		
		this.myJButton.addMouseListener(this);
		this.myJButton.setSize(48, 48);
		this.myJButton.setLocation((this.getWidth()-myJButton.getWidth())/2, 0);
		this.add(myJButton);
		
		name_label = new JLabel(name, JLabel.CENTER);
		name_label.setSize(this.getWidth(), this.getHeight()-myJButton.getHeight());
		name_label.setLocation(0, myJButton.getY()+myJButton.getHeight());
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

	
	public void actionWhenClickSingle(FileButton fileButton){
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount()==2) {
			actionWhenClickDouble(this);
		}else if (e.getClickCount()==1) {
			actionWhenClickSingle(this);
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
