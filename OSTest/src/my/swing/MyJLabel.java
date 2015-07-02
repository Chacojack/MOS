package my.swing;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyJLabel extends JLabel{
	public MyJLabel(String text,int x){
		super(text);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setSize(x, 25);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBackground(SystemColor.textHighlight);
		this.setForeground(Color.WHITE);
		this.setOpaque(true);
	}
}
