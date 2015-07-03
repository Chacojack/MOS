package my.swing;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MyAlphaJPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float alpha = 0.7f; 
	
	public MyAlphaJPanel(){
		
	}
	
	public MyAlphaJPanel(float alpha){
		this.alpha = alpha;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g; 
		AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(newComposite);
		super.paintComponent(g2d);
	}
}
