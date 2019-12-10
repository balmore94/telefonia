<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Registro Contrato</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="nav.jsp" />
	<br>
	<section id="contact" class="section-wrapper contact-section"
		data-stellar-background-ratio="0.5">
		<div class="parallax-overlay"></div>

		<div class="row justify-content-md-center">

			<!-- Section Header -->
			<div
				class="col-md-12 col-sm-12 col-xs-12 section-header wow fadeInDown">
				<h2>
					<span class="highlight-text">Registro de Contrato</span>
				</h2>
			</div>
			<!-- Section Header End -->
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<form action="newContrato" method="Post">
							<div class="form-row">
								<div class="col">
									<label>Fecha de Inicio</label> <input type="date"
										class="form-control" name="fec_inicio" required="required" />
								</div>
								<div class="col">
									<label>Fecha de Finalizacion</label> <input required="required"
										type="date" class="form-control" name="fec_fin" />
								</div>
							</div>
							<div class="form-row">
								<div class="col">
									<label>Tipo de Contrato</label> <select required="required"
										name="tipo_c" class="form-control">
										<option>Seleccione un tipo de contrato</option>
										<c:forEach items="${tipo_c}" var="tipo">
											<option value="${tipo.idTpcontrat}">${tipo.nombreTipoc}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-row">
								<div class="col">
									<label>Cliente</label> <select class="form-control"
										name="cliente">
										<option>Seleccione un Cliente</option>
										<c:forEach items="${cliente}" var="client">
											<option value="${client.idCliente}">${client.personas.nombre}
												${client.personas.apellido}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-row">
								<div class="col">
									<label>Quien Registra</label> <input class="form-control"
										name="usuarios" type="hidden" value="${userLoggin.idUsu}" />
									<input class="form-control" name="usuarios" readonly="readonly"
										type="text" value="${userLoggin.usuario}" />
								</div>
							</div>

							<div class="form-row">
								<div class="col" style="text-decoration: underline;">
									<h5 class="text-success">Plan: ${n}</h5>
									<input type="hidden" value="${pac}" name="pac" id="pac">
								</div>
							</div>
							<br /> ${respuesta} <br>

							<button class="btn btn-info">Registrar</button>
							<a class="btn btn-info" href="readContratos">Ver Contratos</a>
						</form>
					</div>
				</div>
			</div>

		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>