<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<!-- END chat Sidebar-->
	<div class="content-wrap">
		<div class="main">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-2 col-md-offset-5" style="text-align: center">
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
										style="max-width: calc(100% -   180px);">Server Top Info</div>
								</div>
								<div class="panel-body">
									vmName:${runtimeBean.vmName },<br>
									vmVersion:${runtimeBean.vmVersion},<br>
									uptime:${runtimeBean.uptime}<br>
								</div>
							</div>
						</div>
					</div>
					<!-- /# row -->
					<div class="row">
						<div class="col-lg-4 col-md-offset-2">
							<div class="card alert">
								<div class="card-header">
									<h4>Bootstrap(pid:65234)</h4>
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
												<td><span class="badge badge-success">12%</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<tr>
												<td>Memory usage</td>
												<td><span class="badge badge-danger">89%</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<tr>
												<td>Thread count</td>
												<td><span class="badge badge-danger">732</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<tr>
												<td>GC times</td>
												<td><span class="badge badge-danger">16</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="card alert">
								<div class="card-header">
									<h4>Bootstrap(pid:65234)</h4>
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
												<td><span class="badge badge-success">12%</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<tr>
												<td>Memory usage</td>
												<td><span class="badge badge-danger">89%</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<tr>
												<td>Thread count</td>
												<td><span class="badge badge-danger">732</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
											<tr>
												<td>GC times</td>
												<td><span class="badge badge-danger">16</span></td>
												<td><button type="button"
														class="btn btn-link m-b-10 m-l-5">detail</button></td>
											</tr>
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
