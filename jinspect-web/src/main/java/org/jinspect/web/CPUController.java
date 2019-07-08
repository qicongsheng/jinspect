package org.jinspect.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.bean.ThreadBean;
import org.jinspect.cpu.CPUInspector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cpu")
public class CPUController {
	
	@RequestMapping("/busyStack")
	public String hello(HttpServletRequest request) throws Exception {
		String vmid = request.getParameter("vmid");
		CPUInspector inspector = new CPUInspector();
		List<ThreadBean> threads = inspector.getCpuUseThreadIdOrderByUse(vmid);
		request.setAttribute("threads", threads);
		return "cpu";
	}
}