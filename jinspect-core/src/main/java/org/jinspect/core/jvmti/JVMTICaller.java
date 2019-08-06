package org.jinspect.core.jvmti;

import java.util.ArrayList;
import java.util.List;

public class JVMTICaller {

    private static JVMTICaller caller = new JVMTICaller();
    private List<ThreadStartupListener> threadListener = new ArrayList<ThreadStartupListener>();

    private JVMTICaller() {
        bindJVMTIThreadStartupEvent();
    }

    public static JVMTICaller getCaller() {
        return caller;
    }

    private void bindJVMTIThreadStartupEvent() {

    }

    public native void threadStartupCallback(List<ThreadStartupListener> threadListener);

    /**
     * bind Thread listener
     * 
     * @param ThreadStartupListener
     */
    public void addThreadStartupListener(ThreadStartupListener listener) {
        if (!threadListener.contains(listener)) {
            threadListener.add(listener);
        }
    }

    /**
     * clear Thread listener
     * 
     * @param ThreadStartupListener
     */
    public void cleanThreadStartupListener() {
        threadListener.clear();
    }

}
