package org.jinspect.web;

import java.util.ArrayList;
import java.util.List;

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
		List<String> stacks = inspector.getThreadStackByTID(vmid, tid);
		System.out.println("个数" + stacks.size());
		request.setAttribute("stacks", stacks);
		return "stack";
	}
}