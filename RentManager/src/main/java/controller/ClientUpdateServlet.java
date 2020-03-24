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
import com.ensta.rentmanager.service.ClientService;

@WebServlet("/users/update")
public class ClientUpdateServlet extends HttpServlet {
ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID=1L;
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/update.jsp");
		String id = request.getParameter("id");
		try {
			Client client =clientservice.findById(Integer.parseInt(id));
			request.setAttribute("Myclient", client);
		} catch (NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		if(request.getParameter("first_name").length()>0) {try {
			clientservice.updatePrenom(request.getParameter("first_name"),id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdateclient", "le nom est invalide !");
			e.printStackTrace();
		}}else {}
		if(request.getParameter("last_name").length()>0) {try {
			clientservice.updateName(request.getParameter("last_name"),id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdateclient", "le prenom est invalide !");
			e.printStackTrace();
		}}else {}
		if(request.getParameter("naissance").length()>0) {try {
			clientservice.updateBirth(request.getParameter("naissance"), id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdateclient", "le client doit avoir plus de 18 ans !");
			e.printStackTrace();
		}}else {}
		if(request.getParameter("email").length()>0) {try {
			clientservice.updateEmail(request.getParameter("email"), id);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(true);	
			session.setAttribute("errorupdateclient", "l'adresse email est invalide !");
			e.printStackTrace();
		}}else {}
		response.sendRedirect("/RentManager/users");
		return;
		
	}
}
