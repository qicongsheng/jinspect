package org.jinspect.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jinspect.bean.JavaProcessBean;
import org.jinspect.process.JavaProcessInspector;
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
		JavaProcessInspector inspector = new JavaProcessInspector();
		List<JavaProcessBean> javaProcess = inspector.getLiveJavaProcess();
		request.setAttribute("javaProcess", javaProcess);
		return "index";
	}
}