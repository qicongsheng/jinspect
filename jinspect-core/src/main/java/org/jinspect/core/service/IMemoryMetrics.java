package org.jinspect.core.service;

import java.lang.management.MemoryUsage;

public interface IMemoryMetrics {
	
	MemoryUsage getHeapMemoryUsage(String pid);

	MemoryUsage getNonHeapMemoryUsage(String pid);
	
}
