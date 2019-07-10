package org.jinspect.core.common;

import java.io.File;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.management.ConnectorAddressLink;
import sun.tools.attach.HotSpotVirtualMachine;

import com.sun.management.OperatingSystemMXBean;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class JMXMetricsFactory {
	
	private static Logger logger = LoggerFactory.getLogger(JMXMetricsFactory.class);

	private static String AGENT_JAR_PATH = null;
	private static Map<String, MBeanServerConnection> pidConnectionMap = new ConcurrentHashMap<String, MBeanServerConnection>();
	private static Map<String, MBeanServerConnection> ipPortConnectionMap = new ConcurrentHashMap<String, MBeanServerConnection>();
	
	private static MBeanServerConnection getLocalServerConnection(String pid) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
		MBeanServerConnection conn = pidConnectionMap.get(pid);
		if(conn == null){
			VirtualMachine vm = VirtualMachine.attach(pid);
			int intPid = Integer.parseInt(pid);
			JMXServiceURL url = getLocalStubServiceURLFromPID(intPid);
			if (url == null) {
				String agentJarPath = getAgentJarPath();
				logger.info("loadAgent : {}", agentJarPath);
				vm.loadAgent(agentJarPath, "com.sun.management.jmxremote");
				executeCommandForPID(vm, pid, "ManagementAgent.start_local");
				url = getLocalStubServiceURLFromPID(intPid);
			}
			logger.info("JMXServiceURL : {}", url.getURLPath());
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			conn = jmxc.getMBeanServerConnection();
			pidConnectionMap.put(pid, conn);
		}
		return conn;
	}
	
	private static MBeanServerConnection getLocalServerConnection(String ip, int port) throws IOException {
		String cacheKey = ip + ":" + port;
		MBeanServerConnection conn = ipPortConnectionMap.get(cacheKey);
		if(conn == null){
			String serviceUrl = "service:jmx:rmi:///jndi/rmi://" + cacheKey + "/jmxrmi";
			JMXServiceURL url = new JMXServiceURL(serviceUrl);
			logger.info("JMXServiceURL : {}", url.getURLPath());
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			conn = jmxc.getMBeanServerConnection();
			ipPortConnectionMap.put(cacheKey, conn);
		}
		return conn;
	}
	
	public static com.sun.management.ThreadMXBean getThreadMXBean(String pid) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException{
		return ManagementFactory.newPlatformMXBeanProxy(JMXMetricsFactory.getLocalServerConnection(pid), "java.lang:type=Threading", com.sun.management.ThreadMXBean.class);
	}
	
	public static MemoryMXBean getMemoryMXBean(String pid) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException{
		return ManagementFactory.getPlatformMXBean(JMXMetricsFactory.getLocalServerConnection(pid), MemoryMXBean.class);
	}
	
	public static RuntimeMXBean getRuntimeMXBean(String pid) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException{
		return ManagementFactory.getPlatformMXBean(JMXMetricsFactory.getLocalServerConnection(pid), RuntimeMXBean.class);
	}
	
	public static OperatingSystemMXBean getOperatingSystemMXBean(String pid) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException{
		return ManagementFactory.getPlatformMXBean(JMXMetricsFactory.getLocalServerConnection(pid), OperatingSystemMXBean.class);
	}
	
	public static List<GarbageCollectorMXBean> getGarbageCollectorMXBeans(String pid) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException{
		return ManagementFactory.getPlatformMXBeans(JMXMetricsFactory.getLocalServerConnection(pid), GarbageCollectorMXBean.class);
	}
	
	public static com.sun.management.HotSpotDiagnosticMXBean getHotSpotDiagnosticMXBean(String pid) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException{
		return ManagementFactory.getPlatformMXBean(JMXMetricsFactory.getLocalServerConnection(pid), com.sun.management.HotSpotDiagnosticMXBean.class);
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

	public static synchronized String getAgentJarPath(){
		if(null == JMXMetricsFactory.AGENT_JAR_PATH || "".equals(JMXMetricsFactory.AGENT_JAR_PATH)){
			String userDir = System.getProperty("user.dir");
			String agentJarPath = userDir + "/lib/jinspect-agent.jar";
			if(!new File(agentJarPath).exists()){
				agentJarPath = userDir + "/src/main/resources/assembly/lib/jinspect-agent.jar";
			}
			JMXMetricsFactory.AGENT_JAR_PATH = agentJarPath;
		}
		return JMXMetricsFactory.AGENT_JAR_PATH;
	}
	
	public static synchronized void setAgentJarPath(String agentJarPath){
		JMXMetricsFactory.AGENT_JAR_PATH = agentJarPath;
	}

}
