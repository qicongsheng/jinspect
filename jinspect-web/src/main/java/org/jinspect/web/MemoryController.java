package org.jinspect.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.bean.ThreadBean;
import org.jinspect.memory.MemoryInspector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/memory")
public class MemoryController {
	
	@RequestMapping("/useStack")
	public String hello(HttpServletRequest request) throws Exception {
		String vmid = request.getParameter("vmid");
		MemoryInspector inspector = new MemoryInspector();
		List<ThreadBean> threads = inspector.getMemoryUseThreadIdOrderByUse(vmid);
		request.setAttribute("threads", threads);
		return "memory";
	}
}