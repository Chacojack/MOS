package my.swing;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

public class MyJButton extends JButton implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyJButton(){
		super();
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFocusPainted(false);
		this.addMouseListener(this);
	}
	
	public MyJButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFocusPainted(false);
		this.addMouseListener(this);
		
	}



	public MyJButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFocusPainted(false);
		this.addMouseListener(this);
		
	}



	public MyJButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFocusPainted(false);
		this.addMouseListener(this);
	}



	public MyJButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFocusPainted(false);
		this.addMouseListener(this);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setBorder(BorderFactory.createEmptyBorder());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	
	

}
