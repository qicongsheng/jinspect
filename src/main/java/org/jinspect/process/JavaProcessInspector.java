package org.jinspect.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jinspect.bean.JavaProcessBean;
import org.jinspect.common.CommandExecutor;
import org.jinspect.common.LinuxCommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaProcessInspector {
	
	private static Logger logger = LoggerFactory.getLogger(JavaProcessInspector.class);
	
	public List<JavaProcessBean> getLiveJavaProcess() throws Exception{
		List<JavaProcessBean> result = new ArrayList<JavaProcessBean>();
		CommandExecutor executor = new LinuxCommandExecutor();
		List<String> processDetails = executor.exec("process.live", new HashMap<String, String>());
		List<String> jpsIds = executor.exec("jps", new HashMap<String, String>());
		for (int i = 0; i <jpsIds.size(); i++) {
			jpsIds.set(i, jpsIds.get(i).trim().split("\\s+")[0]);
		}
		
		for (int i = 0; i < processDetails.size(); i++) {
			String processDetail = processDetails.get(i).trim();
			String[] detailItems = processDetail.split("\\s+");
			String pid = detailItems[1];
			if(!jpsIds.contains(pid)){
				continue;
			}
			JavaProcessBean javaProcess = new JavaProcessBean();
			javaProcess.setStartUser(detailItems[0]);
			javaProcess.setProcessId(detailItems[1]);
			javaProcess.setStartTime(detailItems[6]);
			javaProcess.setStartCommand(detailItems[7]);
			StringBuilder paramBuilder = new StringBuilder();
			for (int j = 8; j < detailItems.length; j++) {
				paramBuilder.append(detailItems[j]);
			}
			javaProcess.setParams(paramBuilder.toString());
			result.add(javaProcess);
		}
		return result;
	}
	
}
