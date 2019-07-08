package org.jinspect.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.bean.GCDetailBean;
import org.jinspect.gc.GCInspector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gc")
public class GCController {
	
	@RequestMapping("/jstat")
	public String jstat(HttpServletRequest request) throws Exception {
		String vmid = request.getParameter("vmid");
		String interval = request.getParameter("interval");
		String count = request.getParameter("count");
		GCInspector inspector = new GCInspector();
		List<GCDetailBean> gcDetails = inspector.getGCDetail(vmid, interval, count);
		request.setAttribute("gcDetails", gcDetails);
		return "gc";
	}
}