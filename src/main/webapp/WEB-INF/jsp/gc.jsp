<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>JVM GC情况：</h2>

<table border="1">
	<tr>
		<td>Survivor0</td>
		<td>Survivor1</td>
		<td>Eden</td>
		<td>Old</td>
		<td>Permanent</td>
		<td>Young GC</td>
		<td>Young GC Time</td>
		<td>Full GC</td>
		<td>Full GC Time</td>
		<td>GC Total Time</td>
	</tr>
	<c:forEach var="gcDetail" items="${gcDetails}">
		<tr>
			<td>${gcDetail.survivor0}%</td>
			<td>${gcDetail.survivor1}%</td>
			<td>${gcDetail.eden}%</td>
			<td>${gcDetail.old}%</td>
			<td>${gcDetail.permanent}%</td>
			<td>${gcDetail.youngGC}</td>
			<td>${gcDetail.youngGCTime}</td>
			<td>${gcDetail.fullGC}</td>
			<td>${gcDetail.fullGCTime}</td>
			<td>${gcDetail.gcTotalTime}</td>
		</tr>
	</c:forEach>
</table>

垃圾回收状态评估：开发中...

</body>
</html>
