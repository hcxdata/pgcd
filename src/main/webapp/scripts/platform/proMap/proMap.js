var g = M.rpc._call('proMapService.getRelaNodes', eventID);

var sigInst = sigma.init(document.getElementById('graph-container'))
		.drawingProperties({
			defaultLabelColor : '#000', // 标签文字颜色
			defaultLabelSize : 14, // 标签大小
			defaultLabelBGColor : '#fff', // 标签背景色
			defaultLabelHoverColor : '#000', // 鼠标移上标签颜色
			labelThreshold : 6,
			defaultEdgeType : 'curve',
			defaultEdgeArrow : 'target'
		}).graphProperties({
			minNodeSize : 10, // 最小node大小
			maxNodeSize : 10, // 最大node大小
			minEdgeSize : 3, // 最大edge大小
			maxEdgeSize : 3
		// 最小edge大小
		}).mouseProperties({
			maxRatio : 32
		});

// add event
// 鼠标悬停事件
sigInst.bind('downnodes', function(event) {
	var nodes = event.content;
	var node = nodes[0];
	if(node.indexOf('content')==-1) return ;
	showCard(event);
	M.rpc._call(updateCardContent,'proMapService.getContent', node.replace("content",""));
});

// 鼠标移出事件
sigInst.bind('outnodes', function(event) {
	var nodes = event.content;
	var node = nodes[0];
	// hideCard(event);
});

// Draw the graph :

sigInst.draw();
sigInst.pushGraph(g);
sigInst.draw();