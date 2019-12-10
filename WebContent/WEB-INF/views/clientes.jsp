
<%@page import="com.telefonia.models.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%
	if (session.getAttribute("userLoggin") == null || session.getAttribute("userLoggin").equals("")) {
		response.sendRedirect("login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Lista de clientes</title>
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
					<span class="highlight-text">Clientes</span>
				</h2>
			</div>
			<!-- Section Header End -->
			<div class="container">
				<div class="row">
					<div class="col-hg-12">
						<div class="row">
							<div class="col-sm-4">
								<form action="searchCliente" method="post">
									<div class="form-row">
										<div class="col">
											<label>Buscar por # DUI</label> <input class="form-control"
												placeholder="Numero de Dui" type="text" name="ndui" />
											<button class="btn btn-info">Buscar</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-sm-4">
								<form action="searchClienteEstado" method="post">
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
								<form action="searchClienteNombre" method="post">
									<div class="col">
										<label>Buscar por Nombre</label> <input class="form-control"
											minlength="2" placeholder="Nombre a buscar" type="text"
											name="client" />
										<button class="btn btn-info">Buscar</button>
									</div>
								</form>
							</div>
							<div class="col-sm-4">
								<form action="searchClienteDuiEstado" method="post">
									<div class="col">
										<label>Buscar por Dui y estado</label><input
											required="required" class="form-control"
											placeholder="Ingrese numero de dui a buscar" type="text"
											name="ndui" /> <select name="estado" required="required"
											class="form-control">
											<option class="form-control" value="1" selected="selected">Activos</option>
											<option class="form-control" value="0">Inactivos</option>
										</select>
										<button class="btn btn-info">Buscar</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					${msg} <br>
					<div class="col-md-12">
						<table id="dtBasicExample"
							class="table table-striped table-bordered table-sm"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Codigo Cliente</th>
									<th>Estado</th>
									<th>Nombre</th>
									<th>Apellido</th>
									<th>N� de DUI</th>
									<th>Telefono</th>
									<th>Direccion</th>
									<th>Municipio</th>
									<th>Acciones</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${clientes}" var="c">
									<tr>
										<td>${c.codigoCl}</td>
										<td><c:choose>
												<c:when test="${c.estado==true}">Activo</c:when>
												<c:otherwise> Inactivo</c:otherwise>
											</c:choose></td>
										<td>${c.personas.nombre}</td>
										<td>${c.personas.apellido}</td>
										<td>${c.personas.dui}</td>
										<td>${c.personas.telefono}</td>
										<td>${c.personas.direcciones.direccion}</td>
										<td>${c.personas.direcciones.municipios.nombreMuni}</td>
										<td><a href="editCliente?t=${c.idCliente}" class="">Editar</a></td>
										<td><c:choose>
												<c:when test="${c.estado==true}">
													<a
														href="disableCliente?c=${c.idCliente}&p=${c.personas.idPer}&co=${c.codigoCl}&estado=false"
														class="btn btn-danger btn-xs">Innabilitar</a>
												</c:when>
											</c:choose> <c:choose>
												<c:when test="${c.estado==false}">
													<a
														href="disableCliente?c=${c.idCliente}&p=${c.personas.idPer}&co=${c.codigoCl}&estado=true"
														class="btn btn-danger btn-xs">Habilitar</a>
												</c:when>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Codigo Cliente</th>
									<th>Estado</th>
									<th>Nombre</th>
									<th>Apellido</th>
									<th>N� de DUI</th>
									<th>Telefono</th>
									<th>Direccion</th>
									<th>Municipio</th>
									<th>Acciones</th>
									<th></th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>

		</div>
	</section>

	<jsp:include page="footer.jsp" />

	<script>
		$(function() {
			var alert = $('div.alert[auto-close]');
			alert.each(function() {
				var that = $(this);
				var time_period = that.attr('auto-close');
				setTimeout(function() {
					that.alert('close');
				}, time_period);
			});
		});
	</script>