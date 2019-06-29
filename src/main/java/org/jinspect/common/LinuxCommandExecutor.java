package org.jinspect.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jinspect.util.ShellPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinuxCommandExecutor implements CommandExecutor {
	
	private static Logger logger = LoggerFactory.getLogger(LinuxCommandExecutor.class);
	private ShellPropertiesUtil shellProperties = ShellPropertiesUtil.getInstance();
	
	@Override
	public List<String> exec(String shell, Map<String, String> params) throws Exception {
		String sellContent = shellProperties.getProperty(shell);
		if(params != null && params.size() > 0){
			Iterator<Entry<String, String>> iter = params.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, String> entry = iter.next();
				sellContent = sellContent.replaceAll("#" + entry.getKey() + "#", entry.getValue());
			}
		}
		return execCommand(sellContent);
	}
	
	private List<String> execCommand(String command) throws Exception {
		List<String> result = new ArrayList<String>();
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(new String[] {"/bin/sh", "-c", command});
			proc.waitFor();
			InputStream in = proc.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while((line = read.readLine())!=null){
				result.add(line);
			}
		} catch (Exception e) {
			logger.error("Command execute error : [" + command + "]", e);
			throw e;
		}
		return result;
	}
	
}
