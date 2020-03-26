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

		   <!-- Navbar items -->
    	<div id="navlist"> 
        <a href="#">Salir</a> 
        <a href="#">Sobre Nosotros</a> 
        <a href="#">Sitio</a> 
        <a href="#">Instructivo</a> 
        <a href="#">Contacto</a> 
          
        <!-- seach bar right align -->
        <div class="search"> 
            <form action="#"> 
                <input type="text"
                    placeholder=" Buscar Trámites"
                    name="search"> 
                <button> 
                    <i class="fa fa-search"
                        style="font-size: 18px;"> 
                    </i> 
                </button> 
            </form> 
        </div> 
    </div>
    
    <br><br><br><br>
    
	
	<div class="container">
	<div class="rectangulo">
		
		<h3>${msg}</h3>
	
	</div> 
	<div class="sub-titulo">
	
		<h4>INGRESAR TRÁMITES</h4>
	
	</div>
	
	

	<form  class="card shadow1" action="salvarTramites" method="post" id="formUser" onsubmit=" return validarCampos() ? true : false;" enctype="multipart/form-data" >
		<ul class="form-style-1">
			<li>

				<table class="tabla" style="margin:auto; padding:auto;">
					<tr>
						<td></td>
						<td ><input style="width:100%;" placeholder=" Id Automatico"  type="text" readonly="readonly" id="Id" name="Id"
							value="${user.id }" class="field-long"></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input style="width:100%;" placeholder="  Ingrese el nombre" type="text" id="tramite" name="tramite"
							value="${user.tramite}"></td>
					</tr>
					
					
					<tr>
						<td></td>
						<td><input style="width:100%;" placeholder="  Ingrese Dirección" type="text" id="dir" name="dir"
							value="${user.dir}"></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input style="width:100%;" placeholder="  Ingrese fecha 00/00/0000 "  type="text" id="fecha" name="fecha"
							value="${user.fecha }"></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input style="width:100%;" placeholder="  Ingrese hora 00:00 "  type="text" id="hora" name="hora"
							value="${user.hora }"></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input style="width:100%;" placeholder="Ingrese Costo $" type="text" id="costo" name="costo"
							value="${user.costo }"></td>
					</tr>
					
					<tr>
						<td>
						 
						</td>
						<td >
						
						<label class="custom-file-upload">
						<input  type="file"  name="foto" value="foto" > 
						    Subir Comprobante .pdf .png .jpg
						</label>
						
						</td>
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
        <th scope="col">Dirección</th>
        <th scope="col">Fecha de Trámite</th>
        <th scope="col">Hora</th>
        <th scope="col">Costo</th>
        <th scope="col">Comprobante</th>
        <th scope="col">Borrar</th>
        <th scope="col">Editar</th>
      
      </tr>
    </thead>
		
		<tbody >
		<c:forEach items="${usuarios}" var="user">
			<tr >
				
					<!-- Se crea una linea para cada registro -->
					<td data-titulo="Id">
						<!-- Se crea un celda para cada registro--> <c:out value="${user.id}"></c:out> <!-- Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>

					<td data-titulo="Tramite">
						<!-- Se crea un celda para cada registro --> <c:out value="${user.tramite}"></c:out> <!-- Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>
					
					<td data-titulo="Dirección">
						<!-- Se crea un celda para cada registro --> <c:out value="${user.dir}"></c:out> <!-- Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>
					
					
					<td data-titulo="Fecha">
						<!--Se crea un celda para cada registro --> <c:out value="${user.fecha }"></c:out> <!--  Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language-->
					</td>
					
					<td data-titulo="Hora">
						<!--Se crea un celda para cada registro --> <c:out value="${user.hora }"></c:out> <!--  Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language-->
					</td>
					
				
					<td data-titulo="Costo">
						<!--Se crea un celda para cada registro --> <c:out value="${user.costo }"></c:out> <!--  Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language-->
					</td>
					
					<td data-titulo="Comprobante">
						<!--Se crea un celda para cada registro --><img src='<c:out value="${user.tempFotoTramite }"></c:out>'  width=30px height=25px > <!--  Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language-->
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
	
	if (document.getElementById("tramite").value == '' ){
		
	
	alert('Ingrese Trámite');
	return false;
	}
	else
		if (document.getElementById("dir").value == '' ){
		
		alert('Ingrese Direccion');
		return false;
	}else
		if (document.getElementById("fecha").value == '' ){
			
			alert('Ingrese Fecha');
			return false;
	}else
		if (document.getElementById("hora").value == '' ){
			
			alert('Ingrese hora');
			return false;
		}
	else
		if (document.getElementById("costo").value == '' ){
		
			alert('Ingrese el costo, si no tiene coste ingrese 0');
			return false;
		}
		
	return true;
	
}



</script>



</body>
</html>