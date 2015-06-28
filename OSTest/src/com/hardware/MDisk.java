package com.hardware;


public class MDisk {
	public enum DiskState{
		reading,writing,avaliable;
	}
	private static final MDisk mdisk=new MDisk();
	private String diskModel;
	private double diskSize;	
	private String diskFactoryInfo;
	private DiskState diskState;
	private String diskExtraInfo;
	
	private MDisk(){}
	public static MDisk getDisk(){
		return mdisk;
	}
	public String getDiskModel() {
		return diskModel;
	}
	public void setDiskModel(String diskModel) {
		this.diskModel = diskModel;
	}
	public double getDiskSize() {
		return diskSize;
	}
	public void setDiskSize(double diskSize) {
		this.diskSize = diskSize;
	}
	public String getDiskFactoryInfo() {
		return diskFactoryInfo;
	}
	public void setDiskFactoryInfo(String diskFactoryInfo) {
		this.diskFactoryInfo = diskFactoryInfo;
	}
	public String getDiskExtraInfo() {
		return diskExtraInfo;
	}
	public void setDiskExtraInfo(String diskExtraInfo) {
		this.diskExtraInfo = diskExtraInfo;
	}
	public DiskState getDiskState() {
		return diskState;
	}
	public void setDiskState(DiskState diskState) {
		this.diskState = diskState;
	}

}
