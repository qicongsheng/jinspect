package org.jinspect.core.jvmti;

/**
 * 
 * @author Administrator
 * @date 2019-8-6
 */
public class JVMTICaller {

    private static JVMTICaller caller = new JVMTICaller();

    private JVMTICaller() {}

    public static JVMTICaller getCaller() {
        return caller;
    }

    public native void threadStartAndEndCallback(JVMTIListenerHandler listenerHandler);

    public void loadNativeLib(String lib) {
        System.load(lib);
    }

    public void bindJVMTIListenerHandler(JVMTIListenerHandler listenerHandler) {
        threadStartAndEndCallback(listenerHandler);
    }

}
