<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort() + contextPath; 
	request.setAttribute("basePath", basePath);
%>