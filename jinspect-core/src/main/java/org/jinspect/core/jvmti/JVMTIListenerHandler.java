package org.jinspect.core.jvmti;

import java.util.ArrayList;
import java.util.List;

public class JVMTIListenerHandler {
    private static JVMTIListenerHandler handler = new JVMTIListenerHandler();
    private List<ThreadStartupAndEndListener> threadListener = new ArrayList<ThreadStartupAndEndListener>();

    private JVMTIListenerHandler() {}

    public static JVMTIListenerHandler getHandler() {
        return handler;
    }

    public void onThreadStart(Thread thread) {
        for (ThreadStartupAndEndListener listener : threadListener) {
            listener.onThreadStart(thread);
        }
    }

    public void onThreadEnd(Thread thread) {
        for (ThreadStartupAndEndListener listener : threadListener) {
            listener.onThreadEnd(thread);
        }
    }

    /**
     * bind Thread listener
     * 
     * @param ThreadStartupListener
     */
    public void addThreadStartAndEndListener(ThreadStartupAndEndListener listener) {
        if (!threadListener.contains(listener)) {
            threadListener.add(listener);
        }
    }

    /**
     * clear Thread listener
     * 
     * @param ThreadStartupListener
     */
    public void cleanThreadStartAndEndListener() {
        threadListener.clear();
    }

}
