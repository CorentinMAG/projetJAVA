package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.ModeleCarac;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.model.VehicleReservation;
import com.ensta.rentmanager.service.ReservationService;

@WebServlet("/rents/delete")
public class RentsDeleteServlet extends HttpServlet{
ReservationService reservationservice = ReservationService.getInstance();
	
	private static final long serialVersionUID=1L;
	
	
		
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				reservationservice.deleteReservation(id);
			} catch (ServiceException e) {
				HttpSession session = request.getSession(true);	
				session.setAttribute("errordeletereservation", "la réservation ne peut pas être supprimée car elle est liée à des clients !");
			}
			//dispatcher.forward(request, response);
			response.sendRedirect("/RentManager/rents");
			return;
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
			doGet(request,response);
		}
}
