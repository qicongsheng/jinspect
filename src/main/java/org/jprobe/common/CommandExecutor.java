package org.jprobe.common;

import java.util.Map;

/**
 * 系统命令执行器
 * @author qicongsheng
 *
 */
public interface CommandExecutor {

	String exec(String shell, Map<String, String> params) throws Exception;
	
}
