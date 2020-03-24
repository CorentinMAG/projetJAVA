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
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/cars/update")
public class VehicleUpdateServlet extends HttpServlet {
VehicleService vehicleservice = VehicleService.getInstance();
	
	private static final long serialVersionUID=1L;
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp");
		String id = request.getParameter("id");
		try {
			Vehicle vehicle =vehicleservice.findById(Integer.parseInt(id));
			request.setAttribute("Myvehicle", vehicle);
		} catch (NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		if(request.getParameter("constructeur").length()>0) {try {
			vehicleservice.updateConstructeur(request.getParameter("constructeur"), id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdatevehicle", "le constructeur est invalide !");
			e.printStackTrace();
		}}else {}
		if(request.getParameter("modele").length()>0) {try {
			vehicleservice.updateModele(request.getParameter("modele"), id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdatevehicle", "le modèle est invalide !");
			e.printStackTrace();
		}}else {}
		if(request.getParameter("places").length()>0) {try {
			vehicleservice.updateNb_places(Integer.parseInt(request.getParameter("places")), id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdatevehicle", "le nombre de places est invalide !");
			e.printStackTrace();
		}}else {}
		response.sendRedirect("/RentManager/cars");
		return;
		
	}
}
