package com.app.process.frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;






import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import java.awt.Panel;

import javax.swing.JRadioButton;

import com.manager.MProcess;
import com.manager.MProcessManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProcessSystemFrame extends JFrame implements ActionListener{
	
    private int Screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int Screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;    //显示屏的高
	private JTextField Id_TextField;
	private JTextField MemorySize_TextField;
	private JRadioButton firstInFirstOut_RadioButton;
	private JRadioButton shortProcessFirst_RadioButton;
	private JRadioButton timeSliceRound_RadioButton;
	private JRadioButton prioritySchedule_RadioButton;
	private JLabel showSchedule_Label;
	private JButton createProcess_Button;
	private JTextField time_TextField;
	private JComboBox prority_comboBox;
	private MProcessManager mprocessManager=MProcessManager.getProcessManager();
	
	public ProcessSystemFrame(){
		setTitle("\u4EFB\u52A1\u7BA1\u7406\u5668");
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\processsystem\\draw\\my_process_72.png"));
		this.setSize(683, 525);
		this.setLocation((Screen_width-this.getWidth())/2,(Screen_height-this.getHeight())/2);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel ProcessSystempanel = new JPanel();
		ProcessSystempanel.setBackground(Color.WHITE);
		ProcessSystempanel.setBounds(0, 0, 677, 496);
		getContentPane().add(ProcessSystempanel);
		ProcessSystempanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 255));
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 429, 222);
		ProcessSystempanel.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u963B\u585E\u961F\u5217");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(10, 34, 67, 28);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5C31\u7EEA\u961F\u5217");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(10, 86, 67, 26);
		panel.add(label_1);
		
		JLabel lblCpu = new JLabel("");
		lblCpu.setBounds(310, 34, 109, 102);
		panel.add(lblCpu);
		lblCpu.setIcon(new ImageIcon("D:\\\u7528\u6237\u76EE\u5F55\\\u6211\u7684\u6587\u6863\\GitHub\\MOS\\OSTest\\res\\processsystem\\draw\\cpu.gif"));
		
		JLabel label_4 = new JLabel("\u8FD0\u884C\u8FDB\u7A0B");
		label_4.setFont(new Font("宋体", Font.PLAIN, 15));
		label_4.setBounds(10, 133, 67, 26);
		panel.add(label_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(87, 36, 215, 26);
		panel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(87, 86, 215, 26);
		panel.add(panel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(87, 133, 215, 26);
		panel.add(panel_6);
		
		JLabel lblCpu_1 = new JLabel("CPU\u65F6\u95F4\u7247");
		lblCpu_1.setForeground(SystemColor.textHighlight);
		lblCpu_1.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 15));
		lblCpu_1.setBounds(334, 146, 85, 26);
		panel.add(lblCpu_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_1.setBounds(454, 10, 213, 221);
		ProcessSystempanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8FDB\u7A0BID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setBounds(25, 10, 76, 22);
		panel_1.add(lblNewLabel);
		
		JLabel label_3 = new JLabel("\u4F18\u5148\u7EA7");
		label_3.setForeground(Color.WHITE);
		label_3.setOpaque(true);
		label_3.setBackground(SystemColor.textHighlight);
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(25, 56, 76, 22);
		panel_1.add(label_3);
		
		JLabel label_5 = new JLabel("\u5185\u5B58\u5927\u5C0F");
		label_5.setForeground(Color.WHITE);
		label_5.setBackground(SystemColor.textHighlight);
		label_5.setOpaque(true);
		label_5.setHorizontalTextPosition(SwingConstants.CENTER);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(25, 100, 76, 22);
		panel_1.add(label_5);
		
		createProcess_Button = new JButton("\u751F\u6210\u8FDB\u7A0B");
		createProcess_Button.setBounds(110, 188, 93, 23);
		createProcess_Button.addActionListener(this);
		panel_1.add(createProcess_Button);
		
		Id_TextField = new JTextField();
		Id_TextField.setBackground(SystemColor.menu);
		Id_TextField.setBorder(null);
		Id_TextField.setBounds(111, 11, 93, 22);
		panel_1.add(Id_TextField);
		Id_TextField.setColumns(10);
		
		MemorySize_TextField = new JTextField();
		MemorySize_TextField.setBackground(SystemColor.menu);
		MemorySize_TextField.setBorder(null);
		MemorySize_TextField.setBounds(111, 101, 93, 22);
		panel_1.add(MemorySize_TextField);
		MemorySize_TextField.setColumns(10);
		
		String[] strings={"1","2","3","4","5"};
		 prority_comboBox = new JComboBox(strings);
		prority_comboBox.setBackground(SystemColor.menu);
		prority_comboBox.setBorder(null);
		prority_comboBox.setBounds(111, 57, 92, 21);
		panel_1.add(prority_comboBox);
		
		JLabel label_9 = new JLabel("\u65F6\u95F4\u7247");
		label_9.setOpaque(true);
		label_9.setHorizontalTextPosition(SwingConstants.CENTER);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.WHITE);
		label_9.setBackground(SystemColor.textHighlight);
		label_9.setBounds(25, 143, 76, 22);
		panel_1.add(label_9);
		
		time_TextField = new JTextField();
		time_TextField.setColumns(10);
		time_TextField.setBorder(null);
		time_TextField.setBackground(SystemColor.menu);
		time_TextField.setBounds(110, 144, 93, 22);
		panel_1.add(time_TextField);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 247, 544, 98);
		ProcessSystempanel.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(28, 50, 506, 25);
		panel_5.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("\u7CFB\u7EDF\u5360\u7528");
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(SystemColor.textHighlight);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(0, 0, 112, 25);
		panel_7.add(lblNewLabel_3);
		
		JLabel label_2 = new JLabel("\u5185\u5B58\u72B6\u51B5\u52A8\u6001\u663E\u793A");
		label_2.setBounds(231, 6, 96, 15);
		panel_5.add(label_2);
		
		JLabel label_6 = new JLabel("0");
		label_6.setBounds(28, 25, 13, 15);
		panel_5.add(label_6);
		
		JLabel lblm = new JLabel("100 M");
		lblm.setBounds(505, 25, 39, 15);
		panel_5.add(lblm);
		
		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new ProcessShowFrame();
			}
		});
		panel_4.setBounds(566, 247, 101, 96);
		ProcessSystempanel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblCpu_2 = new JLabel("\u5185\u5B58\u4F7F\u7528\u7387:");
		lblCpu_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCpu_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpu_2.setBounds(10, 10, 81, 15);
		panel_4.add(lblCpu_2);
		
		JLabel lblNewLabel_1 = new JLabel("20%");
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(10, 52, 81, 15);
		panel_4.add(lblNewLabel_1);
		
		Panel panel_8 = new Panel();
		panel_8.setBounds(10, 366, 351, 120);
		ProcessSystempanel.add(panel_8);
		panel_8.setLayout(null);
		
		firstInFirstOut_RadioButton = new JRadioButton("\u5148\u8FDB\u5148\u51FA");
		firstInFirstOut_RadioButton.setBounds(6, 36, 121, 23);
		firstInFirstOut_RadioButton.addActionListener(this);
		
		
		shortProcessFirst_RadioButton = new JRadioButton("\u77ED\u4F5C\u4E1A\u4F18\u5148");
		shortProcessFirst_RadioButton.setBounds(6, 61, 121, 23);
		shortProcessFirst_RadioButton.addActionListener(this);
		
		
		timeSliceRound_RadioButton = new JRadioButton("\u65F6\u95F4\u7247\u8F6E\u8F6C");
		timeSliceRound_RadioButton.setBounds(6, 86, 121, 23);
		timeSliceRound_RadioButton.addActionListener(this);
		
		
		prioritySchedule_RadioButton = new JRadioButton("\u4F18\u5148\u7EA7\u8C03\u5EA6");
		prioritySchedule_RadioButton.setBounds(6, 11, 121, 23);
		prioritySchedule_RadioButton.addActionListener(this);
		
		ButtonGroup processSchedule=new ButtonGroup();
		processSchedule.add(firstInFirstOut_RadioButton);
		processSchedule.add(shortProcessFirst_RadioButton);
		processSchedule.add(timeSliceRound_RadioButton);
		processSchedule.add(prioritySchedule_RadioButton);
		panel_8.add(prioritySchedule_RadioButton);
		panel_8.add(firstInFirstOut_RadioButton);
		panel_8.add(shortProcessFirst_RadioButton);
		panel_8.add(timeSliceRound_RadioButton);
		
		
		
		JLabel label_7 = new JLabel("\u76EE\u524D\u7684\u8FDB\u7A0B\u8C03\u5EA6\u7B56\u7565\uFF1A");
		label_7.setBounds(152, 25, 141, 15);
		panel_8.add(label_7);
		
		showSchedule_Label = new JLabel("\u65F6\u95F4\u7247\u8F6E\u8F6C");
		showSchedule_Label.setHorizontalAlignment(SwingConstants.CENTER);
		showSchedule_Label.setHorizontalTextPosition(SwingConstants.CENTER);
		showSchedule_Label.setBackground(Color.WHITE);
		showSchedule_Label.setForeground(SystemColor.textHighlight);
		showSchedule_Label.setOpaque(true);
		showSchedule_Label.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 20));
		showSchedule_Label.setBounds(152, 50, 155, 48);
		panel_8.add(showSchedule_Label);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(376, 366, 291, 120);
		ProcessSystempanel.add(panel_10);
		panel_10.setLayout(null);
		
		
		String[] str1={"最坏适应","最先适应","最好适应"};
		JComboBox comboBox_1 = new JComboBox(str1);
		comboBox_1.setBounds(58, 58, 79, 21);
		panel_10.add(comboBox_1);
		
		JLabel label_8 = new JLabel("\u5185\u5B58\u5206\u914D\u7B56\u7565");
		label_8.setBounds(105, 10, 102, 15);
		panel_10.add(label_8);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539\u7B56\u7565");
		btnNewButton.setBounds(177, 57, 93, 23);
		panel_10.add(btnNewButton);
		
		this.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object=e.getSource();
		if(object.equals(firstInFirstOut_RadioButton)){
			showSchedule_Label.setText("先入先出");
		}
		if(object.equals(shortProcessFirst_RadioButton)){
			showSchedule_Label.setText("短作业优先");	
		}
		if(object.equals(timeSliceRound_RadioButton)){
			showSchedule_Label.setText("时间片轮转");
		}
		if(object.equals(prioritySchedule_RadioButton)){
			showSchedule_Label.setText("优先级调度");
		}
		if(object.equals(createProcess_Button)){
			
			if(Id_TextField.getText().equals("")||time_TextField.getText().equals("")||MemorySize_TextField
					.equals("")){
				JOptionPane.showMessageDialog(null, "进程信息不能为空！");
			}
			else {
				MProcess process=new MProcess();
				process.setPid(Double.parseDouble(Id_TextField.getText()));
				process.setMemorySize(Double.parseDouble(MemorySize_TextField
						.getText()));
				process.setProcessPrority(Integer.parseInt(prority_comboBox
						.getSelectedItem().toString()));
				process.setRunTime(Double.parseDouble(time_TextField.getText()));
				if(mprocessManager.registerProcess(process)){
					System.out.println(mprocessManager.getProcessRegistry().get(process.getPid()).getPid());
					
				}else{
					JOptionPane.showMessageDialog(null, "进程创建失败，确认输入的信息正确");
				}
			}
		}
		
	}
}
