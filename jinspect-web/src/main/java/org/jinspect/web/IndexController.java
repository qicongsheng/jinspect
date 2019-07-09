package org.jinspect.web;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.service.IRuntimeMetrics;
import org.jinspect.service.impl.RuntimeMetricsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String welcome(HttpServletRequest request) throws Exception {
		return index(request);
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) throws Exception {
		return "index";
	}
	
	@RequestMapping("/demo")
	public String demo(HttpServletRequest request) throws Exception {
		String pid = request.getParameter("pid");
		IRuntimeMetrics runtimeMetrics = new RuntimeMetricsImpl();
		request.setAttribute("runtimeBean", runtimeMetrics.getRuntimeBean(pid));
		return "demo";
	}
}