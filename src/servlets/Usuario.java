package servlets;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
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

				daoUsuario.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());

				view.forward(request, response);

			}

			else if (accion.equalsIgnoreCase("edit")) {

				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);

			} else if (accion.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());

				view.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("accion"));

		String accion = request.getParameter("accion");

		if (accion != null && accion.equalsIgnoreCase("reset")) {
			
			try {
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}else {

		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");

		BeanCursoJsp usuario = new BeanCursoJsp();
		usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
		usuario.setLogin(login);
		usuario.setPass(pass);
		usuario.setName(name);
		
		
		
		
		try {
			
			String msg= null;
			boolean puedeInsertar = true;
			

			if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
				
				msg = "Usuario ya existe!";
				puedeInsertar = false;
				
			} else if (id == null || id.isEmpty() && !daoUsuario.validarPass(pass)) {

			msg= "\n La contraseña ya existe para otro usuario";
			puedeInsertar = false;
			
			}
			
			if( msg != null) {
				request.setAttribute("msg", msg);
			}
			
			
			if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && puedeInsertar) {
				daoUsuario.salvar(usuario);
				
			}else if( id != null &&  !id.isEmpty() && puedeInsertar) {
				daoUsuario.actualizar(usuario);
			}

			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuario", daoUsuario.listar());
			view.forward(request, response);
			System.out.println("Actualiza lista doPost");
		} catch (Exception e) {
			e.printStackTrace();
		

			}
		}

	}
}