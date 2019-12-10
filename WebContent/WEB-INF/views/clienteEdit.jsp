<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Registro Cliente</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h3>Actualizacion de datos de cliente</h3>
				<form action="updateClient" method="POST">
					<h4>Datos personales</h4>
					<div class="form-row">
						<div class="col">
							<label>Codigo de Cliente</label> <input name="codigo"
								class="form-control" value="${cliente.codigoCl}" type="text"
								readonly="readonly" /> <input name="idCliente"
								class="form-control" value="${cliente.idCliente}" type="hidden"
								readonly="readonly" /> <input name="idPer" class="form-control"
								value="${cliente.personas.idPer}" type="hidden"
								readonly="readonly" /> <input name="idDir" class="form-control"
								value="${cliente.personas.direcciones.idDir}" type="hidden"
								readonly="readonly" />
						</div>
						<div class="col">
							<label>Nombre</label> <input type="text" class="form-control"
								name="nombre" required="required"
								value="${cliente.personas.nombre}" />
						</div>
						<div class="col">
							<label>Apellido</label> <input required="required" type="text"
								class="form-control" value="${cliente.personas.apellido}"
								name="apellido" />
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<label>Dui</label> <input required="required" type="text"
								class="form-control" name="dui" value="${cliente.personas.dui}" />
						</div>
						<div class="col">
							<label>Correo</label> <input required="required" type="text"
								class="form-control" name="correo"
								value="${cliente.personas.correo}" />
						</div>
						<div class="col">
							<label>Telefono</label> <input required="required" type="text"
								class="form-control" name="telefono"
								value="${cliente.personas.telefono}" />
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<label>Fecha de Nacimiento (dd-MM-yyyy)</label> <input
								required="required" type="date" class="form-control"
								name="fNacimiento" value="${cliente.personas.FNacimiento}" />
						</div>
						<div class="col">
							<label>Sexo</label>
							<div class="custom-control custom-radio">
								<c:choose>
									<c:when test="${cliente.personas.sexo=='m'}">
										<input checked="checked" class="custom-control-input"
											type="radio" id="m" name="sexo"
											value="${cliente.personas.sexo}">
										<label class="custom-control-label" for="m">Masculino</label>
									</c:when>
									<c:otherwise>
										<input class="custom-control-input" type="radio" id="m"
											name="sexo" value="m">
										<label class="custom-control-label" for="m">Masculino</label>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="custom-control custom-radio">
								<c:choose>
									<c:when test="${cliente.personas.sexo=='f'}">
										<input checked="checked" class="custom-control-input"
											type="radio" id="f" name="sexo"
											value="${cliente.personas.sexo}">
										<label class="custom-control-label" for="f">Masculino</label>
									</c:when>
									<c:otherwise>
										<input class="custom-control-input" type="radio" id="f"
											name="sexo" value="f">
										<label class="custom-control-label" for="f">Femenino</label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<h4>Direccion</h4>
					<div class="form-row">
						<div class="col">
							<label>Municipio</label> <select required="required"
								name="municipio" class="form-control" id="mun">
								<c:forEach items="${mun}" var="muni">
									<c:choose>
										<c:when
											test="${muni.idMuni==cliente.personas.direcciones.municipios.idMuni}">
											<option value="${muni.idMuni}" selected="selected">${muni.nombreMuni}</option>
										</c:when>
										<c:otherwise>
											<option value="${muni.idMuni}">${muni.nombreMuni}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="col">
							<label>Direccion</label> <input required="required"
								class="form-control"
								value="${cliente.personas.direcciones.direccion}" type="text"
								name="direccion" />
						</div>
					</div>
					<br />
					<button class="btn btn-info">Actualizar</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>