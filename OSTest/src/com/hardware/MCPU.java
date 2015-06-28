package com.hardware;

/**   
 *  
 * @ClassName:    MCPU
 *    
 */
public class MCPU {
	
	public enum CPUState {
		available,unavailable;    
	}
	private static final MCPU mcpu = new MCPU();
	private String cpuModel;
	private double cpuFrequency;
	private String cpuExtraInfo;
	private double pid;
	private CPUState cpuState=CPUState.available;
	
	private MCPU(){}
	
	public static MCPU getMCPU(){
		return mcpu;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public double getCpuFrequency() {
		return cpuFrequency;
	}

	public void setCpuFrequency(double cpuFrequency) {
		this.cpuFrequency = cpuFrequency;
	}

	public String getCpuExtraInfo() {
		return cpuExtraInfo;
	}

	public void setCpuExtraInfo(String cpuExtraInfo) {
		this.cpuExtraInfo = cpuExtraInfo;
	}

	public CPUState getCpuState() {
		return cpuState;
	}

	public void setCpuState(CPUState cpuState) {
		this.cpuState = cpuState;
	}

	public double getPid() {
		return pid;
	}

	public void setPid(double pid) {
		this.pid = pid;
	}
	

}
