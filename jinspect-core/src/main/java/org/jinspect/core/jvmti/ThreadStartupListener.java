package org.jinspect.core.jvmti;

public interface ThreadStartupListener {

    void onThreadStart(Thread thread);

    void onThreadEnd(Thread thread);

}
