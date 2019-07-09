package org.jinspect.service.impl;

import java.lang.management.ThreadInfo;

import org.jinspect.common.JMXMetricsFactory;
import org.jinspect.service.IThreadMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadMetricsImpl implements IThreadMetrics{
	
	private static Logger logger = LoggerFactory.getLogger(ThreadMetricsImpl.class);

	@Override
	public ThreadInfo[] getThreadInfos(String pid) {
		ThreadInfo[] result = null;
		try {
			com.sun.management.ThreadMXBean threadMXBean = JMXMetricsFactory.getThreadMXBean(pid);
			result = threadMXBean.dumpAllThreads(false, false);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		return result;
	}


}
