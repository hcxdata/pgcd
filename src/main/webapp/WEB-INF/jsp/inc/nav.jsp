<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="/">PGCD-0.1</a>
	</div>

	<div class="collapse navbar-collapse">

		<ul class="nav navbar-nav">
			<li><a href="<%=request.getContextPath()%>/eventList.do?method=list" > <i class="fa"></i> <span
					class="glyphicon glyphicon-user"></span> 事件列表 <b class="caret"></b>
			</a>
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