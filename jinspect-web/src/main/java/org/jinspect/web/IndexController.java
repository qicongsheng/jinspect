package org.jinspect.web;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.core.service.IOperatingSystemMetrics;
import org.jinspect.core.service.IRuntimeMetrics;
import org.jinspect.core.service.impl.OperatingSystemMetricsImpl;
import org.jinspect.core.service.impl.RuntimeMetricsImpl;
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
		IOperatingSystemMetrics osMetrics = new OperatingSystemMetricsImpl();
		IRuntimeMetrics runtimeMetrics = new RuntimeMetricsImpl();
		request.setAttribute("osBean", osMetrics.getOperatingSystemBean(pid));
		request.setAttribute("runtimeBean", runtimeMetrics.getRuntimeBean(pid));
		return "demo";
	}
}