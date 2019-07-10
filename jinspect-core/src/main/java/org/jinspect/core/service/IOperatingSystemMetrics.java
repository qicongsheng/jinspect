package org.jinspect.core.service;

import org.jinspect.core.bean.OperatingSystemBean;

public interface IOperatingSystemMetrics {
	
	OperatingSystemBean getOperatingSystemBean(String pid);
	
}
