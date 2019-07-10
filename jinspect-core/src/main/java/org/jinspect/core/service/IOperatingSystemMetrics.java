package org.jinspect.core.service;

import java.util.Map;

import org.jinspect.core.bean.OperatingSystemBean;

public interface IOperatingSystemMetrics {
	
	OperatingSystemBean getOperatingSystemBean(String pid);
	
	Map<String, String> getActiveVms();
	
}
