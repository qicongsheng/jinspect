package org.jinspect.core.jvmti;

public interface ThreadStartupAndEndListener {

    void onThreadStart(Thread thread);

    void onThreadEnd(Thread thread);

}
