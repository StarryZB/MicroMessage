﻿<%@ page import="java.util.List" %>
<%@ page import="com.bean.Message" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>内容列表页面</title>
		<link href="<%= basepath %>resources/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%= basepath %>resources/js/common/jquery-1.8.0.min.js"></script>
		<script src="<%= basepath %>resources/js/back/list.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form action="List.action" id="mainForm" method="post">
			<div class="right">
				<div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
				<div class="rightCont">
					<p class="g_title fix">内容列表 <a class="btn03" href="#">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="javascript:deleteBatch('<%=basepath%>');">删 除</a></p>
					<table class="tab1">
						<tbody>
							<tr>
								<td width="90" align="right">指令名称：</td>
								<td>
									<input name="command" type="text" class="allInput" value=""/>
								</td>
								<td width="90" align="right">描述：</td>
								<td>
									<input name="description" type="text" class="allInput" value=""/>
								</td>
	                            <td width="85" align="right"><input type="submit" class="tabSub" value="查 询" /></td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
								    <th><input type="checkbox" id="all" onclick="#"/></th>
								    <th>序号</th>
								    <th>指令名称</th>
								    <th>描述</th>
								    <th>操作</th>
								</tr>

								<%
									List<Message> messageList = (List<Message>) request.getAttribute("messageList");
									System.out.println(messageList);
									Iterator<Message> iterator = messageList.iterator();
									int status = 1;
									while (iterator.hasNext()) {
										Message message = iterator.next();
								%>
								<%
									if (status % 2 != 0) {
								%>
								<tr style="background-color:#ECF6EE;">
								<%
									} else {
								%>
								<tr>
								<%
									}
								%>
									<td><input type="checkbox" name="id" value="<%=message.getId()%>"/></td>
									<td><%= status%></td>
									<td><%= message.getCommand()%></td>
									<td><%= message.getDescription()%></td>
									<td>
										<a href="#">修改</a>&nbsp;&nbsp;&nbsp;
										<a href="<%=basepath%>DeleteOne.action?id=<%=message.getId()%>">删除</a>
									</td>
								</tr>
								<%
										status ++;
									}
								%>
							</tbody>
						</table>
						<div class='page fix'>
							共 <b><%=messageList.size()%></b> 条
							<a href='###' class='first'>首页</a>
							<a href='###' class='pre'>上一页</a>
							当前第<span>1/1</span>页
							<a href='###' class='next'>下一页</a>
							<a href='###' class='last'>末页</a>
							跳至&nbsp;<input type='text' value='1' class='allInput w28' />&nbsp;页&nbsp;
							<a href='###' class='go'>GO</a>
						</div>
					</div>
				</div>
			</div>
	    </form>
	</body>
</html>