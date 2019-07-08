<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="commons.jsp"></jsp:include>
<html>
<body>
<h2>JVM GC情况：</h2>

<table border="1">
	<tr align="center">
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
		<tr align="center">
			<td>${gcDetail.survivor0}%</td>
			<td>${gcDetail.survivor1}%</td>
			<td>${gcDetail.eden}%</td>
			<td>${gcDetail.old}%</td>
			<td>${gcDetail.permanent}%</td>
			<td>${gcDetail.youngGC}&nbsp;次</td>
			<td>${gcDetail.youngGCTime}&nbsp;s</td>
			<td>${gcDetail.fullGC}&nbsp;次</td>
			<td>${gcDetail.fullGCTime}&nbsp;s</td>
			<td>${gcDetail.gcTotalTime}&nbsp;s</td>
		</tr>
	</c:forEach>
</table>
<br><br>
垃圾回收状态评估：开发中...

</body>
</html>
