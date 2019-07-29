package org.jinspect.agent;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.util.jar.JarFile;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.jinspect.agent.asm.ThreadClassFileTransformer;

import sun.management.Agent;

import com.sun.management.ThreadMXBean;

public class JInspectAgent extends Agent {

    public static void agentmain(String paramString, Instrumentation inst) throws Exception {
        Agent.agentmain(paramString);
        String agentJarFile = "E:\\krm_workspace_jinspector\\jinspect\\jinspect-agent\\target\\jinspect-agent.jar";
        inst.appendToBootstrapClassLoaderSearch(new JarFile(agentJarFile));
        inst.appendToBootstrapClassLoaderSearch(new JarFile(
            "D:/apache-maven-3.5.3/repos/org/ow2/asm/asm/7.0/asm-7.0.jar"));
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("java.lang:type=Threading");
        mbs.unregisterMBean(name);
        if (!mbs.isRegistered(name)) {
            com.sun.management.ThreadMXBean tx = (ThreadMXBean)ManagementFactory.getThreadMXBean();
            mbs.registerMBean(tx, name);
        }
        System.out.println("000000");
        inst.addTransformer(new ThreadClassFileTransformer(), true);
        System.out.println("1111");
        inst.retransformClasses(Thread.class);
        System.out.println("22221");
    }
}
