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
						<div class="col-lg-6 col-md-offset-3">
							<div
								class="panel lobipanel-basic panel-info lobipanel lobipanel-sortable"
								data-inner-id="As1tgt17n8" data-index="0"
								style="display: block;">
								<div class="panel-heading ui-sortable-handle">
									<div class="panel-title"
										style="max-width: calc(100% -   180px);">Operating System Info</div>
								</div>
								<div class="panel-body">
<pre style="padding: 12px;">
OS Name: ${osBean.osName}  Version: ${osBean.version} 
OS Arch: ${osBean.osArch}  Processors: ${osBean.availableProcessors}
GiB Mem : <fmt:formatNumber type="number" pattern="0.00">${osBean.totalPhysicalMemorySize / 1024 / 1024 / 1024}</fmt:formatNumber> total, <fmt:formatNumber type="number" pattern="0.00">${osBean.freePhysicalMemorySize / 1024 / 1024 / 1024}</fmt:formatNumber> free
GiB Swap: <fmt:formatNumber type="number" pattern="0.00">${osBean.totalSwapSpaceSize / 1024 / 1024 / 1024}</fmt:formatNumber> total, <fmt:formatNumber type="number" pattern="0.00">${osBean.freeSwapSpaceSize / 1024 / 1024 / 1024}</fmt:formatNumber> free
%CPU: <fmt:formatNumber type="number" pattern="0.00">${osBean.systemCpuLoad * 100}</fmt:formatNumber>%
</pre>
								</div>
							</div>
						</div>
					</div>
					<!-- /# row -->
					<div class="row">
						<c:forEach var="summary" items="${summaries}" varStatus="summStatus">
						
						<c:if test="${fn:length(summaries) == 1 && summStatus.count == 1}">
							<c:set var="md_offset" value="col-md-offset-4"></c:set>
						</c:if>
						
						<c:if test="${fn:length(summaries) == 2 && summStatus.count == 1}">
							<c:set var="md_offset" value="col-md-offset-2"></c:set>
						</c:if>
						
						<c:if test="${summStatus.count > 1}">
							<c:set var="md_offset" value=""></c:set>
						</c:if>
						
						<div class="col-lg-4 ${md_offset}">
							<div class="card alert">
								<div class="card-header">
									<h4 style="white-space: nowrap;overflow: hidden;-o-text-overflow: ellipsis;text-overflow: ellipsis;width: 230px;" title="${summary.pname}">PID:${summary.pid}(${summary.pname})</h4>
									<div class="card-header-right-icon">
										<ul>
											<li class="card-option drop-menu"><i class="ti-settings"
												data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="true" role="link"></i>
												<ul class="card-option-dropdown dropdown-menu">
													<li><a href="#"><i class="ti-loop"></i> JVM detail</a></li>
												</ul></li>

										</ul>
									</div>
								</div>
								<div class="card-body">
									<table class="table table-responsive table-bordered">
										<tbody>
											<tr>
												<td>CPU usage</td>
												<td><span class="badge badge-success"><fmt:formatNumber type="number" pattern="0.00">${summary.osBean.processCpuLoad * 100}</fmt:formatNumber>%</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<tr>
												<td>Memory usage</td>
												<td><span class="badge badge-danger"><fmt:formatNumber type="number" pattern="0.00">${summary.memoryUsage.used  / 1024 / 1024 / 1024 }</fmt:formatNumber>G / <fmt:formatNumber type="number" pattern="0.00">${summary.memoryUsage.max  / 1024 / 1024 / 1024 }</fmt:formatNumber>G</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<tr>
												<td>Thread count</td>
												<td><span class="badge badge-danger">${fn:length(summary.threadInfos)}</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<c:forEach var="gcBean" items="${summary.gcBeans}">
											<tr>
												<td>${gcBean.name}</td>
												<td><span class="badge badge-danger">${gcBean.collectionCount}</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
