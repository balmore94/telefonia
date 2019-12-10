<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Insert title here</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="nav.jsp" />
	<section id="contact" class="section-wrapper contact-section"
		data-stellar-background-ratio="0.5">
		<div class="parallax-overlay"></div>

		<div class="row">

			<!-- Section Header -->
			<div
				class="col-md-12 col-sm-12 col-xs-12 section-header wow fadeInDown">
				<h2>
					<span class="highlight-text">Pagos</span>
				</h2>
			</div>
			<!-- Section Header End -->
			<div class="container">
				<div class="col-md-12">
					<h1>Registros de Pagos</h1>


					<div class="form-group">
						<form id="filtro" name="filtro" method="POST" action="carf">
							<label>Numero de Factura:</label> <input type="text" id="nf"
								name="nf" aria-describedby="Numero_factura"
								placeholder="N° Factura" class="rounded-pill">
							<button
								class="btn btn-color btn-lg active text-white fab fa-searchengin"
								type="submit">Ver</button>
						</form>
					</div>


					<div class="row">
						<div class="w-100 p-3 border border-success">${da}</div>
					</div>
				</div>
			</div>

		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>