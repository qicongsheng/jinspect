package org.jinspect.core.common;

import org.jinspect.core.bean.JstatBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.VmIdentifier;
import sun.tools.jstat.Arguments;
import sun.tools.jstat.OptionFormat;
import sun.tools.jstat.OptionOutputFormatter;
import sun.tools.jstat.OutputFormatter;

public class Jstat {
	
	private static Logger logger = LoggerFactory.getLogger(Jstat.class);

	public static JstatBean gcutil(String vmId) throws MonitorException {
		JstatBean result = null;
		MonitoredHost monitoredHost = null;
		MonitoredVm monitoredVm = null;
		try {
			Arguments arguments = new Arguments(new String[] { "-gcutil", vmId });
			VmIdentifier vmIdentifier = arguments.vmId();
			int interval = arguments.sampleInterval();
			monitoredHost = MonitoredHost.getMonitoredHost(vmIdentifier);
			monitoredVm = monitoredHost.getMonitoredVm(vmIdentifier, interval);
			OptionFormat format = arguments.optionFormat();
			OutputFormatter formatter = new OptionOutputFormatter(monitoredVm, format);
			String rowData = formatter.getRow();
			result = new JstatBean(rowData.trim().split("\\s+"));
			// detach from host events and from the monitored target jvm
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			monitoredHost.detach(monitoredVm);
		}
		return result;
	}

}
