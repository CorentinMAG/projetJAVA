package controller;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;

@WebServlet("/rents/update")
public class RentsUpdateServlet extends HttpServlet{
ReservationService reservationservice =ReservationService.getInstance();
	private static final long serialVersionUID=1L;
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/update.jsp");
		String id = request.getParameter("id");
		System.out.println(id);
		try {
			Reservation reservation =reservationservice.findById(Integer.parseInt(id));
			request.setAttribute("Myresa", reservation);
		} catch (NumberFormatException | ServiceException e) {
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		if(request.getParameter("date_debut").length()>0) {try {
			reservationservice.updatedebut(request.getParameter("date_debut"), id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdateresa", "date de début invalide !");
			e.printStackTrace();
		}}else {}
		if(request.getParameter("date_fin").length()>0) {try {
			reservationservice.updatefin(request.getParameter("date_fin"), id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdateresa", "date de fin invalide !");
			e.printStackTrace();
		}}else {}
		response.sendRedirect("/RentManager/rents");
		return;
		
	}

}
