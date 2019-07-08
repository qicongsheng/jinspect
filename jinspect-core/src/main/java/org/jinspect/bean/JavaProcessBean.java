package org.jinspect.bean;

import java.io.Serializable;

public class JavaProcessBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String startUser;
	private String processId;
	private String startTime;
	private String startCommand;
	private String params;
	
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getStartUser() {
		return startUser;
	}

	public void setStartUser(String startUser) {
		this.startUser = startUser;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartCommand() {
		return startCommand;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setStartCommand(String startCommand) {
		this.startCommand = startCommand;
	}

}
