<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<html>
<body>
<h2>threadIds:</h2>
<c:forEach var="threadId" items="${threadIds}">
${threadId}<br>
</c:forEach>

</body>
</html>
