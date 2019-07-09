package org.jinspect.core.service.impl;

import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;

import org.jinspect.core.common.JMXMetricsFactory;
import org.jinspect.core.service.IOperatingSystemMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatingSystemMetricsImpl implements IOperatingSystemMetrics {
	
	private static Logger logger = LoggerFactory.getLogger(OperatingSystemMetricsImpl.class);
	
	public void getProcessCpuLoad(String pid){
		try {
			com.sun.management.OperatingSystemMXBean osMXBean = JMXMetricsFactory.getOperatingSystemMXBean(pid);
			double t = osMXBean.getProcessCpuLoad();
			logger.info(t + " # ---");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
