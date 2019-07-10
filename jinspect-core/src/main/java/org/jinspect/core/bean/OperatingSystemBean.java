package org.jinspect.core.bean;

import java.io.Serializable;

public class OperatingSystemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String osName;
	private String version;
	private String osArch;
	private int availableProcessors;
	private double totalPhysicalMemorySize;
	private double totalSwapSpaceSize;
	private double freePhysicalMemorySize;
	private double freeSwapSpaceSize;
	private double systemCpuLoad;
	private double processCpuLoad;
	private long processCpuTime;

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public int getAvailableProcessors() {
		return availableProcessors;
	}

	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}

	public double getTotalPhysicalMemorySize() {
		return totalPhysicalMemorySize;
	}

	public void setTotalPhysicalMemorySize(double totalPhysicalMemorySize) {
		this.totalPhysicalMemorySize = totalPhysicalMemorySize;
	}

	public double getTotalSwapSpaceSize() {
		return totalSwapSpaceSize;
	}

	public void setTotalSwapSpaceSize(double totalSwapSpaceSize) {
		this.totalSwapSpaceSize = totalSwapSpaceSize;
	}

	public double getFreePhysicalMemorySize() {
		return freePhysicalMemorySize;
	}

	public void setFreePhysicalMemorySize(double freePhysicalMemorySize) {
		this.freePhysicalMemorySize = freePhysicalMemorySize;
	}

	public double getFreeSwapSpaceSize() {
		return freeSwapSpaceSize;
	}

	public void setFreeSwapSpaceSize(double freeSwapSpaceSize) {
		this.freeSwapSpaceSize = freeSwapSpaceSize;
	}

	public double getSystemCpuLoad() {
		return systemCpuLoad;
	}

	public void setSystemCpuLoad(double systemCpuLoad) {
		this.systemCpuLoad = systemCpuLoad;
	}

	public double getProcessCpuLoad() {
		return processCpuLoad;
	}

	public void setProcessCpuLoad(double processCpuLoad) {
		this.processCpuLoad = processCpuLoad;
	}

	public long getProcessCpuTime() {
		return processCpuTime;
	}

	public void setProcessCpuTime(long processCpuTime) {
		this.processCpuTime = processCpuTime;
	}

}
