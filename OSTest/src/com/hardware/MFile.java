package com.hardware;

import java.io.File;

public class MFile extends File{
	private String id;
	private double low;
	private double high;
	private String iconPath;
	private String path;
	
	public MFile(String path){
		super("E:/MOSDiskSpace"+path);
		this.path=path;
	}
	public String getMOSPath(){
		return path;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	

}
