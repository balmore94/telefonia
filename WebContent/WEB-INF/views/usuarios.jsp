<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@page import="com.telefonia.models.Usuarios"%>
<%
	if (session.getAttribute("userLoggin") == null || session.getAttribute("userLoggin").equals("")) {
		response.sendRedirect("login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
<meta charset="ISO-8859-1">
<title>Registro Usuario</title>
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
					<span class="highlight-text">Registro de Usuario</span>
				</h2>
			</div>
			<!-- Section Header End -->
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<form action="newUsuario" method="Post">
							<h4>Datos personales</h4>
							<div class="form-row">
								<div class="col-sm-4">
									<label>Nombre</label> <input type="text" class="form-control"
										name="nombre" required="required" />
								</div>
								<div class="col-sm-4">
									<label>Apellido</label> <input required="required" type="text"
										class="form-control" name="apellido" />
								</div>
								<div class="col-sm-4">
									<label>Dui</label> <input required="required" type="text"
										class="form-control" name="dui" />
								</div>
							</div>
							<div class="form-row">
								<div class="col-sm-4">
									<label>Correo</label> <input required="required" type="text"
										class="form-control" name="correo" />
								</div>
								<div class="col-sm-4">
									<label>Telefono</label> <input required="required" type="text"
										class="form-control" name="telefono" />
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
							<div class="form-row">
								<div class="col-sm-4">
									<label>Fecha de Nacimiento (dd-MM-yyyy)</label> <input
										required="required" type="date" class="form-control"
										name="fNacimiento" />
								</div>

							</div>
							<div class="col-sm-6">
								<label>Tipo Usuario</label> <select required="required"
									name="tipo_u" class="form-control" id="id_tpu">
									<c:forEach items="${listTP}" var="tp">
										<option value="${tp.idTpu}">${tp.descripcion}</option>
									</c:forEach>
								</select>
							</div>
							<br>

							<div class="form-row">
								<div class="col-sm-12">
									<h4 align="left">Direccion</h4>
									<label>Municipio</label> <select required="required"
										name="municipio" class="form-control" id="mun">
										<c:forEach items="${mun}" var="muni">
											<option value="${muni.idMuni}">${muni.nombreMuni}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-4">
									<label>Direccion</label> <input required="required"
										class="form-control" type="text" name="direccion" />
								</div>
							</div>
							<br />
							<div class="form-row">
								<div class="col-sm-4">
									<label>Usuario</label> <input type="text" class="form-control"
										name="usuario" required="required" />
								</div>
								<div class="col-sm-4">
									<label>Password</label> <input required="required"
										minlenght="8" type="text" class="form-control" name="pass" />
								</div>
								<div class="col-sm-12">
								<br>
									<button class="btn btn-info">Registrar</button>
								</div>
							</div>
							<br />
							<br>

						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>