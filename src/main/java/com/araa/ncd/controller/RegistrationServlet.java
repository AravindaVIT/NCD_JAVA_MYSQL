package com.araa.ncd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.araa.ncd.dao.RegistrationDao;
import com.araa.ncd.model.Registration;


@WebServlet("/registrationServlet")
public class RegistrationServlet extends HttpServlet {
	public static String aadhaar;
	
	private static final long serialVersionUID = 1L;
     
	private RegistrationDao registrationDao;
	
	
    public void init() {
        registrationDao = new RegistrationDao();
        
    }
  
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		
		String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        aadhaar = request.getParameter("aadhaar");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        int pincode = Integer.parseInt(request.getParameter("pincode"));
        
        Registration register =new Registration();
        register.setFirstname(firstname);
        register.setLastname(lastname);
        register.setGender(gender);
        register.setAadhaar(aadhaar);
        register.setPhone(phone);
        register.setBirthday(birthday);
        register.setPincode(pincode);
        
        
        try {
			registrationDao.registerPatient(register);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/checklist.jsp");
		dispatcher.forward(request, response);
        
	}

}
