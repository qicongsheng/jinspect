package org.jinspect.bean;

import java.io.Serializable;

public class GCDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pid;
	private String survivor0;
	private String survivor1;
	private String eden;
	private String old;
	private String permanent;
	private String youngGC;
	private String youngGCTime;
	private String fullGC;
	private String fullGCTime;
	private String gcTotalTime;

	public String getSurvivor0() {
		return survivor0;
	}

	public void setSurvivor0(String survivor0) {
		this.survivor0 = survivor0;
	}

	public String getSurvivor1() {
		return survivor1;
	}

	public void setSurvivor1(String survivor1) {
		this.survivor1 = survivor1;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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
