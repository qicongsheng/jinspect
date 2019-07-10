package org.jinspect.core.service.impl;

import java.lang.management.ThreadInfo;

import org.jinspect.core.bean.ThreadInfoBean;
import org.jinspect.core.common.JMXMetricsFactory;
import org.jinspect.core.service.IThreadMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadMetricsImpl implements IThreadMetrics{
	
	private static Logger logger = LoggerFactory.getLogger(ThreadMetricsImpl.class);

	@Override
	public ThreadInfoBean[] getThreadInfos(String pid) {
		ThreadInfoBean[] result = null;
		try {
			com.sun.management.ThreadMXBean threadMXBean = JMXMetricsFactory.getThreadMXBean(pid);
			ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
			result = new ThreadInfoBean[threadInfos.length];
			// ø…”≈ªØ
			for (int i = 0; i < threadInfos.length; i++) {
				ThreadInfo info = threadInfos[i];
				long tid = info.getThreadId();
				result[i] = new ThreadInfoBean();
				result[i].setThreadId(tid);
				result[i].setThreadName(info.getThreadName());
				result[i].setThreadState(info.getThreadState());
				result[i].setThreadAllocatedBytes(threadMXBean.getThreadAllocatedBytes(tid));
				result[i].setThreadCpuTime(threadMXBean.getThreadCpuTime(tid));
				result[i].setThreadUserTime(threadMXBean.getThreadUserTime(tid));
				result[i].setStackTraces(info.getStackTrace());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		return result;
	}


}
