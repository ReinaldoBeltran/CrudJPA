package co.edu.ufps.CrudJPA.Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.CrudJPA.DAO.UserDAO;
import co.edu.ufps.CrudJPA.modelo.User;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
	 public void init() {
	        userDAO = new UserDAO();
	    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String action = request.getServletPath();
		
		System.out.println(action);
		try {
		switch(action) {
		case "/new":
			showNewForm(request,response);
			break;
		case "/insert":
			insertUser(request,response);
			break;
		case "/delete":
            deleteUser(request, response);
            break;
		case "/edit":
            showEditForm(request, response);
            break;
        case "/update":
            updateUser(request, response);
            break;        
	
		default: 
			listUser(request,response);
			break;	
			}
		}catch (SQLException ex) {
            throw new ServletException(ex);
        }	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		        dispatcher.forward(request, response);
		    }
	private void listUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < User > listUser = userDAO.selectedAll();
		        request.setAttribute("listUser", listUser);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("list-user.jsp");
		        dispatcher.forward(request, response);
		    }


	private void insertUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        String name = request.getParameter("name");
		        String email = request.getParameter("email");
		        String country = request.getParameter("country");
		        User newUser = new User(name, email, country);
		        userDAO.insert(newUser);
		        response.sendRedirect("list");
		    }
	
	 private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        int id = Integer.parseInt(request.getParameter("id"));
			        User user = userDAO.select(id);
			        userDAO.borrarUser(user);
			        response.sendRedirect("list");

			    }
	 
	 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException {
			        int id = Integer.parseInt(request.getParameter("id"));
			        User existingUser = userDAO.buscarUser(id);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			        request.setAttribute("user", existingUser);
			        dispatcher.forward(request, response);

			    }
	  private void updateUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        int id = Integer.parseInt(request.getParameter("id"));
			        String name = request.getParameter("name");
			        String email = request.getParameter("email");
			        String country = request.getParameter("country");

			        User user = userDAO.buscarUser(id);			        
			        user.setName(name);
			        user.setEmail(email);
			        user.setCountry(country);
			        userDAO.actualizarUser(user);
			        response.sendRedirect("list");
			    }

}
