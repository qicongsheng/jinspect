package org.jinspect.cpu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jinspect.common.CommandExecutor;
import org.jinspect.common.LinuxCommandExecutor;

public class CPUInspector {
	
	public List<String> getCpuUseThreadIdOrderByUse(String vmid) throws Exception{
		List<String> result = new ArrayList<String>();
		CommandExecutor executor = new LinuxCommandExecutor();
		Map<String, String> params = new HashMap<String, String>();
		params.put("vmid", vmid);
		List<String> threadDetails = executor.exec("ps.cpu.busy.thread", params);
		for (int i = 0; i < threadDetails.size(); i++) {
			String threadDetail = threadDetails.get(i);
			if(threadDetail.contains("CPU") || threadDetail.contains("cpu")){
				continue;
			}
			String[] detailItems = threadDetail.split(" ");
			result.add(detailItems[2]);
		}
		return result;
	}

}
