package org.jinspect.bean;

import java.io.Serializable;
import java.util.Map;

public class RuntimeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String vmName;
	private String vmVersion;
	private Map<String, String> systemProperties;
	private long uptime;

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getVmVersion() {
		return vmVersion;
	}

	public void setVmVersion(String vmVersion) {
		this.vmVersion = vmVersion;
	}

	public Map<String, String> getSystemProperties() {
		return systemProperties;
	}

	public void setSystemProperties(Map<String, String> systemProperties) {
		this.systemProperties = systemProperties;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

}
