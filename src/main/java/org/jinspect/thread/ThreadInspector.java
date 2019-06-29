package org.jinspect.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jinspect.common.CommandExecutor;
import org.jinspect.common.LinuxCommandExecutor;

public class ThreadInspector {
	
	public List<String> getThreadStackByTID(String vmid, String tid) throws Exception{
		List<String> result = new ArrayList<String>();
		CommandExecutor executor = new LinuxCommandExecutor();
		Map<String, String> params = new HashMap<String, String>();
		params.put("vmid", vmid);
		params.put("tid", Integer.toHexString(Integer.valueOf(tid)));
		List<String> stackDetails = executor.exec("jstack", params);
		for (int i = 0; i < stackDetails.size(); i++) {
			String stackDetail = stackDetails.get(i).trim();
			System.out.println(stackDetail);
			if(i != 0 && stackDetail.contains("tid=") && stackDetail.contains("nid=")){
				System.out.println("停止");
				break;
			}
			result.add(stackDetail);
		}
		return result;
	}
	
}
