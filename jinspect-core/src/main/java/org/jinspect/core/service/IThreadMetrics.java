package org.jinspect.core.service;

import java.lang.management.ThreadInfo;

import org.jinspect.core.bean.ThreadInfoBean;

public interface IThreadMetrics {

	ThreadInfoBean[] getThreadInfos(String pid);

}
