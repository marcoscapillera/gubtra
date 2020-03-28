<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Decalaraçion  tag del jstl para mostrar los datos recuperados del banco en la  tabla -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertar Documentos</title>

<link rel="stylesheet" href="resources/css/estiloPageDocumentos.css">
</head>
<body style="background-color: #;">

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
	
		<h4>Todos tus  documentos<br> en un solo lugar </h4>
	
	</div>
	
	

	<form  class="card shadow1" action="salvarDocumento" method="post" id="formUser" onsubmit=" return validarCampos() ? true : false;" enctype="multipart/form-data" >
		<ul class="form-style-1">
			<li>

				<table class="tabla" style="margin:auto; padding:auto;">

					
					<tr>
						<td></td>
						<td><input style="width:100%;" placeholder="Numero de Documento" type="text" id="numdocumento" name="numdocumento"
							value="${user.numdocumento}"></td>
					</tr>
					
					
					<tr>
						<td></td>
						<td><input style="width:100%;" placeholder="Nombre" type="text" id="nombredocumento" name="nombredocumento"
							value="${user.nombredocumento}"></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input style="width:100%;" placeholder="Validez 00/00/0000 "  type="text" id="validez" name="validez"
							value="${user.validez }"></td>
					</tr>
					

					
					<tr>
						<td>
						 
						</td>
						<td >
						
						<label class="custom-file-upload">
						<input  type="file"  name="foto" value="foto" > 
						    Subir Documento .pdf .png .jpg
						</label>
						
						</td>
					</tr>

					<tr>

						<td></td>
						<td><input type="submit" value="Guardar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarDocumento?accion=reset'"></td>

					</tr>

				</table>





			</li>

		</ul>
	</form>


	
	<table class="responsive-table" >
	<caption>Documentos Archivados</caption>
	   <thead>
      <tr>
        <th scope="col">Número</th>
        <th scope="col">Documento</th>
        <th scope="col">Validez</th>
		<th scope="col">Imagen</th>
		<th scope="col">Borrar</th>
        <th scope="col">Editar</th>
      
      </tr>
    </thead>
		
		<tbody >
		<c:forEach items="${usuarios}" var="user">
			<tr >
				
					<!-- Se crea una linea para cada registro -->
					<td data-titulo="Número">
						<!-- Se crea un celda para cada registro--> <c:out value="${user.numdocumento}"></c:out> <!-- Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>

					<td data-titulo="Nombre">
						<!-- Se crea un celda para cada registro --> <c:out value="${user.nombredocumento}"></c:out> <!-- Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>
					
					<td data-titulo="Validez">
						<!-- Se crea un celda para cada registro --> <c:out value="${user.validez}"></c:out> <!-- Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language -->
					</td>
									
					<td class="responsiveDoc" data-titulo="Documento">
						<!--Se crea un celda para cada registro --><img  class="mini"  style="border-radius: 10px" src='<c:out value="${user.tempFotoDocumento }"></c:out>'  width=50% height=% > <!--  Tag c:out Tag c:out imprime en pantalla el valor pasado en expression language-->
					</td>

				<td data-titulo="Borrar" style="width: 150px"><a href="salvarDocumento?accion=delete&user=${user.numdocumento }"><img src="resources/img/btn_excluir.png" alt="Delet" title="Delete"width=20px height=20px ></a></td>
				<td data-titulo="Editar"style="width: 150px"><a href="salvarDocumento?accion=edit&user=${user.numdocumento }"><img src="resources/img/btn_edit.png" alt ="Editar" title="Edit"width=20px height=20px ></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">

function validarCampos(){
	
	if (document.getElementById("numdocumento").value == '' ){
		
	
	alert('Ingrese Numero Documento');
	return false;
	}
	else
		if (document.getElementById("nombredocumento").value == '' ){
		
		alert('Ingrese Nombre Documento');
		return false;
	}else
		if (document.getElementById("validez").value == '' ){
			
			alert('Ingrese Validez');
			return false;
	}
		
	return true;
	
}



</script>



</body>
</html>