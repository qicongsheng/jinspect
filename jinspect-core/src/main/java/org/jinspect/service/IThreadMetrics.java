package org.jinspect.service;

import java.lang.management.ThreadInfo;

public interface IThreadMetrics {

	ThreadInfo[] getThreadInfos(String pid);

}
