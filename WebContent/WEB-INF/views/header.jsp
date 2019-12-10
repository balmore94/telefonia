<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.telefonia.models.Usuarios"%>
<%@page session="true"%>
<%
	HttpSession session2 = request.getSession(false);
	Usuarios us = (Usuarios) session.getAttribute("userLoggin");

	if (us == null) {
		response.sendRedirect("login");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    
    <title></title>
    
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/font-awesome/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/elegant_font/style.css" />
    <!--[if lte IE 7]><script src="elegant_font/lte-ie7.js"></script><![endif]-->
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/slider-pro.css">
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/owl.carousel.css">
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/owl.theme.css">
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/owl.transitions.css">
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/animate.css">
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/elegant_font/style.css"> 
    <link rel="stylesheet" href="${pageContext.request.servletContext.contextPath}/assets/css/style.css"> 

    <!--[if lt IE 9]>
        <script src="js/html5shiv.min.js"></script>
        <script src="js/respond.min.js"></script>
        <script type="text/javascript" src="js/selectivizr.js"></script>
    <![endif]-->
</head>
