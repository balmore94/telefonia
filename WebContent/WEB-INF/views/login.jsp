
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
<link rel="stylesheet" type="text/css" href="assets/css/util.css">
<link rel="stylesheet" type="text/css" href="assets/css/main.css">
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="login"
					method="Post">
					<span class="login100-form-title p-b-70"> Login </span>

					<div class="wrap-input100 validate-input m-t-85 m-b-35"
						data-validate="Ingrese su usuario">
						<input id="user" class="input100" type="text" name="user"
							 required> <span class="focus-input100"
							data-placeholder="Usuario"></span>
							<label id="lavel"></label>
					</div>

					<div class="wrap-input100 validate-input m-b-50"
						data-validate="Ingrese su contraseña">
						<input class="input100" type="password" name="pass" required> <span
							class="focus-input100" data-placeholder="Clave"></span>
					</div>
					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn">Ingresar</button>
					</div>
				</form>
				${msg}
			</div>
		</div>
	</div>
</body>
</html>