<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Decalaraçion  tag del jstl para mostrar los datos recuperados del banco en la  tabla -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertar Tramites</title>

<link rel="stylesheet" href="resources/css/estilotramites.css">
</head>
<body>
	
	<div class="container">
	<div class="rectangulo">
		
		<h3>${msg}</h3>
	
	</div> 
	
	<h4>INGRESAR TRÁMITES</h4>

	<form action="salvarTramites" method="post" id="formUser" onsubmit=" return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>

				<table class="tabla">
					<tr>
						<td>Código:</td>
						<td><input placeholder="Generado Automaticamente"  type="text" readonly="readonly" id="Id" name="Id"
							value="${user.id }" class="field-long"></td>
					</tr>
					
					<tr>
						<td>Nombre del trámite:</td>
						<td><input placeholder="  Ingrese el nombre" type="text" id="tramite" name="tramite"
							value="${user.tramite}"></td>
					</tr>
					
					
					<tr>
						<td>Fecha de trámite:</td>
						<td><input placeholder="  Ingrese fecha 00/00/0000 "  type="text" id="fecha" name="fecha"
							value="${user.fecha }"></td>
					</tr>
					
					<tr>
						<td>Costo $ :</td>
						<td><input placeholder="Ingrese monto" type="text" id="costo" name="costo"
							value="${user.costo }"></td>
					</tr>



					<tr>

						<td></td>
						<td><input type="submit" value="Guardar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarTramites?accion=reset'"></td>

					</tr>

				</table>





			</li>

		</ul>
	</form>


	
	<table class="responsive-table" >
	<caption>Trámites Pendientes</caption>
	   <thead>
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Nombre Tramite</th>
        <th scope="col">Fecha de Trámite</th>
        <th scope="col">Costo</th>
        <th scope="col">Borrar</th>
        <th scope="col">Editar</th>
      
      </tr>
    </thead>
		
		<tbody>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				
					<!-- Se crea una linea para cada registro -->
					<td data-titulo="Id">
						<!-- Se crea un celda para cada registro--> <c:out value="${user.id}"></c:out> <!-- Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>

					<td data-titulo="Tramite">
						<!-- Se crea un celda para cada registro --> <c:out value="${user.tramite}"></c:out> <!-- Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>
					<td data-titulo="Fecha">
						<!--Se crea un celda para cada registro --> <c:out value="${user.fecha }"></c:out> <!--  Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language-->
					</td>
					
					<td data-titulo="Costo">
						<!--Se crea un celda para cada registro --> <c:out value="${user.costo }"></c:out> <!--  Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language-->
					</td>


				<td data-titulo="Borrar" style="width: 150px"><a href="salvarTramites?accion=delete&user=${user.id }"><img src="resources/img/btn_excluir.png" alt="Delet" title="Delete"width=20px height=20px ></a></td>
				<td data-titulo="Editar"style="width: 150px"><a href="salvarTramites?accion=edit&user=${user.id }"><img src="resources/img/btn_edit.png" alt ="Editar" title="Edit"width=20px height=20px ></a></td>
			</tr>
		</c:forEach>
		</tbody>
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