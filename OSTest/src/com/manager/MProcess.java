package com.manager;

public class MProcess extends Thread{
	private enum ProcessType{
		systemProcess,userProcess,idlingProcess
	}
    private enum ProcessState{
		ready,running,stopped
	}
	private double processId;
	private ProcessType processType;
	private ProcessState processState;
	private double processGroupId;
	private MUser user;
	private double runTime;
	private String createTime;
	private int processPrority;
	
	public double getProcessId() {
		return processId;
	}
	public void setProcessId(double processId) {
		this.processId = processId;
	}
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

}
