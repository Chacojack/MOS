package com.manager;

public class MProcess extends Thread{
	public enum ProcessType{
		systemProcess,userProcess,idlingProcess;
	}
    public enum ProcessState{
		ready,running,stopped;
	}
	private double pid;
	private ProcessType processType=ProcessType.systemProcess;
	private ProcessState processState;
	private double processGroupId;
	private MUser user;
	private double runTime=Double.POSITIVE_INFINITY;//默认时间片无限大
	private String createTime;
	private int processPrority;
	
	public ProcessType getProcessType() {
		return processType;
	}
	public void setProcessType(ProcessType processType) {
		this.processType = processType;
	}
	public ProcessState getProcessState() {
		return processState;
	}
	public void setProcessState(ProcessState processState) {
		this.processState = processState;
	}
	public double getProcessGroupId() {
		return processGroupId;
	}
	public void setProcessGroupId(double processGroupId) {
		this.processGroupId = processGroupId;
	}
	public MUser getUser() {
		return user;
	}
	public void setUser(MUser user) {
		this.user = user;
	}
	public double getRunTime() {
		return runTime;
	}
	public void setRunTime(double runTime) {
		this.runTime = runTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getProcessPrority() {
		return processPrority;
	}
	public void setProcessPrority(int processPrority) {
		this.processPrority = processPrority;
	}
	public double getPid() {
		return pid;
	}
	public void setPid(double pid) {
		this.pid = pid;
	}

}
