package org.jinspect.common;

import java.util.List;
import java.util.Map;

/**
 * 系统命令执行器
 * @author qicongsheng
 *
 */
public interface CommandExecutor {

	List<String> exec(String shell, Map<String, String> params) throws Exception;
	
}
