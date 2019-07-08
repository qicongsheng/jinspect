package org.jinspect.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jinspect.common.CommandExecutor;
import org.jinspect.common.LinuxCommandExecutor;
import org.jinspect.process.JavaProcessInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadInspector {
	
	private static Logger logger = LoggerFactory.getLogger(ThreadInspector.class);
	
	public String getThreadStackByTID(String vmid, String tid) throws Exception{
		StringBuilder result = new StringBuilder();
		CommandExecutor executor = new LinuxCommandExecutor();
		Map<String, String> params = new HashMap<String, String>();
		params.put("vmid", vmid);
		params.put("tid", Integer.toHexString(Integer.valueOf(tid)));
		List<String> stackDetails = executor.exec("jstack", params);
		for (int i = 0; i < stackDetails.size(); i++) {
			String stackDetail = stackDetails.get(i);
			if(i != 0 && stackDetail.contains("tid=") && stackDetail.contains("nid=")){
				break;
			}
			result.append(stackDetail);
			result.append("\r\n");
		}
		return result.toString();
	}
	
}
