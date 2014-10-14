package com.jetyun.pgcd.util;

import java.util.HashMap;
import java.util.Map;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public class GraphYifanHuLayoutUtil {
	private ProjectController pc = null;
	private Workspace workspace = null;
	private GraphModel graphModel = null;
	private DirectedGraph directedGraph = null;

	public DirectedGraph getDirectedGraph() {
		return directedGraph;
	}

	private Map<String, String> localx = new HashMap<String, String>();
	private Map<String, String> localy = new HashMap<String, String>();

	private Map<String, Node> nodeMap = new HashMap<String, Node>();

	public GraphYifanHuLayoutUtil() {
		// Init a project - and therefore a workspace
		pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		workspace = pc.getCurrentWorkspace();

		// Get a graph model - it exists because we have a workspace
		graphModel = Lookup.getDefault().lookup(GraphController.class)
				.getModel(workspace);
		directedGraph = graphModel.getDirectedGraph();
	}

	public void addNode(String id, String label) {
		Node node = getNode(graphModel, id, label);
		directedGraph.addNode(node);
		nodeMap.put(id, node);
	}

	public void addEdge(String id, String par) {
		Node node = nodeMap.get(id);
		Node parNode = nodeMap.get(par);

		if (node == null)
			throw new RuntimeException("not found node : " + id);
		if (parNode == null)
			throw new RuntimeException("not found node : " + par);

		Edge edge = getEdge(graphModel, node, parNode);
		directedGraph.addEdge(edge);
	}

	public void layout() {
		YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(1f));
		layout.setGraphModel(graphModel);
		layout.resetPropertiesValues();
		layout.initAlgo();
		for (int i = 0; i < 100 && layout.canAlgo(); i++) {
			layout.goAlgo();
		}
		layout.endAlgo();
		// 缓存坐标
		calLocation();
	}

	public void calLocation() {
		for (Node node : directedGraph.getNodes().toArray()) {
			String id = node.getNodeData().getId();
			float x = node.getNodeData().x();
			float y = node.getNodeData().y();

			localx.put(id, String.valueOf(x));
			localy.put(id, String.valueOf(y));
		}
	}

	public String getX(String nodeId) {
		return localx.get(nodeId);
	}

	public String getY(String nodeId) {
		return localy.get(nodeId);
	}

	public static Node getNode(GraphModel graphModel, String id, String label) {
		Node n0 = graphModel.factory().newNode(id);
		n0.getNodeData().setLabel(label);
		return n0;
	}

	public static Edge getEdge(GraphModel graphModel, Node id, Node par) {
		Edge n0 = graphModel.factory().newEdge(id, par);
		return n0;
	}

	public static void printNodes(DirectedGraph graphModel) {
		for (Node node : graphModel.getNodes().toArray()) {
			String label = node.getNodeData().getLabel();
			float x = node.getNodeData().x();
			float y = node.getNodeData().y();

			System.out
					.println("node " + label + " is : x -" + x + " , y -" + y);
		}
	}
}
