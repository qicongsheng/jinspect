package org.jinspect.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jinspect.bean.ThreadBean;
import org.jinspect.common.CommandExecutor;
import org.jinspect.common.LinuxCommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryInspector {
	
	private static Logger logger = LoggerFactory.getLogger(MemoryInspector.class);
	
	public List<ThreadBean> getMemoryUseThreadIdOrderByUse(String vmid) throws Exception{
		List<ThreadBean> result = new ArrayList<ThreadBean>();
		CommandExecutor executor = new LinuxCommandExecutor();
		Map<String, String> params = new HashMap<String, String>();
		params.put("vmid", vmid);
		List<String> threadDetails = executor.exec("ps.memory.use.thread", params);
		for (int i = 0; i < threadDetails.size(); i++) {
			String threadDetail = threadDetails.get(i).trim();
			if(i != 0 && threadDetail.contains("CPU") || threadDetail.contains("cpu")){
				continue;
			}
			String[] detailItems = threadDetail.split("\\s+");
			ThreadBean thread = new ThreadBean();
			thread.setPid(vmid);
			thread.setTid(detailItems[3]);
			thread.setCpuPercent(detailItems[0]);
			thread.setMemoryPercent(detailItems[1]);
			thread.setCpuTime(detailItems[4]);
			thread.setState(detailItems[6]);
			result.add(thread);
		}
		return result;
	}

}
