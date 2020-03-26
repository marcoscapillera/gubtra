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

import beans.BeanTramites;
import dao.DaoTramites;

@WebServlet("/salvarTramites")
@MultipartConfig
public class tramites extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoTramites daoTramites = new DaoTramites();

	public tramites() {
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

				daoTramites.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/homeTramites.jsp");
				request.setAttribute("usuarios", daoTramites.listar());

				view.forward(request, response);

			}

			else if (accion.equalsIgnoreCase("edit")) {

				BeanTramites beantramites = daoTramites.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/homeTramites.jsp");
				request.setAttribute("user", beantramites);
				view.forward(request, response);

			} else if (accion.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/homeTramites.jsp");
				request.setAttribute("usuarios", daoTramites.listar());

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

				RequestDispatcher view = request.getRequestDispatcher("/homeTramites.jsp");
				request.setAttribute("usuarios", daoTramites.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exeption dopost");
			}

		} else {
			System.out.println("===========");
			System.out.println("antes de asignar variable");

			String id = request.getParameter("Id");
			String tramite = request.getParameter("tramite");
			String dir = request.getParameter("dir");
			String fecha = request.getParameter("fecha");
			String hora = request.getParameter("hora");
			String costo = request.getParameter("costo");

			BeanTramites tramites = new BeanTramites();
			tramites.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			tramites.setFecha(fecha);
			tramites.setHora(hora);
			tramites.setCosto(costo);
			tramites.setTramite(tramite);
			tramites.setDir(dir);

			System.out.println("Los datos que ingreso el usuario son los siguientes");
			System.out.println(tramites.getTramite());
			System.out.println(tramites.getFecha());
			System.out.println(tramites.getHora());
			System.out.println(tramites.getCosto());
			
			
			

			
			try {
				/*Inicio File upload de imagenes e pdf*/
				
				if (ServletFileUpload.isMultipartContent(request)){

					Part imagemFoto = request.getPart("foto");
					
					String fotoBase64 = new Base64().encodeBase64String(converteStremParabyte(imagemFoto.getInputStream()));
					
					tramites.setFotoBase64(fotoBase64);
					tramites.setContentType(imagemFoto.getContentType());
				}
				
				
				

				if (id == null || id.isEmpty()) {
					daoTramites.salvar(tramites);

				} else {
					daoTramites.actualizar(tramites);
				}

				RequestDispatcher view = request.getRequestDispatcher("/homeTramites.jsp");
				request.setAttribute("usuarios", daoTramites.listar());
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