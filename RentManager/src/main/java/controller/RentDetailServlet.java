package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/rents/details")
public class RentDetailServlet extends HttpServlet {
	
	ClientService clientservice = ClientService.getInstance();
	VehicleService vehicleservice=VehicleService.getInstance();
	ReservationService reservationservice=ReservationService.getInstance();
			
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/details.jsp");
		int id_reservation=Integer.parseInt(request.getParameter("id"));
		try {
			Reservation myreserv = reservationservice.findById(id_reservation);
			int client_id = myreserv.getClient_id();
			int vehicle_id=myreserv.getVehicle_id();
			Vehicle myvehicle = vehicleservice.findById(vehicle_id);
			Client myclient = clientservice.findById(client_id);
			
			request.setAttribute("myclient", myclient);
			request.setAttribute("myvehicle", myvehicle);
			request.setAttribute("myreservation", myreserv);
		}catch(ServiceException e) {
			
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doGet(request,response);
	}

}
