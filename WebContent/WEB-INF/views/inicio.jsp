<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.telefonia.models.Usuarios"%>
<!DOCTYPE html>
<html>
<head>
<title>Inicio</title>
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />


<jsp:include page="header.jsp" />
<body onload="lanzadera();">
<body>
<!--     Preloader -->
<!--     <div class="preloader"> -->
<!--         <div class="status"></div> -->
<!--     </div> -->
 
	 
<jsp:include page="nav.jsp" />
	<jsp:include page="jumbotron.jsp" />

	<%-- <jsp:include page="container.jsp" /> --%>
	<section id="pricing" class="pricing-section">
		<div class="container">
			<div class="row">

				<!-- Section Header -->
				<div
					class="col-md-12 col-sm-12 col-xs-12 section-header wow fadeInDown">
					<h2>
						<span class="highlight-text">Planes postpago | Movil</span>
					</h2>
				</div>
				<!-- Section Header End -->

				<div class="pricing-wrapper">

					<!-- Plans -->
					<div
						class="col-md-3 col-sm-3 col-xs-12 pricing-plans wow bounceInLeft hoverer">
						<div class="pricing-titles">
							<h2>Estandar</h2>
							<p>
								<span>$9.99/</span>al Mes
							</p>
						</div>
						<div class="pricing-service-name">
							<ul>
								<li class="list-group-item">2 GB de navegaci&oacuten</li>
								<li class="list-group-item">25 SMS</li>
								<li class="list-group-item">60 Min</li>
								<li class="list-group-item">30 Min USA-CA</li>
							</ul>
						</div>

						<div class="bg-btn">
							<a href="newContrato?pac=1&n=Estandar" class="signup-btn">Adquirir</a>
						</div>
					</div>
					<!-- Plans End -->

					<!-- Plans -->
					<div
						class="col-md-3 col-sm-3 col-xs-12 pricing-plans wow fadeInUp hoverer"
						data-wow-duration="1s">
						<div class="pricing-titles">
							<h2>Premium</h2>
							<p>
								<span>$19.99/</span>al Mes
							</p>
						</div>
						<div class="pricing-service-name">
							<ul>
								<li class="list-group-item">5 GB de navegaci&oacuten</li>
								<li class="list-group-item">50 SMS</li>
								<li class="list-group-item">120 Min</li>
								<li class="list-group-item">60 Min USA-CA</li>
							</ul>
						</div>

						<div class="bg-btn">
							<a href="newContrato?pac=2&n=Premium" class="signup-btn">Adquirir</a>
						</div>
					</div>
					<div
						class="col-md-3 col-sm-3 col-xs-12 pricing-plans wow fadeInUp hoverer"
						data-wow-duration="1s">
						<div class="pricing-titles">
							<h2>Profesional</h2>
							<p>
								<span>$29.99/</span>al Mes
							</p>
						</div>
						<div class="pricing-service-name">
							<ul>
								<li class="list-group-item">10 GB de navegaci&oacuten</li>
							<li class="list-group-item">100 SMS</li>
							<li class="list-group-item">180 Min</li>
							<li class="list-group-item">120 Min USA-CA</li>
							</ul>
						</div>

						<div class="bg-btn">
							<a href="newContrato?pac=3&n=Profesional" class="signup-btn">Adquirir</a>
						</div>
					</div>
					<!-- Plans End -->

					<!-- Plans -->
					<div
						class="col-md-3 col-sm-3 col-xs-12 pricing-plans wow bounceInRight hoverer">
						<div class="pricing-titles">
							<h2>Exclusivo</h2>
							<p>
								<span>$39.99/</span>al Mes
							</p>
						</div>
						<div class="pricing-service-name">
							<ul>
							<li class="list-group-item">30 GB de navegaci&oacuten</li>
							<li class="list-group-item">150 SMS</li>
							<li class="list-group-item">250 Min</li>
							<li class="list-group-item">150 Min USA-CA</li>
							</ul>
						</div>
						<div class="bg-btn">
							<a href="newContrato?pac=4&n=Exclusivo" class="signup-btn">Adquirir</a>
						</div>
					</div>
					<!-- Plans End -->
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
	
</body>
</html>