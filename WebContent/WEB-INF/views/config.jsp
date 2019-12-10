
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<jsp:include page="header.jsp" /><meta charset="ISO-8859-1">
<title>Insert title here</title>
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
					<span class="highlight-text">Configuración</span>
				</h2>
			</div>
			<!-- Section Header End -->
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="container position-static">
							<div class="row mx-md-n5">
								<!--  
                <div class="position-static">
                -->
								<div class="w-15 p-3">
									<!-- Only required for left/right tabs -->
									<ul class="nav nav-tabs flex-column">
										<li class="nav-item"><a href="#tab1" data-toggle="tab"
											class="nav-link active">Llamadas</a></li>
										<li class="nav-item"><a href="#tab2" data-toggle="tab"
											class="nav-link">Mensajes</a></li>
										<li><a href="#tab3" data-toggle="tab" class="nav-link">Internet
												Movil</a></li>
									</ul>
								</div>
								<div class="w-70 p-3 border border-success">
									<div class="tab-content">
										<div class="tab-pane active" id="tab1">
											<h3 class="text-success">
												<strong>Configuraciones de Cobro Extra de Llamdas</strong>
											</h3>
											<form id="confll" name="confll" method="POST" action="mul">
												<div class="form-group">
													<label class="clearfix">Llamdas a Misma Red :</label> <input
														name="t0l" id="t0l" type="hidden" value="${c1.idConf}">
													<input name="t1l" id="t1l" type="text" class="form-control"
														value="${c1.tarifaMismaRed}">
												</div>
												<div class="form-group">
													<label class="clearfix">Llamdas a Red Fija :</label> <input
														name="t2l" id="t2l" type="text" value="${c1.tarifaFijo}"
														class="form-control">
												</div>
												<div class="form-group">
													<label class="clearfix">Llamdas a Otra Red :</label> <input
														name="t3l" id="t3l" type="text" value="${c1.tafifaNormal}"
														class="form-control">
												</div>
												<button class="btn btn-color text-white" onclick="mesj()"
													type="submit">
													<i class="far fa-save"></i> Save
												</button>
											</form>
										</div>
										<div class="tab-pane" id="tab2">
											<h3 class="text-success">
												<strong>Configuraciones de Cobro Extra de Mensajes</strong>
											</h3>
											<form id="confM" name="confM" method="POST" action="mum">
												<div class="form-group">
													<label class="">Mensajes a Misma Red :</label> <input
														name="t0m" id="t0m" type="hidden" value="${c2.idConf}">
													<input name="t1m" id="t1m" type="text" class="form-control"
														value="${c2.tarifaMismaRed}">
												</div>
												<div class="form-group">
													<label class="">Mensajes a Red Fija :</label> <input
														name="t2m" id="t2m" type="text" value="${c2.tarifaFijo}"
														class="form-control">
												</div>
												<div class="form-group">
													<label class="">Mensajes a Otra Red :</label> <input
														name="t3m" id="t3m" type="text" value="${c2.tafifaNormal}"
														class="form-control">
												</div>

											</form>
											<button class="btn btn-color text-white" onclick="mesj()"
												type="submit">
												<i class="far fa-save"></i> Save
											</button>
										</div>
										<div class="tab-pane" id="tab3">
											<h3 class="text-success">
												<strong>Configuraciones de Cobro Extra de Megabyte</strong>
											</h3>
											<form id="confD" name="confD" method="POST" action="mud">
												<div class="form-group">
													<label>Costo por Megabyte : </label> <input name="me"
														id="me" type="text" value="${c3.costo}"
														class="form-control"> <input name="id" id="id"
														type="hidden" value="${c3.idConf} ">
												</div>
												<button class="btn btn-color text-white" onclick="mesj()"
													type="submit" id="bd2" name="bd2">
													<i class="far fa-save"></i> Save
												</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</section>

	<script>
		function mesj() {
			var msj = '${msm}';
			var m = '${m}';
			if (m == 1) {
				alert(msj);
			} else {
				window.location.replace("cargarCo");
			}
			/*var m = '${m}';
			if (m == 1) {
				
				//	alertify.alert(msj).setHeader('<em> PR2_TELEFONIA </em> ');

				//window.location.href = "cargarCo"
				//
				alertify.confirm(msj, function() {
					alertify.success('Ok');
					window.location.href = "cargarCo";
				}, function() {
					alertify.error('Cancelado por usuario');
				});
			} else {
				alertify.error("Ocurrio un Problema Inesperado");
			}*/
		}
	</script>

	<!--

	<script
		src="../${pageContext.request.servletContext.contextPath}/assets/js/alertify.js"
		type="text/javascript">
		
	</script>
<script>
		function elimin() {
			alertify.alert().set('message', 'alert dialog!').show();
		}
	</script>-->
	<jsp:include page="footer.jsp" />
</body>
</html>