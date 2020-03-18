package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanTramites;
import dao.DaoTramites;

@WebServlet("/salvarTramites")
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
		System.out.println(request.getParameter("entro a dopost"));

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

			String id = request.getParameter("Id");
			String tramite = request.getParameter("tramite");
			String fecha = request.getParameter("fecha");
			String costo = request.getParameter("costo");

			BeanTramites tramites = new BeanTramites();
			tramites.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			tramites.setFecha(fecha);
			tramites.setCosto(costo);
			tramites.setTramite(tramite);

			System.out.println("Los datos que ingreso el usuario son los siguientes");
			System.out.println(tramites.getTramite());
			System.out.println(tramites.getFecha());
			System.out.println(tramites.getCosto());

			try {

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
}