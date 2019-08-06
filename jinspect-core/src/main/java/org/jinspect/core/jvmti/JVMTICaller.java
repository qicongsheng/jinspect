package org.jinspect.core.jvmti;

public class JVMTICaller {

    private static JVMTICaller caller = new JVMTICaller();
    private JVMTIListenerHandler listenerHandler = JVMTIListenerHandler.getHandler();

    private JVMTICaller() {
        threadStartAndEndCallback(listenerHandler);
    }

    public static JVMTICaller getCaller() {
        return caller;
    }

    public native void threadStartAndEndCallback(JVMTIListenerHandler listenerHandler);

}
