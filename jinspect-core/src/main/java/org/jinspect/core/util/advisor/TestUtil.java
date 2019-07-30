package org.jinspect.core.util.advisor;

import java.lang.instrument.Instrumentation;

import org.jinspect.core.advisor.Enhancer;
import org.jinspect.core.command.ThreadStartupAdviceListener;

public class TestUtil {

    public static void lazy(Instrumentation inst) {
        try {
            Enhancer transformer = new Enhancer();
            Class threadClass = Class.forName("java.lang.Thread");
            transformer.setTargetClass(threadClass);
            transformer.setAdviceListener(new ThreadStartupAdviceListener());
            inst.addTransformer(transformer, true);
            inst.retransformClasses(threadClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
