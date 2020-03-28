package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanDocumento;
import dao.DaoDocumento;


@WebServlet("/salvarDocumento")
@MultipartConfig
public class documento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoDocumento daoDocumento = new DaoDocumento();

	public documento() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		System.out.println("accion");

		try {

			String accion = request.getParameter("accion");
			String user = request.getParameter("user");

			System.out.println(accion);
			System.out.println(user);

			if (accion.equalsIgnoreCase("delete")) {

				daoDocumento.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/homeDocumentos.jsp");
				request.setAttribute("usuarios", daoDocumento.listar());

				view.forward(request, response);

			}

			else if (accion.equalsIgnoreCase("edit")) {

				BeanDocumento beanDocumento = daoDocumento.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/homeDocumentos.jsp");
				request.setAttribute("user", beanDocumento);
				view.forward(request, response);

			} else if (accion.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/homeDocumentos.jsp");
				request.setAttribute("usuarios", daoDocumento.listar());

				view.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===========");
		System.out.println("entro a dopost");
		System.out.println(request.getParameter("accion"));
		String accion = request.getParameter("accion");
		System.out.println("dopost" + accion);

		if (accion != null && accion.equalsIgnoreCase("reset")) {

			try {

				System.out.println("try dopost");

				RequestDispatcher view = request.getRequestDispatcher("/homeDocumentos.jsp");
				request.setAttribute("usuarios", daoDocumento.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exeption dopost");
			}

		} else {
			System.out.println("===========");
			System.out.println("antes de asignar variable");

			String numero_documento = request.getParameter("numdocumento");
			String nombre_documento = request.getParameter("nombredocumento");
			String validez = request.getParameter("validez");
	

			BeanDocumento documento = new BeanDocumento();
			documento.setNumdocumento(Long.parseLong(numero_documento));
			documento.setNombredocumento(nombre_documento);
			documento.setValidez(validez);
	

			System.out.println("Los datos que ingreso el usuario son los siguientes");
			System.out.println(documento.getNumdocumento());
			System.out.println(documento.getNombredocumento());
			System.out.println(documento.getValidez());
			
			
			
			

			
			try {
				/*Inicio File upload de imagenes e pdf*/
				
				if (ServletFileUpload.isMultipartContent(request)){

					Part imagemFoto = request.getPart("foto");
					
					String fotoBase64 = new Base64().encodeBase64String(converteStremParabyte(imagemFoto.getInputStream()));
					
					documento.setFotoBase64(fotoBase64);
					documento.setContentType(imagemFoto.getContentType());
				}
				
				
				

				if (!(numero_documento == null)) {
					daoDocumento.salvar(documento);

				} else {
					daoDocumento.actualizar(documento);
				}

				RequestDispatcher view = request.getRequestDispatcher("/homeDocumentos.jsp");
				request.setAttribute("usuarios", daoDocumento.listar());
				view.forward(request, response);
				System.out.println("Actualiza lista doPost");
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}
		
		/*Convierte la entrada de fluxo de datos de la imagem para byte[]*/
		private byte[] converteStremParabyte(InputStream imagem) throws Exception{
			
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 int reads = imagem.read();
		 while (reads != -1){
			 baos.write(reads);
			 reads = imagem.read();
		 }
		 
		 return baos.toByteArray();
		
		}
}