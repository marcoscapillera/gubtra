<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GubTra</title>

<link rel="stylesheet" href="resources/css/estilo.css">


</head>
<body>

<div class="login-page">
  <div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				Login: <input type="text" id="login" name="login"> <br>
				Password: <input type="password" id="pass" name="pass"> <br>

				<button type="submit" value="Loguear">Login</button>

			</form>
			
	</div>
</div>	

<div align="center">

<img src="resources/img/logoSello.png" width=20% height=20%>


</div>

</body>





</html>




