package org.jinspect.gc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jinspect.bean.GCDetailBean;
import org.jinspect.common.CommandExecutor;
import org.jinspect.common.LinuxCommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GCInspector {
	
	private static Logger logger = LoggerFactory.getLogger(GCInspector.class);
	
	public List<GCDetailBean> getGCDetail(String vmid, String interval, String count) throws Exception{
		List<GCDetailBean> result = new ArrayList<GCDetailBean>();
		CommandExecutor executor = new LinuxCommandExecutor();
		Map<String, String> params = new HashMap<String, String>();
		params.put("vmid", vmid);
		params.put("interval", interval);
		params.put("count", count);
		List<String> gcDetails = executor.exec("gc.detail", params);
		for (int i = 0; i < gcDetails.size(); i++) {
			String gcDetail = gcDetails.get(i).trim();
			if(gcDetail.contains("YGC") || gcDetail.contains("ygc")){
				continue;
			}
			String[] detailItems = gcDetail.split("\\s+");
			GCDetailBean detail = new GCDetailBean();
			detail.setPid(vmid);
			detail.setSurvivor0(detailItems[0]);
			detail.setSurvivor1(detailItems[1]);
			detail.setEden(detailItems[2]);
			detail.setOld(detailItems[3]);
			detail.setPermanent(detailItems[4]);
			detail.setYoungGC(detailItems[5]);
			detail.setYoungGCTime(detailItems[6]);
			detail.setFullGC(detailItems[7]);
			detail.setFullGCTime(detailItems[8]);
			detail.setGcTotalTime(detailItems[9]);
			result.add(detail);
		}
		return result;
	}

}
