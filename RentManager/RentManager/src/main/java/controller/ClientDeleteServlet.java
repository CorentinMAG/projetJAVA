package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.service.ClientService;

@WebServlet("/users/delete")
public class ClientDeleteServlet extends HttpServlet{
ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID=1L;
	
	
		
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/list.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				clientservice.deleteClient(id);
			} catch (ServiceException e) {
				HttpSession session = request.getSession(true);	
				session.setAttribute("errordeleteclient", "le client ne peut pas être supprimé car il possède des commandes!");
			}
			//dispatcher.forward(request, response);
			response.sendRedirect("/RentManager/users");
			return;
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
			doGet(request,response);
		}
}
