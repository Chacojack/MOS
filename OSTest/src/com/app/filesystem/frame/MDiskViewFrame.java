package com.app.filesystem.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.sql.RowSet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hardware.DiskBlock;
import com.hardware.MDisk;
import com.hardware.MFile;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.Map.Entry;

public class MDiskViewFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height; // 显示屏的高
	
	public MDiskViewFrame(){
		setTitle("\u78C1\u76D8\u5185\u90E8\u5B58\u50A8");
		this.setSize(Screen_height / 2, Screen_height / 2+25);
		this.setLocation((Screen_width - this.getWidth()) / 2,
				(Screen_height - this.getHeight()) / 2);

		this.setResizable(false);
		
		DrawPanel panel = new DrawPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.repaint();
	}
	
	class DrawPanel extends JPanel implements MouseListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private MFile mFile = MDisk.getDisk();
		private int cols,rows;
		
		public DrawPanel(){
			this.addMouseListener(this);
		}

		public void paint(Graphics g){
			
			MDisk disk = MDisk.getDisk();
			double number_1 =(disk.getDiskSize()/DiskBlock.blockSize_1);
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
			int count = 0;
			Iterator<Entry<String, MFile>> iterator = disk.getChildFile().entrySet().iterator();
			while(iterator.hasNext()){
				Entry< String, MFile> entry = iterator.next();
				MFile mFile = entry.getValue();
				for(int i=0;i<mFile.getBlockList().size();i++){
					int col = count%cols;
					int row = count/cols;
					if(mFile.getBlockList().get(i).isAvaliable()){
						if(mFile.getBlockList().get(i).getBlockList()==null){
							g2.setColor(Color.green);
							g2.fillRect(this.getWidth()*col/cols+1, this.getHeight()*row/rows+1, this.getWidth()/cols-2, this.getHeight()/rows-2);
						}else{
							g2.setColor(Color.ORANGE);
							g2.fillRect(this.getWidth()*col/cols+1, this.getHeight()*row/rows+1, this.getWidth()/cols-2, this.getHeight()/rows-2);
						}
					}else{
						g2.setColor(Color.red);
						g2.fillRect(this.getWidth()*col/cols+1, this.getHeight()*row/rows+1, this.getWidth()/cols-2, this.getHeight()/rows-2);
					}
					count++;
				}
			}
			
			g.drawImage(bi, 0, 0, this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2){
				System.out.println("查看内存占用详情。");
				int col = e.getX()/(this.getWidth()/cols);
				int row = e.getY()/(this.getHeight()/rows);
				int count = row*cols+col , count_2 = 0;
				MDisk disk = MDisk.getDisk();
				
				Iterator<Entry<String, MFile>> iterator = disk.getChildFile().entrySet().iterator();
				while(iterator.hasNext()){
					Entry< String, MFile> entry = iterator.next();
					MFile mFile = entry.getValue();
					for(int i=0;i<mFile.getBlockList().size();i++){
						if(count_2 ==count){
							new MDiskViewDetailFrame(mFile.getBlockList().get(i));
						}
						count_2++;
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
	
	
	

}
