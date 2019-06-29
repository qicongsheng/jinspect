package org.jinspect.web;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.thread.ThreadInspector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thread")
public class ThreadController {
	
	@RequestMapping("/stack")
	public String hello(HttpServletRequest request) throws Exception {
		String vmid = request.getParameter("vmid");
		String tid = request.getParameter("tid");
		ThreadInspector inspector = new ThreadInspector();
		String stack = inspector.getThreadStackByTID(vmid, tid);
		request.setAttribute("vmid", vmid);
		request.setAttribute("tid", tid);
		request.setAttribute("stack", stack);
		return "stack";
	}
}