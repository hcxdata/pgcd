<%@ include file="/WEB-INF/jsp/inc/page.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/inc/head/head.jsp"%>
<title>事件列表</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/inc/nav.jsp"%>
	<div class="container">
		<cb:altMsg name="message" />
		<div class="page-header">
			<h2>事件列表</h2>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>事件描述</th>
					<th>事件特征词</th>
					<th>横向时间轴</th>
					<th>纵向时间轴</th>
					<th>报道角度分析</th>
					<th>相关新闻数量</th>
					<th>事件生成时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="event" items="${page.list}">
					<tr>
						<td>${event.desc }</td>
						<td>${event.featureWord }</td>
						<td><a
							href="<%=request.getContextPath() %>/hTimeline.do?eventID=${event.id}"
							target="_blank">分析</a></td>
						<td><a
							href="<%=request.getContextPath() %>/hTimeline.do?eventID=${event.id}"
							target="_blank">分析</a></td>
						<td><a
							href="<%=request.getContextPath() %>/hTimeline.do?eventID=${event.id}"
							target="_blank">分析</a></td>
						<td>${event.newsNum }</td>
						<td>${event.time }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<cb:page page="${page}" />
	</div>
</body>
</html>