package org.jinspect.bean;

import java.io.Serializable;

public class ThreadBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pid;
	private String tid;
	private String cpuPercent;
	private String memoryPercent;
	private String cpuTime;
	private String state;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCpuPercent() {
		return cpuPercent;
	}

	public void setCpuPercent(String cpuPercent) {
		this.cpuPercent = cpuPercent;
	}

	public String getMemoryPercent() {
		return memoryPercent;
	}

	public void setMemoryPercent(String memoryPercent) {
		this.memoryPercent = memoryPercent;
	}

	public String getCpuTime() {
		return cpuTime;
	}

	public void setCpuTime(String cpuTime) {
		this.cpuTime = cpuTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
