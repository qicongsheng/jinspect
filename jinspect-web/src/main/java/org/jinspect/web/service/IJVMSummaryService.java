package org.jinspect.web.service;

import java.util.List;
import java.util.Map;

import org.jinspect.web.dto.JVMSummaryDTO;

public interface IJVMSummaryService {
	
	List<JVMSummaryDTO> getJVMSummaryByPid(Map<String, String> activeVMs);
	
}
