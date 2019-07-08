<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="commons.jsp"></jsp:include>
<html>
<body>
<h2>进程号:${vmid}</h2>
<h2>线程号:${tid}</h2>
执行堆栈：<br>
<pre>
${stack}
</pre>

</html>
