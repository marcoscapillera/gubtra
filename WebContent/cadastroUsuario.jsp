<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Decalaraçion  tag del jstl para mostrar los datos recuperados del banco en la  tabla -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro de Usuario</title>

<link rel="stylesheet" href="resources/css/registro.css">
</head>
<body>

	<div class="rectangulo">
		
		<h3>${msg}</h3>
	
	</div> 
	
	

	<form action="salvarUsuario" method="post" id="formUser" onsubmit=" return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>

				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id }" class="field-long"></td>
					</tr>
					
					<tr>
						<td>Nombre:</td>
						<td><input type="text" id="name" name="name"
							value="${user.name}"></td>
					</tr>
					
					
					<tr>
						<td>User:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login }"></td>
					</tr>

					<tr>
						<td>Password:</td>
						<td><input type="password" id="pass" name="pass"
							value="${user.pass}"></td>
					</tr>
					


					<tr>

						<td></td>
						<td><input type="submit" value="Guardar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?accion=reset'"></td>

					</tr>

				</table>





			</li>

		</ul>
	</form>


	<div class="container">
	<table class="responsive-table">
	<caption>Usuarios Registrados</caption>
	    <thead>
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Nombre</th>
        <th scope="col">User</th>
        <th scope="col">Borrar</th>
        <th scope="col">Editar</th>
      
      </tr>
    </thead>

		<c:forEach items="${usuario}" var="user">
			<tr>
				
				
				
					<!-- Se crea una linea para cada registro -->
					<td>
						<!-- Se crea un celda para cada registro--> <c:out value="${user.id}"></c:out> <!-- Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>

					<td>
						<!-- Se crea un celda para cada registro --> <c:out value="${user.name}"></c:out> <!-- Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>
					<td>
						<!--Se crea un celda para cada registro --> <c:out value="${user.login }"></c:out> <!--  Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language-->
					</td>




				<td style="width: 150px"><a href="salvarUsuario?accion=delete&user=${user.id }"><img src="resources/img/btn_excluir.png" alt="Delet" title="Delete"width=20px height=20px ></a></td>
				<td style="width: 150px"><a href="salvarUsuario?accion=edit&user=${user.id }"><img src="resources/img/btn_edit.png" alt ="Editar" title="Edit"width=20px height=20px ></a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<script type="text/javascript">

function validarCampos(){
	
	if (document.getElementById("login").value == '' ){
		
	
	alert('Ingrese Usuario');
	return false;
	}
	else
	if (document.getElementById("pass").value == '' ){
		
	alert('Ingrese la contraseña');
	return false;
	}
	else
	if (document.getElementById("name").value == '' ){
		
		alert('Ingrese el nombre');
		return false;
		}
		
	return true;
	
}



</script>


</body>
</html>