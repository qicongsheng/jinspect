package org.jinspect.core.service;

import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

public interface IGarbageCollectorMetrics {

	List<GarbageCollectorMXBean> getGarbageCollectors(String pid);

}
