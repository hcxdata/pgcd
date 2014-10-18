package com.jetyun.pgcd.dao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.jetyun.pgcd.dao.domain.ContentInfo;
import com.jetyun.pgcd.util.DateUtil;
import com.jetyun.pgcd.util.hbase.HRMTable;
import com.jetyun.pgcd.util.hbase.HRMTable.TYPES;
import com.jetyun.pgcd.web.domain.HTimeLineDate;
import com.jetyun.pgcd.web.domain.HTimeLineDateAsset;
import com.jetyun.pgcd.web.domain.VTimeline;

@Component
public class ContentInfoDao extends BasicDao {
	
	public List<VTimeline> getVTimeLineList(String eventID) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("eventID", eventID);
		List<VTimeline> vtlList = new ArrayList<VTimeline>();
	 	HRMTable ht = new HRMTable("webpage_new");
		List<ContentInfo>  list= this.queryForList("contentinfo.getContentInfoListByEventID", map);
		if(!CollectionUtils.isEmpty(list)){
			Iterator<ContentInfo> it = list.iterator();
			ContentInfo ci = null;
			while(it.hasNext()){
				ci = it.next();
				VTimeline vtl = new VTimeline();
				vtl.setType("blog_post");
				vtl.setDate(ci.getTim());
				vtl.setDateFormat("HH:mm");
				vtl.setTitle(ci.getTitle());
//				htl.setImage("http://www.people.com.cn/mediafile/pic/GQ/20141013/94/8948334366490436494.jpg");
				Map<String,TYPES> argMap = new HashMap<String,TYPES>();
				Map<String, String> rmap = ht.getRowValues(ci.getRowkey(),argMap);
				if(rmap.size()>0){
					if(rmap.containsKey("mtdt:description") && rmap.get("mtdt:description").toString().length()>0 ){
						vtl.setContent(rmap.get("mtdt:description").toString());
					}else{
						vtl.setContent(StringUtils.substring(rmap.get("mtdt:page_content").toString(), 0, 300));
					}
//					htl.setContent(StringUtils.substring(rmap.get("mtdt:page_content").toString(), 0, 100)+"...");
				}
				vtlList.add(vtl);
				ci = null;
			}
		}
		return vtlList;
	}
	public List<HTimeLineDate> getHTimeLineDateList(String eventID) throws IOException, ParseException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("eventID", eventID);
		List<HTimeLineDate> htldList = new ArrayList<HTimeLineDate>();
	 	HRMTable ht = new HRMTable("webpage_new");
		List<ContentInfo>  list= this.queryForList("contentinfo.getContentInfoListByEventID", map);
		if(!CollectionUtils.isEmpty(list)){
			Iterator<ContentInfo> it = list.iterator();
			ContentInfo ci = null;
			while(it.hasNext()){
				ci = it.next();
				HTimeLineDate htld = new HTimeLineDate();
				
				SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
				dateParser.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
				Date d = dateParser.parse(ci.getTim());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
				htld.setStartDate(sdf.format(d));
				htld.setEndDate(sdf.format(d));
				htld.setHeadline(ci.getTitle());
				Map<String,TYPES> argMap = new HashMap<String,TYPES>();
				Map<String, String> rmap = ht.getRowValues(ci.getRowkey(),argMap);
				if(rmap.size()>0){
					if(rmap.containsKey("mtdt:description") && rmap.get("mtdt:description").toString().length()>0 ){
						htld.setText(rmap.get("mtdt:description").toString());
					}else{
						htld.setText(StringUtils.substring(rmap.get("mtdt:page_content").toString(), 0, 300));
					}
					//
					
					HTimeLineDateAsset htlda = new HTimeLineDateAsset();
					htlda.setMedia("http://i2.sinaimg.cn/dy/995/2014/0718/U11182P1T995D133F25924DT20140718103834.jpg");
					htlda.setPosition("");
					htld.setAsset(htlda);
//					htl.setContent(StringUtils.substring(rmap.get("mtdt:page_content").toString(), 0, 100)+"...");
				}
				htldList.add(htld);
				ci = null;
			}
		}
		return htldList;
	}

}
