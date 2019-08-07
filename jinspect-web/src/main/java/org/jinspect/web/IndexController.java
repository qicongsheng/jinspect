package org.jinspect.web;

import javax.servlet.http.HttpServletRequest;

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
        String pid = request.getParameter("pid");
        // IOperatingSystemMetrics osMetrics = new OperatingSystemMetricsImpl();
        // IJVMSummaryService jvmSummaryService = new JVMSummaryServiceImpl();
        // List<JVMSummaryDTO> summaries = jvmSummaryService.getJVMSummaryByPid(osMetrics.getActiveVms());
        // request.setAttribute("summaries", summaries);
        // if(summaries.size() > 0){
        // request.setAttribute("osBean", summaries.get(0).getOsBean());
        // }
        return "index";
    }
}