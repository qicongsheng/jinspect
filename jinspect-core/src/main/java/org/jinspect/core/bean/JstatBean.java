package org.jinspect.core.bean;

import java.io.Serializable;

public class JstatBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String s0;
	private String s1;
	private String eden;
	private String old;
	private String permanent;
	private String youngGC;
	private String youngGCTime;
	private String fullGC;
	private String fullGCTime;
	private String gcTotalTime;
	
	public JstatBean() {
		
	}

	public JstatBean(String[] args) {
		this.s0 = args[0];
		this.s1 = args[1];
		this.eden = args[2];
		this.old = args[3];
		this.permanent = args[4];
		this.youngGC = args[5];
		this.youngGCTime = args[6];
		this.fullGC = args[7];
		this.fullGCTime = args[8];
		this.gcTotalTime = args[9];
	}

	public String getS0() {
		return s0;
	}

	public void setS0(String s0) {
		this.s0 = s0;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getEden() {
		return eden;
	}

	public void setEden(String eden) {
		this.eden = eden;
	}

	public String getOld() {
		return old;
	}

	public void setOld(String old) {
		this.old = old;
	}

	public String getPermanent() {
		return permanent;
	}

	public void setPermanent(String permanent) {
		this.permanent = permanent;
	}

	public String getYoungGC() {
		return youngGC;
	}

	public void setYoungGC(String youngGC) {
		this.youngGC = youngGC;
	}

	public String getYoungGCTime() {
		return youngGCTime;
	}

	public void setYoungGCTime(String youngGCTime) {
		this.youngGCTime = youngGCTime;
	}

	public String getFullGC() {
		return fullGC;
	}

	public void setFullGC(String fullGC) {
		this.fullGC = fullGC;
	}

	public String getFullGCTime() {
		return fullGCTime;
	}

	public void setFullGCTime(String fullGCTime) {
		this.fullGCTime = fullGCTime;
	}

	public String getGcTotalTime() {
		return gcTotalTime;
	}

	public void setGcTotalTime(String gcTotalTime) {
		this.gcTotalTime = gcTotalTime;
	}

}
