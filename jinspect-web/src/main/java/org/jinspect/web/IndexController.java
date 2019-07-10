package org.jinspect.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.core.service.IOperatingSystemMetrics;
import org.jinspect.core.service.IRuntimeMetrics;
import org.jinspect.core.service.impl.OperatingSystemMetricsImpl;
import org.jinspect.core.service.impl.RuntimeMetricsImpl;
import org.jinspect.web.dto.JVMSummaryDTO;
import org.jinspect.web.service.IJVMSummaryService;
import org.jinspect.web.service.impl.JVMSummaryServiceImpl;
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
		IJVMSummaryService jvmSummaryService = new JVMSummaryServiceImpl();
		List<JVMSummaryDTO> summaries = jvmSummaryService.getJVMSummaryByPid(osMetrics.getActiveVms());
		request.setAttribute("summaries", summaries);
		if(summaries.size() > 0){
			request.setAttribute("osBean", summaries.get(0).getOsBean());
		}
		return "demo";
	}
}