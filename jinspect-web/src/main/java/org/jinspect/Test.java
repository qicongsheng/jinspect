package org.jinspect;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import javax.management.MBeanServerConnection;

import org.jinspect.util.JMXMetricsUtil;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main1(String[] args) throws Exception {
		ThreadMXBean tb = ManagementFactory.getThreadMXBean();
		String pid = "1216";
		MBeanServerConnection connection = JMXMetricsUtil.getLocalServerConnection(pid);
		ThreadMXBean threadMXBean = ManagementFactory.getPlatformMXBean(connection, ThreadMXBean.class);
		MemoryMXBean mb = ManagementFactory.newPlatformMXBeanProxy(connection, "java.lang:type=Memory", MemoryMXBean.class);
		com.sun.management.ThreadMXBean ec = ManagementFactory.newPlatformMXBeanProxy(connection, "java.lang:type=Threading", com.sun.management.ThreadMXBean.class);
		
		ThreadInfo[] infos = threadMXBean.dumpAllThreads(true, true);
		threadMXBean.getThreadCpuTime(infos[0].getThreadId());
		
		long byteLen = ec.getThreadAllocatedBytes(infos[0].getThreadId());
		System.out.println(byteLen);
		
	}

}
