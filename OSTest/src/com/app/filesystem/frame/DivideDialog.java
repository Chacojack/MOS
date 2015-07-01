package com.app.filesystem.frame;


import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerNumberModel;

import com.hardware.MDisk;
import com.manager.MDiskManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DivideDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSpinner divideNumber_spinner;
	private JSpinner first_spinner;
	private JSpinner second_spinner;
	private JSpinner third_spinner;
	private JSpinner fourth_spinner;
	private JSpinner fifth_spinner;
	private JButton first_button;
	private JButton second_button;
	private JButton third_button;
	private JButton fourth_button;
	private JButton divide_button;
	
	
	
	
	private double[] sizes;
	
	public DivideDialog(JFrame owner){
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\filesystem\\draw\\harddisk_72.png"));
		this.setTitle("\u5206\u533A");
		this.setSize(263, 304);
		this.setLocation(owner.getLocation().x+(owner.getSize().width-this.getSize().width)/2,
				owner.getLocation().y+(owner.getSize().height-this.getSize().height)/2
				);
		
		this.setModal(true);
		getContentPane().setLayout(null);
		
		JLabel diskInfo_label = new JLabel("\u603B\u78C1\u76D8\u5927\u5C0F\uFF1A");
		diskInfo_label.setBounds(10, 10, 73, 15);
		getContentPane().add(diskInfo_label);
		
		JLabel label1 = new JLabel("\u5C06\u78C1\u76D8\u5206\u4E3A");
		label1.setBounds(10, 38, 73, 15);
		getContentPane().add(label1);
		
		divideNumber_spinner = new JSpinner();
		divideNumber_spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		divideNumber_spinner.setBounds(93, 35, 44, 22);
		getContentPane().add(divideNumber_spinner);
		
		JLabel label11 = new JLabel("\u5206\u533A");
		label11.setBounds(147, 38, 36, 15);
		getContentPane().add(label11);
		
		first_spinner = new JSpinner();
		first_spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer((int)(MDisk.getDisk().getDiskSize()/MDisk.MB)), new Integer(1)));
		first_spinner.setEnabled(false);
		first_spinner.setBounds(93, 67, 44, 22);
		getContentPane().add(first_spinner);
		
		
		JLabel label_2 = new JLabel("\u7B2C\u4E00\u5206\u533A\uFF1A");
		label_2.setBounds(10, 70, 73, 15);
		getContentPane().add(label_2);
		
		JLabel lblMb = new JLabel("MB");
		lblMb.setBounds(147, 70, 36, 15);
		getContentPane().add(lblMb);
		
		JLabel label_3 = new JLabel("\u7B2C\u4E8C\u5206\u533A\uFF1A");
		label_3.setBounds(10, 102, 73, 15);
		getContentPane().add(label_3);
		
		second_spinner = new JSpinner();
		second_spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		second_spinner.setEnabled(false);
		second_spinner.setBounds(93, 99, 44, 22);
		getContentPane().add(second_spinner);
		
		JLabel label_4 = new JLabel("MB");
		label_4.setBounds(147, 102, 36, 15);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u7B2C\u4E09\u5206\u533A\uFF1A");
		label_5.setBounds(10, 135, 73, 15);
		getContentPane().add(label_5);
		
		third_spinner = new JSpinner();
		third_spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		third_spinner.setEnabled(false);
		third_spinner.setBounds(93, 132, 44, 22);
		getContentPane().add(third_spinner);
		
		JLabel label_6 = new JLabel("MB");
		label_6.setBounds(147, 135, 36, 15);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u7B2C\u56DB\u5206\u533A\uFF1A");
		label_7.setBounds(10, 167, 73, 15);
		getContentPane().add(label_7);
		
		fourth_spinner = new JSpinner();
		fourth_spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		fourth_spinner.setEnabled(false);
		fourth_spinner.setBounds(93, 164, 44, 22);
		getContentPane().add(fourth_spinner);
		
		JLabel label_8 = new JLabel("MB");
		label_8.setBounds(147, 167, 36, 15);
		getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("\u7B2C\u4E94\u5206\u533A\uFF1A");
		label_9.setBounds(10, 200, 73, 15);
		getContentPane().add(label_9);
		
		fifth_spinner = new JSpinner();
		fifth_spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		fifth_spinner.setEnabled(false);
		fifth_spinner.setBounds(93, 197, 44, 22);
		getContentPane().add(fifth_spinner);
		
		JLabel label_10 = new JLabel("MB");
		label_10.setBounds(147, 200, 36, 15);
		getContentPane().add(label_10);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				int value = (int) divideNumber_spinner.getValue();
				switch (value) {
				case 1:
					sizes = new double[1];
					sizes[0] = (int)first_spinner.getValue();
					break;
				case 2:
					sizes = new double[1];
					sizes[0] = (int)first_spinner.getValue();
//					sizes[1] = (int)second_spinner.getValue();
					break;
				case 3:
					sizes = new double[2];
					sizes[0] = (int)first_spinner.getValue();
					sizes[1] = (int)second_spinner.getValue();
//					sizes[2] = (int)third_spinner.getValue();
					break;
				case 4:
					sizes = new double[3];
					sizes[0] = (int)first_spinner.getValue();
					sizes[1] = (int)second_spinner.getValue();
					sizes[2] = (int)third_spinner.getValue();
//					sizes[3] = (int)fourth_spinner.getValue();
					break;
				case 5:
					sizes = new double[4];
					sizes[0] = (int)first_spinner.getValue();
					sizes[1] = (int)second_spinner.getValue();
					sizes[2] = (int)third_spinner.getValue();
					sizes[3] = (int)fourth_spinner.getValue();
//					sizes[4] = (int)fifth_spinner.getValue();
					break;
				default:
					return ;
				}
				double[] sizes_double = new double[sizes.length];
				for(int i=0;i<sizes_double.length;i++){
					sizes_double[i] = sizes[i] * MDisk.MB;
				}
				MDiskManager.getDiskManager().divide(sizes_double);
				dispose();
			}
		});
		button.setBounds(79, 233, 93, 23);
		getContentPane().add(button);
		
		JLabel label = new JLabel("MB");
		label.setBounds(147, 10, 36, 15);
		getContentPane().add(label);
		
		JLabel diskSize_label = new JLabel(""+MDisk.getDisk().getDiskSize()/MDisk.MB);
		
		diskSize_label.setBounds(93, 10, 44, 15);
		getContentPane().add(diskSize_label);
		
		divide_button = new JButton("\u786E\u8BA4");
		divide_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first_spinner.setValue(Integer.valueOf(1));
				second_spinner.setValue(Integer.valueOf(1));
				third_spinner.setValue(Integer.valueOf(1));
				fourth_spinner.setValue(Integer.valueOf(1));
				fifth_spinner.setValue(Integer.valueOf(1));
				divideNumber_spinner.setEnabled(false);
				divide_button.setEnabled(false);
				first_spinner.setEnabled(true);
				first_button.setEnabled(true);
				int value = (int)divideNumber_spinner.getValue();
				if(value==1){
					first_spinner.setValue(new Integer((int)(MDisk.getDisk().getDiskSize()/MDisk.MB)));
				}
			}
		});
		divide_button.setBounds(179, 34, 60, 23);
		getContentPane().add(divide_button);
		
		first_button = new JButton("\u786E\u8BA4");
		first_button.setEnabled(false);
		first_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first_spinner.setEnabled(false);
				first_button.setEnabled(false);
				int value = (int)divideNumber_spinner.getValue();
				if(value>1){
					second_button.setEnabled(true);
					second_spinner.setEnabled(true);
				}
				if(value==2){
					second_spinner.setValue(remainDisk()+(int)second_spinner.getValue());
				}
			}
		});
		first_button.setBounds(179, 66, 60, 23);
		getContentPane().add(first_button);
		
		second_button = new JButton("\u786E\u8BA4");
		second_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				second_button.setEnabled(false);
				second_spinner.setEnabled(false);
				int value = (int)divideNumber_spinner.getValue();
				if(value>2){
					third_button.setEnabled(true);
					third_spinner.setEnabled(true);
				}
				if(value==3){
					third_spinner.setValue(remainDisk()+(int)third_spinner.getValue());
				}
			}
		});
		second_button.setEnabled(false);
		second_button.setBounds(179, 98, 60, 23);
		getContentPane().add(second_button);
		
		third_button = new JButton("\u786E\u8BA4");
		third_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = (int)divideNumber_spinner.getValue();
				third_button.setEnabled(false);
				third_spinner.setEnabled(false);
				if(value>3){
					fourth_button.setEnabled(true);
					fourth_spinner.setEnabled(true);
				}
				if(value==4){
					fourth_spinner.setValue(remainDisk()+(int)fourth_spinner.getValue());
				}
			}
		});
		third_button.setEnabled(false);
		third_button.setBounds(179, 131, 60, 23);
		getContentPane().add(third_button);
		
		fourth_button = new JButton("\u786E\u8BA4");
		fourth_button.setEnabled(false);
		fourth_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fourth_spinner.setEnabled(false);
				fourth_button.setEnabled(false);
				int value = (int)divideNumber_spinner.getValue();
				if(value>4){
					fifth_spinner.setEnabled(true);
				}
				if(value==5){
					fifth_spinner.setValue(remainDisk()+(int)fifth_spinner.getValue());
				}
			}
		});
		fourth_button.setBounds(179, 163, 60, 23);
		getContentPane().add(fourth_button);
		this.setVisible(true);
	}
	
	public int remainDisk(){
		int value = (int) divideNumber_spinner.getValue();
		switch (value) {
		case 1:
			return  ((int)(MDisk.getDisk().getDiskSize()/MDisk.MB)-(int)first_spinner.getValue());
		case 2:
			return  ((int)(MDisk.getDisk().getDiskSize()/MDisk.MB)-(int)first_spinner.getValue()
					-(int)second_spinner.getValue());
		case 3:
			return  ((int)(MDisk.getDisk().getDiskSize()/MDisk.MB)-(int)first_spinner.getValue()
					-(int)second_spinner.getValue()-(int)third_spinner.getValue());
		case 4:
			return  ((int)(MDisk.getDisk().getDiskSize()/MDisk.MB)-(int)first_spinner.getValue()
					-(int)second_spinner.getValue()-(int)third_spinner.getValue()-(int)fourth_spinner.getValue());
		case 5:
			return  ((int)(MDisk.getDisk().getDiskSize()/MDisk.MB)-(int)first_spinner.getValue()
					-(int)second_spinner.getValue()-(int)third_spinner.getValue()-(int)fourth_spinner.getValue()
					-(int)first_spinner.getValue());
		default:
			return 0;
		}
	}
	
	
	
}
