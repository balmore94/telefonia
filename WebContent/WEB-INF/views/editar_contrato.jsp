<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<meta charset="ISO-8859-1">
<title>Finalizacion Contrato</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h3>Detalles Contrato</h3>
				<form action="updateContrato" method="Post">
					<div class="form-row">
						<div class="col">
							<label>Fecha de Inicio</label> <input type="date"
								class="form-control" name="fec_inicio"
								value="${lista.fecInicio}" readonly="readonly" /> <input
								type="hidden" class="form-control" name="id"
								value="${lista.idContrat}" readonly="readonly" />
						</div>
						<div class="col">
							<label>Fecha de Finalizacion</label> <input type="date"
								class="form-control" name="fec_fin" value="${lista.fecFin}"
								readonly="readonly" />
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<label>Tipo de Contrato</label>
							<input type="text" value="${lista.tipoContrato.nombreTipoc}" class="form-control" readonly="readonly">
							<input type="hidden" name="tipo_c"
								value="${lista.tipoContrato.idTpcontrat}" class="form-control" readonly="readonly">
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<label>Cliente</label> <input type="text"  class="form-control"
								value="${lista.clientes.personas.nombre} ${lista.clientes.personas.apellido}"
								readonly="readonly"> <input type="hidden" name="cliente"
								value="${lista.clientes.personas.idPer}" readonly="readonly">
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<label>Estado</label>
							<c:choose>
								<c:when test="${lista.estado==true}"> <b style="color: green"> Activo</b></c:when>
								<c:otherwise>Inactivo</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-row">
						<div class="col">
							<label>Usuario</label> <input type="text"  class="form-control"
								value="${lista.usuarios.usuario}" readonly="readonly" /> <input
								type="hidden" name="usuarios" value="${lista.usuarios.idUsu}"
								readonly="readonly" />
						</div>
					</div>
					<br /> ${mensa} <br>
					<button class="btn btn-info">Terminar contrato</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>