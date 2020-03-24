package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.VehicleService;

@WebServlet("/cars")
public class VehicleServlet extends HttpServlet{
private static final long serialVersionUID=1L;

VehicleService vehicleservice=VehicleService.getInstance();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp");
		if(request.getParameter("manufacturer")!=null) {
			String constructeur=request.getParameter("manufacturer");
			String modele=request.getParameter("modele");
			String seats=request.getParameter("seats");
			try {
				Vehicle vehicle=new Vehicle(constructeur,modele,Integer.parseInt(seats));
				vehicleservice.create(vehicle);
			}catch(ServiceException e) {
				System.out.println("error /cars post method");
			}
		}
		try {
			request.setAttribute("listvehicle", vehicleservice.FindAll());
		}catch(ServiceException e) {
			request.setAttribute("listvehicle", "une erreur est survenue");
		}
		
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		doGet(request,response);
	}
}
