
<%@page import="com.telefonia.models.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if (session.getAttribute("userLoggin") == null || session.getAttribute("userLoggin").equals("")) {
		response.sendRedirect("login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Registro Cliente</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="nav.jsp" />
	<section id="contact" class="section-wrapper contact-section" data-stellar-background-ratio="0.5">
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
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<form action="newCliente" method="Post">
							<h4>Datos personales</h4>
							<div class="form-row">
								<div class="col-sm-4">
									<label>Codigo de Cliente</label> <input name="codigo"
										class="form-control" value="${codigo}" type="text"
										readonly="readonly" />
								</div>
								<div class="col-sm-4">
									<label>Nombre</label> <input type="text" class="form-control"
										name="nombre" required="required" />
								</div>
								<div class="col-sm-4">
									<label>Apellido</label> <input required="required" type="text"
										class="form-control" name="apellido" />
								</div>
							</div>
							<div class="form-row">
								<div class="col-sm-4">
									<label>Dui</label> <input required="required" type="text"
										class="form-control" name="dui" />
								</div>
								<div class="col-sm-4">
									<label>Correo</label> <input required="required" type="text"
										class="form-control" name="correo" />
								</div>
								<div class="col-sm-4">
									<label>Telefono</label> <input required="required" type="text"
										class="form-control" name="telefono" />
								</div>
							</div>
							<div class="form-row">
								<div class="col-sm-4">
									<label>Fecha de Nacimiento (dd-MM-yyyy)</label> <input
										required="required" type="date" class="form-control"
										name="fNacimiento" />
								</div>
								<div class="col-sm-4">
									<label>Sexo</label>
									<div class="custom-control custom-radio">
										<input class="custom-control-input" type="radio" id="m"
											name="sexo" value="m"> <label
											class="custom-control-label" for="m">Masculino</label>
									</div>
									<div class="custom-control custom-radio">
										<input class="custom-control-input" type="radio" id="f"
											name="sexo" value="f"> <label
											class="custom-control-label" for="f">Femenino</label>
									</div>

								</div>
							</div>
							<h4>Direccion</h4>
							<div class="form-row">
								<div class="col-sm-4">
									<label>Municipio</label> <select required="required"
										name="municipio" class="form-control" id="mun">
										<c:forEach items="${mun}" var="muni">
											<option value="${muni.idMuni}">${muni.nombreMuni}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-8">
									<label>Direccion</label> <input required="required"
										class="form-control" type="text" name="direccion" />
								</div>
								<div class="col-sm-12">
									<br>
									<br>
								</div>
								<div class="col-sm-12">
									<button class="btn btn-info">Registrar</button>
									<a class="btn btn-info" href="readClientes">Ver Clientes</a>
								</div>
							</div>
							<br>

						</form>
						${text}
					</div>
				</div>
			</div>

		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>