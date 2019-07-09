package org.jinspect.core.service.impl;

import java.lang.management.RuntimeMXBean;

import org.jinspect.core.bean.RuntimeBean;
import org.jinspect.core.common.JMXMetricsFactory;
import org.jinspect.core.service.IRuntimeMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuntimeMetricsImpl implements IRuntimeMetrics{
	
	private static Logger logger = LoggerFactory.getLogger(RuntimeMetricsImpl.class);
	
	public RuntimeBean getRuntimeBean(String pid){
		RuntimeBean bean = new RuntimeBean();
		try {
			RuntimeMXBean runtimeMX = JMXMetricsFactory.getRuntimeMXBean(pid);
			bean.setSystemProperties(runtimeMX.getSystemProperties());
			bean.setUptime(runtimeMX.getUptime());
			bean.setVmName(runtimeMX.getVmName());
			bean.setVmVersion(runtimeMX.getVmVersion());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return bean;
	}


}
