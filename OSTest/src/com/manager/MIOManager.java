package com.manager;

import com.driver.MIODriver;

public class MIOManager {
	private static final MIOManager ioManager=new MIOManager();
	private MIOManager(){}
	public static MIOManager getIOManager(){
		return ioManager;
	}
	public boolean applyIO(double pid,int io){
		if(!MIODriver.getIOState(io)){
			return false;
		}
		else {
//			return MIODriver.setOcuppied(pid, io);
			return true;
		}
	}
	public boolean distributeIO(double pid,int io){
		return MIODriver.setOcuppied(pid, io);
	}
	public boolean releaseIO(double pid){
		return MIODriver.releaseOcuppy(pid);
	}

}
