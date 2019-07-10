package org.jinspect.core.bean;

import java.io.Serializable;
import java.lang.Thread.State;

public class ThreadInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private long threadId;
	private String threadName;
	private State threadState;
	private long threadAllocatedBytes;
	private long threadCpuTime;
	private long threadUserTime;
	private StackTraceElement[] stackTraces;

	public long getThreadId() {
		return threadId;
	}

	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public State getThreadState() {
		return threadState;
	}

	public void setThreadState(State threadState) {
		this.threadState = threadState;
	}

	public long getThreadAllocatedBytes() {
		return threadAllocatedBytes;
	}

	public void setThreadAllocatedBytes(long threadAllocatedBytes) {
		this.threadAllocatedBytes = threadAllocatedBytes;
	}

	public long getThreadCpuTime() {
		return threadCpuTime;
	}

	public void setThreadCpuTime(long threadCpuTime) {
		this.threadCpuTime = threadCpuTime;
	}

	public long getThreadUserTime() {
		return threadUserTime;
	}

	public void setThreadUserTime(long threadUserTime) {
		this.threadUserTime = threadUserTime;
	}

	public StackTraceElement[] getStackTraces() {
		return stackTraces;
	}

	public void setStackTraces(StackTraceElement[] stackTraces) {
		this.stackTraces = stackTraces;
	}

}
