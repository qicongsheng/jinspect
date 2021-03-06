package org.jinspect.agent;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.jar.JarFile;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.jinspect.probe.Probe;

import sun.management.Agent;

import com.sun.management.ThreadMXBean;

public class JInspectAgent extends Agent {

    private static final String ADVICEWEAVER = "org.jinspect.core.advisor.AdviceWeaver";
    private static final String ON_BEFORE = "methodOnBegin";
    private static final String ON_RETURN = "methodOnReturning";
    private static final String ON_THROWS = "methodOnThrowing";

    private static volatile ClassLoader jinspectClassLoader;

    public static void agentmain(String paramString, Instrumentation inst) throws Exception {
        try {
            Agent.agentmain(paramString);
            String agentJar = "E:/krm_workspace_jinspector/jinspect/jinspect-agent/target/jinspect-agent.jar";
            File agentJarFile = new File(agentJar);
            if (!agentJarFile.exists()) {
                System.out.println("Agent jar file does not exist: " + agentJarFile);
                return;
            }
            String probeJar = "E:/krm_workspace_jinspector/jinspect/jinspect-probe/target/jinspect-probe.jar";
            File probeJarFile = new File(probeJar);
            if (!probeJarFile.exists()) {
                System.out.println("probe jar file does not exist: " + probeJarFile);
                return;
            }

            final ClassLoader agentLoader = getClassLoader(inst, probeJarFile, agentJarFile);
            initProbe(agentLoader);

            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("java.lang:type=Threading");
            mbs.unregisterMBean(name);
            if (!mbs.isRegistered(name)) {
                com.sun.management.ThreadMXBean tx = (ThreadMXBean)ManagementFactory.getThreadMXBean();
                mbs.registerMBean(tx, name);
            }
            // for test
            Class testUtilClazz = jinspectClassLoader.loadClass("org.jinspect.core.util.advisor.TestUtil");
            Method m = testUtilClazz.getMethod("lazy", Instrumentation.class);
            m.invoke(null, inst);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private static ClassLoader getClassLoader(Instrumentation inst, File probeJarFile, File agentJarFile)
        throws Throwable {
        inst.appendToBootstrapClassLoaderSearch(new JarFile(probeJarFile));
        return loadOrDefineClassLoader(agentJarFile);
    }

    private static ClassLoader loadOrDefineClassLoader(File agentJar) throws Throwable {
        if (jinspectClassLoader == null) {
            String coreJar =
                "E:/krm_workspace_jinspector/jinspect/jinspect-core/target/jinspect-core-jar-with-dependencies.jar";
            jinspectClassLoader =
                new JInspectClassloader(new URL[] {agentJar.toURI().toURL(), new File(coreJar).toURI().toURL()});
        }
        return jinspectClassLoader;
    }

    private static void initProbe(ClassLoader classLoader) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> adviceWeaverClass = classLoader.loadClass(ADVICEWEAVER);
        Method onBefore =
            adviceWeaverClass.getMethod(ON_BEFORE, int.class, ClassLoader.class, String.class, String.class,
                String.class, Object.class, Object[].class);
        Method onReturn = adviceWeaverClass.getMethod(ON_RETURN, Object.class);
        Method onThrows = adviceWeaverClass.getMethod(ON_THROWS, Throwable.class);
        Probe.initForAgentLauncher(classLoader, onBefore, onReturn, onThrows, null, null, null, null);
    }
}
