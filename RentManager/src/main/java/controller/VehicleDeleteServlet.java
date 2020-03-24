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
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/cars/delete")
public class VehicleDeleteServlet extends HttpServlet{
VehicleService vehicleservice = VehicleService.getInstance();
	
	private static final long serialVersionUID=1L;
	
	
		
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/list.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				vehicleservice.delete(id);
			} catch (ServiceException e) {
				HttpSession session = request.getSession(true);	
				session.setAttribute("errordeletevehicle", "le vehicule ne peut pas être supprimé car il est lié à une réservation !");
			}
			//dispatcher.forward(request, response);
			response.sendRedirect("/RentManager/cars");
			return;
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
			doGet(request,response);
		}
}
