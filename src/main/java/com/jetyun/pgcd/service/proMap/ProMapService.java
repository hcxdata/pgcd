package com.jetyun.pgcd.service.proMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jetyun.pgcd.dao.ProMapDao;
import com.jetyun.pgcd.dao.domain.ContentInfo;
import com.jetyun.pgcd.util.GraphYifanHuLayoutUtil;

@Service
public class ProMapService {
	private String[] colors = { "#FF0000", "#0066CC", "#FFFF37", "#00EC00",
			"FF8F59", "#E800E8", "#5A5AAD", "#F00078", "#4F9D9D", "#FE0600",
			"#CC0073" };

	@Autowired
	ProMapDao proMapDao;

	// 获取关系路径中的所有节点
	public Map<String, Object> getRelaNodes(String eventID) {
		List<Node> nodes = new ArrayList<Node>();
		List<Edge> edges = new ArrayList<Edge>();

		// 处理event信息
		Node event = proMapDao.getEventNode(eventID);
		event.setId("event" + event.getId());
		event.setColor(colors[0]);
		nodes.add(event);
		{
			Edge dege = new Edge();
			dege.setId(event.getId());
			dege.setSize("1");
			dege.setSource(event.getId());
			dege.setTarget(null);
			edges.add(dege);
		}

		// 处理topic信息
		List<Node> topics = proMapDao.getTopicNodesByEventId(eventID);
		for (Node topic : topics) {
			topic.setId("topic" + topic.getId());
			topic.setColor(colors[1]);
			nodes.add(topic);
			Edge dege = new Edge();
			dege.setId(topic.getId());
			dege.setSize("1");
			dege.setSource(topic.getId());
			dege.setTarget(event.getId());
			edges.add(dege);
		}

		// 处理content信息
		for (Node topic : topics) {
			List<Node> contents = proMapDao.getContentNodesByEventId(topic
					.getId().replaceAll("topic", ""));
			for (Node content : contents) {
				content.setId("content" + content.getId());
				content.setColor(colors[2]);
				nodes.add(content);
				Edge dege = new Edge();
				dege.setId(content.getId());
				dege.setSize("1");
				dege.setSource(content.getId());
				dege.setTarget(topic.getId());
				edges.add(dege);
			}
		}

		// 建立节点的坐标
		layout(nodes, edges);

		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("nodes", nodes);
		mapData.put("edges", edges);


		return mapData;
	}

	/**
	 * 布局节点的位置
	 * 
	 * @param nodes
	 *            - 所有节点
	 */
	public void layout(List<Node> nodes, List<Edge> edges) {
		GraphYifanHuLayoutUtil util = new GraphYifanHuLayoutUtil();
		for (Node node : nodes) {
			util.addNode(node.getId(), node.getLabel());
		}
		for (Edge edge : edges) {
			if (edge.getTarget() != null)
				util.addEdge(edge.getSource(), edge.getTarget());
		}
		util.layout();

		for (Node node : nodes) {
			String x = util.getX(node.getId());
			String y = util.getY(node.getId());
			node.setX(x);
			node.setY(y);
		}
	}

	public ContentInfo getContent(String ID) {
		return proMapDao.getContentByID(ID);
	}
}
