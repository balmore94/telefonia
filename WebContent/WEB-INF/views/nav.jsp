<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<!-- Preloader -->
	<!--     <div class="preloader"> -->
	<!--         <div class="status"></div> -->
	<!--     </div> -->


	<header>
		<!-- Navigation Menu start-->

		<nav class="navbar navbar-default main-menu">
			<div class="container">

				<a class="navbar-brand page-scroll" href="#slider"><img
					class="logo" id="logo"
					src="${pageContext.request.servletContext.contextPath}/assets/images/logo.png"
					alt="logo"></a>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="inicio">INICIO</a>
					</li>

					<c:choose>
						<c:when test="${userLoggin.tipoUsu.idTpu==1}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Usuarios</a>
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="registroUsuario">Nuevo</a> <a
										class="dropdown-item" href="">Usuarios</a>
								</div>
							</li>
			
						</c:when>
					</c:choose>
					<c:choose>
						<c:when
							test="${userLoggin.tipoUsu.idTpu==1 or userLoggin.tipoUsu.idTpu==2}">
							<li class="nav-item dropdown"> 
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Clientes</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="newCliente">Nuevo</a> 
									<a class="dropdown-item" href="readClientes">Clientes</a>
								</div>
							</li>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when
							test="${userLoggin.tipoUsu.idTpu==2  or userLoggin.tipoUsu.idTpu==1}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Contrato</a>
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="readContratos">Contratos</a>
								</div></li>
						</c:when>
					</c:choose>

					<c:choose>
						<c:when
							test="${userLoggin.tipoUsu.idTpu==2  or userLoggin.tipoUsu.idTpu==1}">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Facturas</a>
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="pagos">Pagos</a> <a
										class="dropdown-item" href="generarfactura">Generar
										factura</a>
								</div></li>
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${userLoggin.tipoUsu.idTpu==1}">

							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Reportes</a>
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="newCliente">Nuevo</a> <a
										class="dropdown-item" href="readClientes">Clientes</a>
										
								</div></li>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${userLoggin.tipoUsu.idTpu==1}">
							<li class="nav-item"><a class="nav-link" href="cargarCo">Configuracion</a></li>
							
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${userLoggin==null}">
							<li class="nav-item"><a class="nav-link" href="login">Iniciar
									Session</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">${userLoggin.usuario}</a>
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="closelogin">Cerrar session</a>
								</div></li>
						</c:otherwise>
					</c:choose>
					
				</ul>
			</div>
		</nav>


	</header>