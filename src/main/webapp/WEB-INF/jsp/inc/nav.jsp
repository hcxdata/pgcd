<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="/">HNTD-0.6</a>
	</div>

	<div class="collapse navbar-collapse">

		<ul class="nav navbar-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-weibo"></i> <span
					class="glyphicon glyphicon-user"></span> 采集管理 <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a
						href="<%=request.getContextPath()%>/keyWord.do?method=list"><i
							class="fa fa-tag fa-fw"></i> 关键字管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/userInfo.do?method=list"><i
							class="fa fa-user fa-fw"></i> 关注账号管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/stopKeyWord.do?method=list"><i
							class="fa fa-user fa-fw"></i> 停止词管理</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-weibo"></i> <span
					class="glyphicon glyphicon-time"></span> 实时采集展示 <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a
						href="<%=request.getContextPath()%>/keyWordRealTime.do"><i
							class="fa fa-tag fa-fw"></i> 关键字采集</a></li>
					<li><a
						href="<%=request.getContextPath()%>/accountRealTime.do"><i
							class="fa fa-user fa-fw"></i> 关注账号采集</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><span class="glyphicon glyphicon-road"></span>
					新闻线索发现<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="<%=request.getContextPath()%>/topicTrend.do"><i
							class="fa fa-tag fa-fw"></i> 趋势话题库</a></li>
					<li><a href="<%=request.getContextPath()%>/topicSeedTrend.do"><i
							class="fa fa-user fa-fw"></i> 种子话题库</a></li>
					<li><a
						href="<%=request.getContextPath()%>/topicTargetTrend.do"><i
							class="fa fa-user fa-fw"></i> 线索话题库</a></li>
				</ul></li>
			<li><a href="<%=request.getContextPath()%>/countAnalyze.do"><span
					class="glyphicon glyphicon-th-large"></span>统计概览</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> user <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/profile/edit?ok_url=%2Fweibo%2Fkeywords"><span
							class='glyphicon glyphicon-wrench'></span> 修改密码</a></li>
					<li class="divider"></li>
					<li><a data-method="delete" href="/sign_out" rel="nofollow"><span
							class="glyphicon glyphicon-log-out"></span> 退出</a></a></li>
				</ul></li>
		</ul>
	</div>
</nav>