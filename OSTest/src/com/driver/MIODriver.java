package com.driver;

import java.io.IOError;
import java.util.Map;

import com.hardware.MIO;

public class MIODriver {
	private static MIO IO=MIO.getMio();
	private static Map<Integer, Double> ioUserList=IO.getIOUserList();
	
	public static Map<Integer, Double> getIOUserList(){
		return ioUserList;
	}
	public static boolean setOcuppied(double pid,int io){
		ioUserList.put(io, pid);
		return true;
	}
	public static boolean releaseOcuppy(double pid){
		for(int i=1;i<6;i++){
			if(ioUserList.get(i)==pid){
				ioUserList.put(i, null);
				return true;
			}
		}
		return false;
	}
	public static boolean getIOState(int io){
		if(ioUserList.get(io)!=null){
			return false;
		}
		else {
			return true;
		}
	}
}
