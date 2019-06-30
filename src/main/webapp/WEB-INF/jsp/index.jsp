<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>活动Java进程</h2>

<table border="1">
	<tr>
		<td>启动用户</td>
		<td>进程ID</td>
		<td>启动时间</td>
		<td>启动命令</td>
		<td>启动参数</td>
		<td>查看线程</td>
	</tr>
	<c:forEach var="process" items="${javaProcess}">
		<tr>
			<td>${process.startUser}</td>
			<td>${process.processId}</td>
			<td>${process.startTime}</td>
			<td>${process.startCommand}</td>
			<td width="200px">${process.params}</td>
			<td>
				<a target="_blank" href="/cpu/busyStack?vmid=${process.processId}">CPU占用分析</a>
				<a target="_blank" href="/memory/useStack?vmid=${process.processId}">内存占用分析</a><br>
				<a target="_blank" href="/gc/jstat?vmid=${process.processId}&interval=2000&count=5">GC异常分析</a>
				<a href="javascript:alert('死锁线程、僵化线程   检测开发中')">死锁线程分析</a>
			</td>
		</tr>
	</c:forEach>
	
</table>



</body>
</html>
