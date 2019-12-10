<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.telefonia.models.Usuarios"%>
<%@page session="true"%>
<%
	if (session.getAttribute("userLoggin") == null || session.getAttribute("userLoggin").equals("")) {
		response.sendRedirect("login");
	}
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Contratos</title>
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
					<span class="highlight-text">Contratos</span>
				</h2>
			</div>
			<!-- Section Header End -->
			<div class="container">
				<div class="row">
					<div class="col-hg-12">
						<div class="row">
							<div class="col-sm-4">
								<form action="searchContratoEstado" method="post">
									<div class="col">
										<label>Buscar por estado</label><select name="estado"
											class="form-control">
											<option class="form-control" value="1">Activos</option>
											<option class="form-control" value="0">Inactivos</option>
										</select>
										<button class="btn btn-info">Buscar</button>
									</div>
								</form>
							</div>
							<div class="col-sm-4">
								<form action="searchContratoXCliente" method="post">
									<div class="col">
										<label>Buscar por Nombre</label> <input class="form-control"
											placeholder="Nombre a buscar" type="text" name="client" />
										<button class="btn btn-info">Buscar</button>
									</div>
								</form>
							</div>
							<div class="col-sm-4">
								<form action="searchContratoXfecha" method="post">
									<div class="col">
										<label>Buscar por Fecha </label> <input class="form-control"
											placeholder="Fecha Inicio" type="date" name="fecha" required />
										<input class="form-control" placeholder="Fecha Fin"
											type="date" name="fecha_fin" required />
										<button class="btn btn-info">Buscar</button>

									</div>
								</form>
								<a href="www.google.com">Google</a> <br>
							</div>

						</div>
					</div>
					<div class="col-md-12">
						<h3>Contratos</h3>
						<br> ${msg} <br>
						<table class="table">
							<thead>
								<tr>
									<th>Fecha de Inicio</th>
									<th>Fecha de Finalizacion</th>
									<th>Tipo de Contrato</th>
									<th>Cliente</th>
									<th>Estado</th>
									<th>Usuario</th>
									<th>Acciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${contratos}" var="contra">
									<tr>
										<td>${contra.fecInicio}</td>
										<td>${contra.fecFin}</td>
										<td>${contra.tipoContrato.nombreTipoc}</td>
										<td>${contra.clientes.personas.nombre}
											${contra.clientes.personas.apellido}</td>
										<td><c:choose>
												<c:when test="${contra.estado==true}">Activo</c:when>
												<c:otherwise> Inactivo</c:otherwise>
											</c:choose></td>
										<td>${contra.usuarios.usuario}</td>

										<td><a class="btn btn-info"
											href="consultarContrato?cod=${contra.idContrat}">Editar</a>
									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>
				</div>

			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>