package com.hardware;


public class MMemoryItem {
	private double pid;
	private double low;
	private double high;
	private double size;
	public MMemoryItem(){};
	public double getPid() {
		return pid;
	}
	public void setPid(double pid) {
		this.pid = pid;
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
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
}
