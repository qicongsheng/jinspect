package org.jinspect.top;

import java.util.HashMap;
import java.util.List;

import org.jinspect.common.CommandExecutor;
import org.jinspect.common.LinuxCommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopInspector {
	
	private static Logger logger = LoggerFactory.getLogger(TopInspector.class);
	
	public String getTopInfo() throws Exception{
		StringBuffer result = new StringBuffer();
		CommandExecutor executor = new LinuxCommandExecutor();
		List<String> topDetails = executor.exec("top.detail", new HashMap<String, String>());
		for (int i = 0; i < topDetails.size(); i++) {
			String topDetail = topDetails.get(i).trim();
			if("".equals(topDetail) || topDetail == null){
				break;
			}
			result.append(topDetail);
		}
		return result.toString();
	}

}
