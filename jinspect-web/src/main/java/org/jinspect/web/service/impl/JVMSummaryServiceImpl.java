package org.jinspect.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jinspect.web.dto.JVMSummaryDTO;
import org.jinspect.web.service.IJVMSummaryService;

public class JVMSummaryServiceImpl implements IJVMSummaryService {

    @Override
    public List<JVMSummaryDTO> getJVMSummaryByPid(Map<String, String> activeVMs) {
        List<JVMSummaryDTO> summaries = new ArrayList<JVMSummaryDTO>();
        // IOperatingSystemMetrics osMetrics = new OperatingSystemMetricsImpl();
        // IMemoryMetrics memMetrics = new MemoryMetricsImpl();
        // IThreadMetrics threadMetrics = new ThreadMetricsImpl();
        // IGarbageCollectorMetrics gcMetrics = new GarbageCollectorMetricsImpl();
        // Iterator<Entry<String, String>> iter = activeVMs.entrySet().iterator();
        // while(iter.hasNext()){
        // Entry<String, String> entry = iter.next();
        // String pid = entry.getKey();
        // String pname = entry.getValue();
        // JVMSummaryDTO sum = new JVMSummaryDTO();
        // sum.setPid(pid);
        // sum.setPname(pname);
        // sum.setOsBean(osMetrics.getOperatingSystemBean(pid));
        // sum.setMemoryUsage(memMetrics.getHeapMemoryUsage(pid));
        // sum.setThreadInfos(threadMetrics.getThreadInfos(pid));
        // sum.setGcBeans(gcMetrics.getGarbageCollectors(pid));
        // summaries.add(sum);
        // }
        return summaries;
    }

}
