package controller;
import java.io.IOException;
import java.sql.Date;
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
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.ReservationClientVehicle;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/cars/detail")
public class VehicleDetailServlet extends HttpServlet{
	ReservationService reservationservice =ReservationService.getInstance();
	ClientService clientservice = ClientService.getInstance();
	VehicleService vehicleservice=VehicleService.getInstance();
	private static final long serialVersionUID=1L;
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/detail.jsp");
		int id_cars = Integer.parseInt(request.getParameter("id"));
		try {
			Vehicle vehicle=vehicleservice.findById(id_cars);
			List<Reservation> allreserv = reservationservice.findVehicleReservations(id_cars);
			List<Client> allclient = new ArrayList<Client>();
			List<ReservationClientVehicle> reservationclientvehicle=new ArrayList<ReservationClientVehicle>();
			List nbClients=new ArrayList();
			for(Reservation resv : allreserv) {
				int id_client=resv.getClient_id();
				Client client=clientservice.findById(id_client);
				ReservationClientVehicle rcv = new ReservationClientVehicle(resv.getId(),client.getEmail(),client.getNom()+" "+client.getPrenom(),resv.getDebut(),resv.getFin());
				reservationclientvehicle.add(rcv);
				if(nbClients.contains(resv.getClient_id())==false) {nbClients.add(resv.getClient_id());}
			}
			for(Object nb:nbClients) {
				Client client=clientservice.findById((int)nb);
				allclient.add(client);
			}
			request.setAttribute("modele", vehicle.getModele());
			request.setAttribute("constructeur", vehicle.getConstructeur());
			request.setAttribute("reservVoiture", reservationclientvehicle);
			request.setAttribute("clients", allclient);
			request.setAttribute("nbreservations", allreserv.size());
			request.setAttribute("nbClients", allclient.size());
		} catch (ServiceException e) {
		}
		
		dispatcher.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
	}
	

}
