package com.hardware;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class MDisk extends MFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final double KB = 1024,MB = 1024*KB, GB = 1024*MB;
	
	public enum DiskState{
		reading,writing,avaliable;
	}
	
	private String diskModel ;
	private double diskSize = 256*MB;	
	private String diskFactoryInfo;   
	private DiskState diskState = DiskState.avaliable;
	private String diskExtraInfo;
	private String path;
	private static MDisk mdisk=new MDisk();
	
	
	private MDisk(){
		this.diskSize = 256*MB;	
		this.diskState = DiskState.avaliable;
		this.setType(FileType.disk);
	}
	
	public static MDisk getDisk(){
		if(mdisk==null){
			mdisk = new MDisk();
//			mdisk.initDisk();
		}
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

	public DiskState getDiskState() {
		return diskState;
	}

	public void setDiskState(DiskState diskState) {
		this.diskState = diskState;
	}

	public String getDiskExtraInfo() {
		return diskExtraInfo;
	}

	public void setDiskExtraInfo(String diskExtraInfo) {
		this.diskExtraInfo = diskExtraInfo;
	}

	public String getPath() {
		return path;
	}


	@Override
	public String toString() {
		return "MDisk [diskModel=" + diskModel + ", diskSize=" + diskSize
				+ ", diskFactoryInfo=" + diskFactoryInfo + ", diskState="
				+ diskState + ", diskExtraInfo=" + diskExtraInfo + ", path="
				+ path + "]";
	}

	
	public void saveDisk(){
		FileOutputStream fileOutputStream = null ;
		ObjectOutputStream objectOutputStream = null ;
		try {
			fileOutputStream = new FileOutputStream("system\\dev\\disk\\disk.ini");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(mdisk);
			System.out.println("save disk over!");
		} catch (IOException e1) {
			System.out.println("save disk faild!");
		} finally {
			try {
				fileOutputStream.close();
				objectOutputStream.close();
			} catch (IOException e1) {
				System.out.println("save disk iostream close faild!");
			}
		}
		
	}
	
	public void initDisk() {
		FileInputStream fileInputStream = null ;
		ObjectInputStream objectInputStream = null ;
		try {
			fileInputStream = new FileInputStream("system\\dev\\disk\\disk.ini");
			objectInputStream = new ObjectInputStream(fileInputStream);
			mdisk = (MDisk) objectInputStream.readObject();
			System.out.println("init disk over!");
		} catch (IOException e1) {
			System.out.println("init disk faild!");
		} catch (ClassNotFoundException e) {
			System.out.println("init disk faild! because class not found!");
		} finally {
			try {
				fileInputStream.close();
				objectInputStream.close();
			} catch (IOException e1) {
				System.out.println("init disk iostream close faild!");
			}
		}
	}
	
	
}
