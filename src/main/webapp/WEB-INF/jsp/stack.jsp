<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<html>
<body>
<h2>Hello World!</h2>
<c:forEach var="stack" items="${stacks}">
${stack}
</c:forEach>

</html>
