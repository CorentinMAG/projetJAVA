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

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.ModeleCarac;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.model.VehicleReservation;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/users/details")
public class ClientDetailServlet extends HttpServlet{
private static final long serialVersionUID=1L;

ClientService clientservice = ClientService.getInstance();
ReservationService reservationservice = ReservationService.getInstance();
VehicleService vehicleservice=VehicleService.getInstance();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			request.setAttribute("MyClient", clientservice.findById(id));
			List<Reservation> clientsReservation=reservationservice.findClientReservations(id);
			List<VehicleReservation> vehicleReservation=new ArrayList<VehicleReservation>();
			List<Vehicle> vehiclemodelecarac=new ArrayList<Vehicle>();
			List nbVoitures=new ArrayList();
			for(Reservation reserv:clientsReservation) {
				int vehicle_id = reserv.getVehicle_id();
				if(nbVoitures.contains(vehicle_id)==false) {nbVoitures.add(vehicle_id);}
				Vehicle vehicle=vehicleservice.findById(vehicle_id);
				VehicleReservation vehicleresvr = new VehicleReservation(reserv.getId(),vehicle.getModele(),reserv.getDebut(),reserv.getFin());
				vehicleReservation.add(vehicleresvr);
			}
			for(Object idv:nbVoitures) {
				Vehicle vehicle=vehicleservice.findById((int)idv);
				vehiclemodelecarac.add(vehicle);
			}
			request.setAttribute("nbreservations",reservationservice.findClientReservations(id).size());
			request.setAttribute("nbvoitures",nbVoitures.size());
			request.setAttribute("reservVoiture",vehicleReservation);
			request.setAttribute("modelevehiclecarac", vehiclemodelecarac);
		}catch(ServiceException e) {
			System.out.println("Error users details");
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doGet(request,response);
	}

}
