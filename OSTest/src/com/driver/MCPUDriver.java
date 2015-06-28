package com.driver;

import com.hardware.MCPU;
import com.hardware.MCPU.CPUState;

public class MCPUDriver {
	private static final double NORMAL_TIME=10;
	private static final double OVERCLOCK_TIME=8;
	private static double time=NORMAL_TIME;
	private static double pid_taking;
	
	public static synchronized boolean push(double pid){
        pid_taking=pid;
		MCPU cpu = MCPU.getMCPU();
		cpu.setPid(pid);
		cpu.setCpuState(CPUState.unavailable);
		return false;
	}
	public static synchronized void run(){
			
	}
	public static synchronized double release(){
		MCPU cpu = MCPU.getMCPU();
		cpu.setPid(0);
		cpu.setCpuState(CPUState.available);
		return pid_taking;
	}
	public static synchronized void overclock(){
		time=OVERCLOCK_TIME;
	}
	public static synchronized void setNormal(){
		time=NORMAL_TIME;
	}
}
