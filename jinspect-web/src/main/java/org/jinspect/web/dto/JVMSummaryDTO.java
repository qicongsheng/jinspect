package org.jinspect.web.dto;

import java.io.Serializable;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

import org.jinspect.core.bean.OperatingSystemBean;
import org.jinspect.core.bean.ThreadInfoBean;

public class JVMSummaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pid;
	private String pname;
	private OperatingSystemBean osBean;
	private MemoryUsage memoryUsage;
	private ThreadInfoBean[] threadInfos;
	private List<GarbageCollectorMXBean> gcBeans;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public OperatingSystemBean getOsBean() {
		return osBean;
	}

	public void setOsBean(OperatingSystemBean osBean) {
		this.osBean = osBean;
	}

	public MemoryUsage getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(MemoryUsage memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

	public ThreadInfoBean[] getThreadInfos() {
		return threadInfos;
	}

	public void setThreadInfos(ThreadInfoBean[] threadInfoBeans) {
		this.threadInfos = threadInfoBeans;
	}

	public List<GarbageCollectorMXBean> getGcBeans() {
		return gcBeans;
	}

	public void setGcBeans(List<GarbageCollectorMXBean> gcBeans) {
		this.gcBeans = gcBeans;
	}

}
