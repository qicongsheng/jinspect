package org.jinspect.core.util.advisor;

import java.lang.instrument.Instrumentation;

import org.jinspect.JInspectAgent;
import org.jinspect.core.advisor.Enhancer;
import org.jinspect.core.command.ThreadStartupAdviceListener;

public class TestUtil {

    public static void lazy(Instrumentation inst) {
        try {
            System.out.println("000000");
            Class xe = Class.forName("cn.david.scrat.profiler.enhance.AdviceListener");
            System.out.println(xe);
            Enhancer transformer = new Enhancer();
            // Class fooClass = Class.forName("org.jinspect.agent.Foo");
            Class fooClass = Class.forName("java.lang.Thread");
            transformer.setTargetClass(fooClass);
            transformer.setAdviceListener(new ThreadStartupAdviceListener());
            inst.addTransformer(transformer, true);
            System.out.println("1111");
            inst.retransformClasses(fooClass);
            System.out.println("cc : " + fooClass);
            System.out.println("Thread classloader: " + Thread.class.getClassLoader());
            System.out.println("JInspectAgent classloader: " + JInspectAgent.class.getClassLoader());
            System.out.println("Probe classloader: " + Class.forName("org.jinspect.probe.Probe1").getClassLoader());
            System.out.println("22221");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
