<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="commons.jsp"></jsp:include>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JInspect</title>
<!-- Styles -->
<link href="${basePath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${basePath}/static/css/themify-icons.css" rel="stylesheet">
<link href="${basePath}/static/css/style.css" rel="stylesheet">
<script src="${basePath}/static/js/jquery.min.js"></script>
<script src="${basePath}/static/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="content-wrap">
		<div class="main">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-4 col-md-offset-4" style="text-align: center">
						<h1>JInspect v1.0</h1>
					</div>
				</div>
				<!-- /# row -->
				<div class="main-content">

					<!-- /# row -->
					<div class="row">
						<div class="col-lg-10 col-md-offset-1">
							<div class="card alert">
								<div class="card-header">
									<h4 style="white-space: nowrap;overflow: hidden;-o-text-overflow: ellipsis;text-overflow: ellipsis;width: 230px;">xxx</h4>
								</div>
								<div class="card-body">
									<table class="table table-responsive table-bordered">
										<tbody>
											<tr>
												<td>ThreadId</td>
												<td>ThreadName</td>
												<td>State</td>
												<td>AllocatedBytes</td>
												<td>CpuTime</td>
												<td>UserTime</td>
												<td><button type="button" class="btn btn-link m-b-10 m-l-5">trace</button></td>
											</tr>
											<c:forEach var="threadInfo" items="${threadInfos}">
											<tr>
												<td>${threadInfo.threadId }</td>
												<td>${threadInfo.threadName }</td>
												<td>${threadInfo.threadState }</td>
												<td>${threadInfo.threadAllocatedBytes }</td>
												<td>${threadInfo.threadCpuTime }</td>
												<td>${threadInfo.threadUserTime }</td>
												<td><button type="button" class="btn btn-link m-b-10 m-l-5">trace</button></td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
