package org.jinspect.core.service.impl;

import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;

import org.jinspect.core.bean.OperatingSystemBean;
import org.jinspect.core.common.JMXMetricsFactory;
import org.jinspect.core.service.IOperatingSystemMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatingSystemMetricsImpl implements IOperatingSystemMetrics {
	
	private static Logger logger = LoggerFactory.getLogger(OperatingSystemMetricsImpl.class);
	
	@Override
	public OperatingSystemBean getOperatingSystemBean(String pid) {
		OperatingSystemBean bean = new OperatingSystemBean();
		try {
			com.sun.management.OperatingSystemMXBean osMXBean = JMXMetricsFactory.getOperatingSystemMXBean(pid);
			bean.setAvailableProcessors(osMXBean.getAvailableProcessors());
			bean.setFreePhysicalMemorySize(osMXBean.getFreePhysicalMemorySize());
			bean.setFreeSwapSpaceSize(osMXBean.getFreeSwapSpaceSize());
			bean.setOsArch(osMXBean.getArch());
			bean.setOsName(osMXBean.getName());
			bean.setProcessCpuLoad(osMXBean.getProcessCpuLoad());
			bean.setProcessCpuTime(osMXBean.getProcessCpuTime());
			bean.setSystemCpuLoad(osMXBean.getSystemCpuLoad());
			bean.setTotalPhysicalMemorySize(osMXBean.getTotalPhysicalMemorySize());
			bean.setTotalSwapSpaceSize(osMXBean.getTotalSwapSpaceSize());
			bean.setVersion(osMXBean.getVersion());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return bean;
	}
}
