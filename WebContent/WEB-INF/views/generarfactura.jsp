<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%@page import="com.telefonia.models.Usuarios"%>
<%
	if (session.getAttribute("userLoggin") == null || session.getAttribute("userLoggin").equals("")) {
		response.sendRedirect("inicio");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="nav.jsp" />
	<section id="contact" class="section-wrapper contact-section"
		data-stellar-background-ratio="0.5">
		<div class="parallax-overlay"></div>

		<div class="row justify-content-md-center">

			<!-- Section Header -->
			<div
				class="col-md-12 col-sm-12 col-xs-12 section-header wow fadeInDown">
				<h2>
					<span class="highlight-text">Registro de Cliente</span>
				</h2>
			</div>
			<!-- Section Header End -->
			<div class=col-md-12>${mensaje}</div>

			<div class="container">
				<div class="row">
					<div class="col-md-6 col align-self-center">
						<form action="generarF" method="post">
							<h3>Generar Factura</h3>
							<label>Fecha Facturaci�n</label> <input class="form-control"
								placeholder="Fecha Inicio" type="date" name="i" /><input
								class="form-control" placeholder="Fecha Fin" type="date"
								name="f" /> <br /> <input name="us" hidden=""
								value="${userLoggin.idUsu}">
							<button class="btn btn-success">Generar</button>
						</form>
					</div>
				</div>
			</div>

		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>