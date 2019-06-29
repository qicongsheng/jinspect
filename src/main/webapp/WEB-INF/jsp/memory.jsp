<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>线程总览（按内存占用率排序）:</h2>

<table border="1">
	<tr>
		<td>进程ID</td>
		<td>线程ID</td>
		<td>内存占用率</td>
		<td>CPU占用率</td>
		<td>CPU运行时间</td>
		<td>线程状态</td>
		<td>查看线程</td>
	</tr>
	<c:forEach var="thread" items="${threads}">
		<tr>
			<td>${thread.pid}</td>
			<td>${thread.tid}</td>
			<td>${thread.memoryPercent}%</td>
			<td>${thread.cpuPercent}%</td>
			<td>${thread.cpuTime}</td>
			<td>${thread.state}</td>
			<td><a target="_blank" href="/thread/stack?vmid=${thread.pid}&tid=${thread.tid}">线程堆栈</a></td>
		</tr>
	</c:forEach>
	
</table>



</body>
</html>
