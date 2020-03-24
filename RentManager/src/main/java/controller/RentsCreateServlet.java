package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/rents/create")
public class RentsCreateServlet extends HttpServlet{
	ReservationService reservationservice=ReservationService.getInstance();
	VehicleService vehicleservice=VehicleService.getInstance();
	ClientService clientservice=ClientService.getInstance();
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
		try {
			List<Vehicle> listallvehicle=vehicleservice.findAll();
			List<Client> listallclients=clientservice.findAll();
			request.setAttribute("listevehicles", listallvehicle);
			request.setAttribute("listeclients", listallclients);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		
		
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
			String vehicle_id = request.getParameter("car");
			String client_id=request.getParameter("client");
			Date debut=Date.valueOf(request.getParameter("begin"));
			Date fin=Date.valueOf(request.getParameter("end"));
			Reservation reservation = new Reservation(Integer.parseInt(client_id),Integer.parseInt(vehicle_id),debut,fin);
			try {
				reservationservice.checkDate(debut, fin,Integer.parseInt(vehicle_id));
				reservationservice.create(reservation);
				response.sendRedirect("/RentManager/rents");
				return;
			} catch (ServiceException e) {
				HttpSession session = request.getSession(true);	
				if(e.getMessage()=="Dates invalides") {
					session.setAttribute("erroraddreservation", "le véhicule est déja loué dans cette période");
				}
				if(e.getMessage()=="Impossible de louer un véhicule plus de 7 jours") {
					session.setAttribute("erroraddreservation", "impossible de louer un véhicule plus de 7 jours !");
				}
			}
			doGet(request,response);
			
	}
}
