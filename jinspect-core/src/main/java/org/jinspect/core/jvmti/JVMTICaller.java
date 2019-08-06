package org.jinspect.core.jvmti;

/**
 * 
 * @author Administrator
 * @date 2019-8-6
 */
public class JVMTICaller {

    private static JVMTICaller caller = new JVMTICaller();
    private JVMTIListenerHandler listenerHandler = JVMTIListenerHandler.getHandler();
    
    private JVMTICaller() {
        System.load("/root/call.so");
        threadStartAndEndCallback(listenerHandler);
    }

    public static JVMTICaller getCaller() {
        return caller;
    }

    public native void threadStartAndEndCallback(JVMTIListenerHandler listenerHandler);

}
