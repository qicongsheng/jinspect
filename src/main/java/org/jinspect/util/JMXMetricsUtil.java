package org.jinspect.util;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import sun.management.ConnectorAddressLink;
import sun.tools.attach.HotSpotVirtualMachine;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class JMXMetricsUtil {
	
	private static final ObjectName MBEAN_OPERATING_SYSTEM = createObjectName("java.lang:type=OperatingSystem");
	private static final ObjectName MBEAN_MEMORY = createObjectName("java.lang:type=Memory");
	public static final ObjectName MBEAN_THREADING = createObjectName("sun.management:type=Threading");
	
	public static MBeanServerConnection getLocalServerConnection(String pid) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
		VirtualMachine vm = VirtualMachine.attach(pid);
		vm.loadAgent("E:\\krm_workspace\\jinspect-agent\\target\\jinspect-agent.jar", "com.sun.management.jmxremote");
		
		int intPid = Integer.parseInt(pid);
		JMXServiceURL url = getLocalStubServiceURLFromPID(intPid);
		if (url == null) {
			executeCommandForPID(vm, pid, "ManagementAgent.start_local");
			url = getLocalStubServiceURLFromPID(intPid);
		}
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
		return jmxc.getMBeanServerConnection();
	}

	private static double getHeapUsage(MBeanServerConnection connection) throws MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException, IOException {
		return ((Long) ((CompositeData) connection.getAttribute(MBEAN_MEMORY, "HeapMemoryUsage")).get("used")) / 1024.0 / 1024.0;
	}

	private static double getJVMCpuLoad(MBeanServerConnection connection) throws MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException, IOException {
		return ((Double) connection.getAttribute(MBEAN_OPERATING_SYSTEM, "ProcessCpuLoad")) * 100.0;
	}

	private static void executeCommandForPID(VirtualMachine vm, String pid, String cmd) throws IOException {
		HotSpotVirtualMachine hsvm = (HotSpotVirtualMachine) vm;
		hsvm.executeJCmd(cmd);
	}

	private static JMXServiceURL getLocalStubServiceURLFromPID(int pid) throws IOException {
		String address = ConnectorAddressLink.importFrom(pid);
		if (address != null) {
			return new JMXServiceURL(address);
		}
		return null;
	}

	private static ObjectName createObjectName(String string) {
		try {
			return new ObjectName(string);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
			return null;
		}
	}

}
