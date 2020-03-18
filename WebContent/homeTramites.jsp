<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Decalaraçion  tag del jstl para mostrar los datos recuperados del banco en la  tabla -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title>GubTra</title>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
<link rel="stylesheet" href="resources/css/estilohome.css">
<script>
  function openSlideMenu(){
    document.getElementById('menu').style.width = '250px';
    document.getElementById('content').style.marginLeft = '250px';
  }
  function closeSlideMenu(){
    document.getElementById('menu').style.width = '0';
    document.getElementById('content').style.marginLeft = '0';
  }
</script>
</head>
<body>

	<div id="content">

		<span class="slide"> <a href="#" onclick="openSlideMenu()">
				<i class="fas fa-bars"></i>
		</a>
		</span>

		<div id="menu" class="nav">
			<a href="#" class="close" onclick="closeSlideMenu()"> <i
				class="fas fa-times"></i>
			</a> <img id="img" alt="" src="resources/img/logoSello.png" width=150px height=48px> 
				<a href="index.jsp">Home</a>
				<a href="#about">About</a>
				<a href="homeTramites.jsp">Trámites</a>
			 	<a href="#facturas">Facturas</a> 
			 	<a href="#documentos">Documentos</a> 
				<a href="#arhivos">Archivos</a>
		</div>


		<section id="tramites">
		<br>
		
			<jsp:include page="tramites.jsp"></jsp:include>
			<br>
			<br>
		</section>
		<br>
		<br>
		<br>
		<section id="footer">
		<br>
		<br>
			<jsp:include page="footer.jsp"></jsp:include>
		</section>
	</div>



</body>
</html>
