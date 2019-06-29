package org.jinspect.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.common.CommandExecutor;
import org.jinspect.common.LinuxCommandExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cpu")
public class CPUController {
	
	@RequestMapping("/busyStack")
	public String hello(HttpServletRequest request) throws Exception {
		String vmid = request.getParameter("vmid");
		CommandExecutor executor = new LinuxCommandExecutor();
		Map<String, String> params = new HashMap<String, String>();
		params.put("vmid", vmid);
		String stack = executor.exec("ps.cpu.busy.thread", params);
		request.setAttribute("stack", stack);
		return "index";
	}
}