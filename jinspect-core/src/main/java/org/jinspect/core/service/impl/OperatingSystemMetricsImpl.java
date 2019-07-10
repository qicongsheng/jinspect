package org.jinspect.core.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jinspect.core.bean.OperatingSystemBean;
import org.jinspect.core.common.JMXMetricsFactory;
import org.jinspect.core.service.IOperatingSystemMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

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

	@Override
	public Map<String, String> getActiveVms() {
		Map<String, String> result = new HashMap<String, String>();
		try {
			MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
			Set<Integer> vmIdSet = new HashSet<Integer>(local.activeVms());
			for(Integer process : vmIdSet) {
				MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + process));
				String processName = MonitoredVmUtil.mainClass(vm, true);
				int lastIndexOfDot = processName.replaceAll(".jar", "").lastIndexOf(".");
				result.put(String.valueOf(process), processName.substring(lastIndexOfDot + 1));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
}
