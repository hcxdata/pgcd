<%@ include file="/WEB-INF/jsp/inc/page.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/inc/head/head.jsp"%>
<link type="text/css" rel="stylesheet" media="screen"
	href="<%=request.getContextPath()%>/styles/platform/proMap/common.css" />
<link type="text/css" rel="stylesheet" media="screen"
	href="<%=request.getContextPath()%>/styles/platform/proMap/css.css" />

<style>
html {
	height: 100%
}

body {
	height: 100%;
}

#graph-container {
	height: 80%;
	width: 90%;
}

body {
	font-family: helvetica, arial;
}
</style>
<title>报道角度分析</title>
<script type="text/javascript">
	var eventID = '${eventID}';
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/inc/nav.jsp"%>
	<div id="graph-container" class="container"></div>
	<div id="uCard"
		style="position: absolute; clear: both; z-index: 780; text-align: left; left: 0px; top: 0px; display: none;">
		<div>
			<table class="mBlogLayer">
				<tbody>
					<tr>
						<td class="top_l"></td>
						<td class="top_c"></td>
						<td class="top_r"></td>
					</tr>
					<tr>
						<td class="mid_l"></td>
						<td class="mid_c">
							<div class="layerBox">
								<div class="layerBoxCon1">
									<div class="name_card">
										<div class="layerArrow"></div>
										<div>
											<dl class="name clearFix">
												<!-- 名称 -->
												<dd class="name_card_con0 " style="width: 206px;">
													<a id="weibourl2" href="" title="" target="_blank"></a>
												</dd>
											</dl>


										</div>
									</div>
								</div>
							</div>
						</td>
						<td class="mid_r"></td>
					</tr>
					<td class="bottom_l"></td>
					<td class="bottom_c"></td>
					<td class="bottom_r"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/platform/proMap/proMap.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/platform/proMap/card.js"></script>
</body>
</html>