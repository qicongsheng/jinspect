package org.jprobe.common;

/**
 * 系统命令执行器
 * @author qicongsheng
 *
 */
public interface CommandExecutor {

	String exec(String shell, String[] params);
	
}
