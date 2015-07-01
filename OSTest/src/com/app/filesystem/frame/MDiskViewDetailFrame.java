package com.app.filesystem.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.hardware.DiskBlock;

public class MDiskViewDetailFrame extends JFrame {
	
	private int cols,rows;
	
	private DiskBlock diskBlock ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height; // ÏÔÊ¾ÆÁµÄ¸ß
	
	public MDiskViewDetailFrame(DiskBlock diskBlock){
		this.diskBlock = diskBlock;
		this.setSize(Screen_height / 2, Screen_height / 2+25);
		this.setLocation((Screen_width - this.getWidth()) / 2,
				(Screen_height - this.getHeight()) / 2);

		this.setResizable(false);
		
		DrawPanel panel = new DrawPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.repaint();
	}
	
	class DrawPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	
		public void paint(Graphics g){
			
			double number_1 = DiskBlock.blockSize_1/DiskBlock.blockSize_2;
			cols = (int) Math.sqrt(number_1);
			rows = (int) (number_1/cols);
			
			BufferedImage bi = new BufferedImage(this.getWidth(),
					this.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g2 = bi.getGraphics();
			
			for(int i = 0;i<=cols;i++){
				g2.drawLine(this.getWidth()*i/cols, 0, this.getWidth()*i/cols, this.getHeight());
			}
			for(int i = 0;i<=rows;i++){
				g2.drawLine(0, this.getHeight()*i/cols,this.getWidth(), this.getHeight()*i/cols);
			}
			for(int i=0;i<diskBlock.getBlockList().size();i++){
				int col = i%cols;
				int row = i/cols;
				if(diskBlock.getBlockList().get(i).isAvaliable()){
					g2.setColor(Color.green);
					g2.fillRect(this.getWidth()*col/cols+1, this.getHeight()*row/rows+1, this.getWidth()/cols-2, this.getHeight()/rows-2);
				}else{
					g2.setColor(Color.red);
					g2.fillRect(this.getWidth()*col/cols+1, this.getHeight()*row/rows+1, this.getWidth()/cols-2, this.getHeight()/rows-2);
				}
			}
			g.drawImage(bi, 0, 0, this);
		}
	}
	

}
