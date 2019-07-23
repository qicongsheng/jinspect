package org.jinspect.agent;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import sun.management.Agent;

import com.sun.management.ThreadMXBean;

public class JInspectAgent extends Agent {

    public static void agentmain(String paramString, Instrumentation inst) throws Exception {

        Agent.agentmain(paramString);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("java.lang:type=Threading");
        mbs.unregisterMBean(name);
        if (!mbs.isRegistered(name)) {
            com.sun.management.ThreadMXBean tx = (ThreadMXBean)ManagementFactory.getThreadMXBean();
            mbs.registerMBean(tx, name);
        }

    }

}
