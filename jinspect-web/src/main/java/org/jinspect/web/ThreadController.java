package org.jinspect.web;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.core.service.IThreadMetrics;
import org.jinspect.core.service.impl.ThreadMetricsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thread")
public class ThreadController {
	
	@RequestMapping("/rank")
	public String index(HttpServletRequest request) throws Exception {
		String pid = request.getParameter("pid");
		IThreadMetrics threadMetrics = new ThreadMetricsImpl();
		request.setAttribute("threadInfos", threadMetrics.getThreadInfos(pid));
		return "thread";
	}
}