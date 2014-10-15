package com.jetyun.pgcd.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.jetyun.pgcd.dao.domain.ContentInfo;
import com.jetyun.pgcd.util.hbase.HRMTable;
import com.jetyun.pgcd.util.hbase.HRMTable.TYPES;
import com.jetyun.pgcd.web.domain.HTimeline;

@Component
public class ContentInfoDao extends BasicDao {
	
	public List<HTimeline> getHTimeLineList(String eventID) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("eventID", eventID);
		List<HTimeline> htlList = new ArrayList<HTimeline>();
	 	HRMTable ht = new HRMTable("webpage_new");
		List<ContentInfo>  list= this.queryForList("contentinfo.getContentInfoListByEventID", map);
		if(!CollectionUtils.isEmpty(list)){
			Iterator<ContentInfo> it = list.iterator();
			ContentInfo ci = null;
			while(it.hasNext()){
				ci = it.next();
				HTimeline htl = new HTimeline();
				htl.setType("blog_post");
				htl.setDate(ci.getTim());
				htl.setDateFormat("HH:mm");
				htl.setTitle(ci.getTitle());
//				htl.setImage("http://www.people.com.cn/mediafile/pic/GQ/20141013/94/8948334366490436494.jpg");
				Map<String,TYPES> argMap = new HashMap<String,TYPES>();
				Map<String, String> rmap = ht.getRowValues(ci.getRowkey(),argMap);
				if(rmap.size()>0){
					if(rmap.containsKey("mtdt:description") && rmap.get("mtdt:description").toString().length()>0 ){
						htl.setContent(rmap.get("mtdt:description").toString());
					}else{
						htl.setContent(StringUtils.substring(rmap.get("mtdt:page_content").toString(), 0, 300));
					}
//					htl.setContent(StringUtils.substring(rmap.get("mtdt:page_content").toString(), 0, 100)+"...");
				}
				htlList.add(htl);
				ci = null;
			}
		}
		return htlList;
	}
}
