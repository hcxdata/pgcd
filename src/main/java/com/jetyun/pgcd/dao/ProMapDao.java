package com.jetyun.pgcd.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jetyun.pgcd.dao.domain.ContentInfo;
import com.jetyun.pgcd.dao.domain.Event;
import com.jetyun.pgcd.dao.domain.Topic;
import com.jetyun.pgcd.service.proMap.Node;

@Component
public class ProMapDao extends BasicDao<Event> {
	/**
	 *  通过eventID获取event对象信息并转换为Node对象
	 * 
	 * @param eventID
	 * @return
	 */
	public Node getEventNode(String eventID) {
		Map<String, String> prm = new HashMap<String, String>();
		prm.put("eventID", eventID);
		Event event = (Event) this.queryForObject("events.getEventById", prm);
		Node node = new Node();
		node.setId(String.valueOf(event.getId()));
		node.setLabel(event.getDescription());
		return node;
	}

	/**
	 * 通过eventID获取topic对象信息并转换为Node对象
	 * @param eventID
	 * @return
	 */
	public List<Node> getTopicNodesByEventId(String eventID) {
		Map<String, String> prm = new HashMap<String, String>();
		prm.put("eventID", eventID);
		List<Topic> topics = this
				.queryForList("topics.getTopicsByEventID", prm);
		List<Node> list = new ArrayList<Node>();
		for (Topic topic : topics) {
			Node node = new Node();
			node.setId(String.valueOf(topic.getId()));
			node.setLabel(topic.getDescription());
			list.add(node);
		}
		return list;
	}
	
	/**
	 * 通过topicID获取content对象信息并转换为Node对象
	 * @param eventID
	 * @return
	 */
	public List<Node> getContentNodesByEventId(String topicID) {
		Map<String, String> prm = new HashMap<String, String>();
		prm.put("topicID", topicID);
		List<ContentInfo> contents = this
				.queryForList("contentinfo.getContentsByTopicID", prm);
		List<Node> list = new ArrayList<Node>();
		for (ContentInfo content : contents) {
			Node node = new Node();
			node.setId(String.valueOf(content.getId()));
			node.setLabel(content.getTitle());
			list.add(node);
		}
		return list;
	}
	
	/**
	 * 通过contentID获取content内容
	 * @param ID
	 * @return
	 */
	public ContentInfo getContentByID(String ID){
		return (ContentInfo)this.queryForObject("contentinfo.getContentById", ID);
	}

}
