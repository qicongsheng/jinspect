package org.jinspect.core.service.impl;

import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import org.jinspect.core.common.JMXMetricsFactory;
import org.jinspect.core.service.IMemoryMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryMetricsImpl implements IMemoryMetrics {

	private static Logger logger = LoggerFactory.getLogger(MemoryMetricsImpl.class);

	public MemoryUsage getHeapMemoryUsage(String pid) {
		MemoryUsage memoryUsage = null;
		try {
			MemoryMXBean memoryMXBean = JMXMetricsFactory.getMemoryMXBean(pid);
			memoryUsage = memoryMXBean.getHeapMemoryUsage();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return memoryUsage;
	}

	public MemoryUsage getNonHeapMemoryUsage(String pid) {
		MemoryUsage memoryUsage = null;
		try {
			MemoryMXBean memoryMXBean = JMXMetricsFactory.getMemoryMXBean(pid);
			memoryUsage = memoryMXBean.getNonHeapMemoryUsage();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return memoryUsage;
	}

}
