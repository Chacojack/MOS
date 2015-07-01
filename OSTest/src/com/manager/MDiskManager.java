package com.manager;

import java.io.Serializable;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import com.hardware.MDisk;
import com.hardware.MFile;
import com.hardware.MFile.FileType;
import com.tool.math.MMathTool;

public class MDiskManager implements Serializable{
	
	private final double KB = 1024,MB = 1024*KB;

	private double blockSize = 1024*KB;
	
	private static MDiskManager diskManager = new MDiskManager();
	
	private MDiskManager(){}
	
	public static MDiskManager getDiskManager(){
		if(diskManager==null){
			diskManager = new MDiskManager();
		}
		return diskManager;
	}
	
	public boolean divide(double[] sizes){
		if(sizes==null||sizes.length==0){
			return false;
		}
		double[] new_sizes = new double[sizes.length+1];
		double last_size = MDisk.getDisk().getDiskSize()-MMathTool.sum(sizes);
		if(last_size>0){
			for(int i=0;i < sizes.length;i++){
				new_sizes[i] = sizes[i];
			}
			new_sizes[new_sizes.length-1] = last_size;
			MDiskManager.getDiskManager().divide(new_sizes);
		}
		double low = 0;
		for(int i = 0;i<sizes.length ;i++ ){
			MDisk disk = MDisk.getDisk();
			MFile mFile = new MFile("block"+(i+1) ,low+1 , sizes[i], FileType.disk);
			disk.getChildFile().put(mFile.getName(), mFile);
			low = low + sizes[i];
		}
		return true;
	}
	
	public void format(){
		format(MDisk.getDisk());
	}
	
	public void format(MFile mFile){
		mFile.setChildFile(new HashMap<String, MFile>());
	}
	
	
	
}
