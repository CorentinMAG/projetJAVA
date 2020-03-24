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
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.ReservationClientVehicle;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/rents")
public class RentsServlet extends HttpServlet{
	ClientService clientservice = ClientService.getInstance();
	ReservationService reservationservice = ReservationService.getInstance();
	VehicleService vehicleservice=VehicleService.getInstance();
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
		
		try {
			List<Reservation> listallrents = reservationservice.FindAll();
			List<ReservationClientVehicle> listereservationclientvehicle=new ArrayList<ReservationClientVehicle>();
			for(Reservation resv:listallrents) {
				int id_client=resv.getClient_id();
				int id_vehicle=resv.getVehicle_id();
				Client client = clientservice.findById(id_client);
				Vehicle vehicle=vehicleservice.findById(id_vehicle);
				ReservationClientVehicle resvcv=new ReservationClientVehicle(resv.getId(),vehicle.getConstructeur()+" "+vehicle.getModele(),client.getNom()+" "+client.getPrenom(),resv.getDebut(),resv.getFin());
				listereservationclientvehicle.add(resvcv);
			}
			request.setAttribute("reservations", listereservationclientvehicle);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doGet(request,response);
	}
}
