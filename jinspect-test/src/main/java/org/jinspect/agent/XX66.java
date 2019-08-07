package org.jinspect.agent;

import java.io.IOException;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class XX66 {

    /**
     * @param args
     * @throws IOException
     * @throws AttachNotSupportedException
     * @throws AgentInitializationException
     * @throws AgentLoadException
     */
    public static void main(String[] args) throws AttachNotSupportedException, IOException, AgentLoadException,
        AgentInitializationException {
        System.out.println(Thread.class.getClassLoader());
        String pid = null;
        VirtualMachineDescriptor virtualMachineDescriptor = null;
        for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
            String descPid = descriptor.id();
            String name = descriptor.displayName();
            System.out.println("XX main: " + name);
            if (name.contains("ThreadTest")) {
                virtualMachineDescriptor = descriptor;
                pid = descPid;
            }
        }

        VirtualMachine virtualMachine = null;
        try {
            if (null == virtualMachineDescriptor) { // 使用 attach(String pid) 这种方式
                virtualMachine = VirtualMachine.attach("" + pid);
            } else {
                virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
            }
            virtualMachine
                .loadAgent("E:\\krm_workspace_jinspector\\jinspect\\jinspect-agent\\target\\jinspect-agent.jar");

        } finally {
            if (null != virtualMachine) {
                virtualMachine.detach();
            }
        }
    }

}
