package org.jinspect.probe;

import java.lang.reflect.Method;

public class Probe {

    // -- ����Advice�Ĺ������� --
    public static volatile Method ON_BEFORE_METHOD;
    public static volatile Method ON_RETURN_METHOD;
    public static volatile Method ON_THROWS_METHOD;
    public static volatile Method BEFORE_INVOKING_METHOD;
    public static volatile Method AFTER_INVOKING_METHOD;
    public static volatile Method THROW_INVOKING_METHOD;

    /**
     * arthas's classloader ����
     */
    public static volatile ClassLoader CLASSLOADER;

    /**
     * �������跽��
     */
    public static volatile Method AGENT_RESET_METHOD;

    /**
     * ������ͨ�ļ����ʼ��
     */
    public static void init(
            ClassLoader classLoader,
            Method onBeforeMethod,
            Method onReturnMethod,
            Method onThrowsMethod,
            Method beforeInvokingMethod,
            Method afterInvokingMethod,
            Method throwInvokingMethod) {
        CLASSLOADER = classLoader;
        ON_BEFORE_METHOD = onBeforeMethod;
        ON_RETURN_METHOD = onReturnMethod;
        ON_THROWS_METHOD = onThrowsMethod;
        BEFORE_INVOKING_METHOD = beforeInvokingMethod;
        AFTER_INVOKING_METHOD = afterInvokingMethod;
        THROW_INVOKING_METHOD = throwInvokingMethod;
    }

    /**
     * ���������̳߳�ʼ��
     */
    public static void initForAgentLauncher(
            ClassLoader classLoader,
            Method onBeforeMethod,
            Method onReturnMethod,
            Method onThrowsMethod,
            Method beforeInvokingMethod,
            Method afterInvokingMethod,
            Method throwInvokingMethod,
            Method agentResetMethod) {
        CLASSLOADER = classLoader;
        ON_BEFORE_METHOD = onBeforeMethod;
        ON_RETURN_METHOD = onReturnMethod;
        ON_THROWS_METHOD = onThrowsMethod;
        BEFORE_INVOKING_METHOD = beforeInvokingMethod;
        AFTER_INVOKING_METHOD = afterInvokingMethod;
        THROW_INVOKING_METHOD = throwInvokingMethod;
        AGENT_RESET_METHOD = agentResetMethod;
    }

    /**
     * Clean up the reference to com.taobao.arthas.agent.AgentLauncher$1
     * to avoid classloader leak.
     */
    public static void destroy() {
        CLASSLOADER = null;
        ON_BEFORE_METHOD = null;
        ON_RETURN_METHOD = null;
        ON_THROWS_METHOD = null;
        BEFORE_INVOKING_METHOD = null;
        AFTER_INVOKING_METHOD = null;
        THROW_INVOKING_METHOD = null;
        // clear the reference to ArthasClassLoader in AgentLauncher
        if (AGENT_RESET_METHOD != null) {
            try {
                AGENT_RESET_METHOD.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AGENT_RESET_METHOD = null;
    }

}