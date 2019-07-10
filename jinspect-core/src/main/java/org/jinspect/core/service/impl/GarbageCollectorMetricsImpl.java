package org.jinspect.core.service.impl;

import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

import org.jinspect.core.common.JMXMetricsFactory;
import org.jinspect.core.service.IGarbageCollectorMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarbageCollectorMetricsImpl implements IGarbageCollectorMetrics {
	
	private static Logger logger = LoggerFactory.getLogger(GarbageCollectorMetricsImpl.class);

	public List<GarbageCollectorMXBean> getGarbageCollectors(String pid) {
		List<GarbageCollectorMXBean> gcs = null;
		try {
			gcs = JMXMetricsFactory.getGarbageCollectorMXBeans(pid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return gcs;
	}

}
