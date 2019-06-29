package org.jinspect.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		List<String> threadIds = inspector.getCpuUseThreadIdOrderByUse(vmid);
		System.out.println("个数" + threadIds.size());
		request.setAttribute("threadIds", threadIds);
		return "cpu";
	}
}