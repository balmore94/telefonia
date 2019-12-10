<%@page import="com.telefonia.models.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page session="true" %>
<%
	if (session.getAttribute("userLoggin") == null || session.getAttribute("userLoggin").equals("")) {
		response.sendRedirect("login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="../${pageContext.request.servletContext.contextPath}/assets/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<link
	href="../${pageContext.request.servletContext.contextPath}/assets/color/default.css"
	rel="stylesheet">
<link
	href="../${pageContext.request.servletContext.contextPath}/assets/css/fontawesome.css"
	rel="stylesheet" />
<link
	href="../${pageContext.request.servletContext.contextPath}/assets/css/brands.css"
	rel="stylesheet" />
<link
	href="../${pageContext.request.servletContext.contextPath}/assets/css/solid.css"
	rel="stylesheet" />

<link
	href="../${pageContext.request.servletContext.contextPath}/assets/css/regular.css"
	rel="stylesheet" />
<link
	href="../${pageContext.request.servletContext.contextPath}/assets/css/alertify.css"
	rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>Datos</h1>
	<div class="container">
		<div class="row">
			<table class="table table-bordered table-responsive-md"
				style="width: 40%; text-align: center">
				<thead class="color">
					<tr>
						<th scope="col">Pago</th>
						<th scope="col">Mora</th>
						<th scope="col">Dias de Retraso</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>$ ${da}</th>
						<th>$ ${car}</th>
						<th>${dia}</th>
					</tr>
					<tr class="color">
					<th colspan="2">Costo de Consumo Extra $</th>
					  <th>${e}</th>
						</tr>
						<tr>
						<th colspan="2">Total a Pagar $</th>
						<th>${total}</th>
					</tr>
				</tbody>
			</table>
		</div>
		<form action="pagar" method="POST">
		<input type="hidden" value="${f}" name="fa" id="fa"> 
			<button class="btn btn-warning">Finalizar Pago</button>
		</form>
	</div>



</body>
</html>