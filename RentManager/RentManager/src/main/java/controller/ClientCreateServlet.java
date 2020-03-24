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

@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet{
ClientService clientservice = ClientService.getInstance();
	
	private static final long serialVersionUID=1L;
	
	
		
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
			
			
			dispatcher.forward(request, response);
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
				String last_name=request.getParameter("last_name");
				String first_name=request.getParameter("first_name");
				String email=request.getParameter("email");
				String birth=request.getParameter("naissance");
				Client client=new Client(last_name,first_name,email,birth);
				try {
					clientservice.verifynp(last_name, first_name);
					clientservice.verifymail(email);
					clientservice.create(client);
					response.sendRedirect("/RentManager/users");
					return;
				} catch (ServiceException e) {
					HttpSession session = request.getSession(true);	
					if(e.getMessage()=="Le mail existe déjà!") {
						session.setAttribute("erroraddclient", "le mail existe déjà !");
					}else if(e.getMessage()=="le client doit avoir 18 ans"){
						session.setAttribute("erroraddclient", "le client doit avoir plus de 18 ans !");
					}else {
						session.setAttribute("erroraddclient", "Le nom/Prénom doit contenir plus de 3 caractères !");
					}
					doGet(request,response);
				}
				
				
		}
}
