package com.araa.ncd.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.araa.ncd.dao.ResultDao;
import com.araa.ncd.model.Result;

@WebServlet("/resultServlet")
public class ResultServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ResultDao resultDao;
	
	
    public void init() {
        resultDao = new ResultDao();
        
    }
  
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int score = -1;
		String screening=null;

		// Reading post parameters from the request
		int age = Integer.parseInt(request.getParameter("age"));
		int smoke = Integer.parseInt(request.getParameter("smoke"));
		int alcohol = Integer.parseInt(request.getParameter("alcohol"));
		int waist = Integer.parseInt(request.getParameter("waist"));
		int phy_act = Integer.parseInt(request.getParameter("phy_act"));
		int fam_his = Integer.parseInt(request.getParameter("fam_his"));
		
		score = age + smoke + alcohol + waist + phy_act + fam_his;
		
		if (score > 4)
			screening ="yes";
		if (score <= 4)
			screening="no";

        
        Result result =new Result();
        result.setScore(score);
        result.setScreening(screening);
        
        
        try {
			resultDao.resultPatient(result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>NCD Risk Assessment Checklist</title> ");
		out.println("</head>");
		out.println("<body>");
		out.println("<table align=center border=1>");
		out.println("<tr>");
		out.println("<th colspan=2 style=background-color:lightgreen>NCD Risk Assessment  Checklist Score</th>");
		out.println("</tr>");

		out.println("<tr style=background-color:skyblue>");
		out.println("<th>Question</th>");
		out.println("<th>Score</th>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>1. What is your age? (in complete years)</td>");
		out.println("<td>" + age + "</td> ");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>2. Do you smoke or Consume smokeless product like Gutka or Khaini?</td>");
		out.println("<td>" + smoke + "</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>3. Do you consume alcohol daily?</td>");
		out.println("<td>" + alcohol + "</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>4. Measurement of waist in (cm)</td>");
		out.println("<td>" + waist + "</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>5. Do you undertake any physical activities for a minimum of 150 minutes a week?</td>");
		out.println("<td>" + phy_act + "</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println(
				"<td>6. Do you have a family history (any one of your parents or siblings) of high blood pressure, diabetes and heart disease?</td>");
		out.println("<td>" + fam_his + "</td>");
		out.println("</tr>");

		out.println("<tr style=text-align:center>");
		out.println("<th rowspan=2 style=background-color:lightpink align=left>Total Score: </th>");
		out.println("<td>" + score + "</td>");
		out.println("</tr>");

		out.println("</table>");

		if (score == -1)
			out.println("<h2>Please answer the questions first.");
		if (score > 4)
			out.println("The person may be at higher risk of NCDs and needs to be prioritized for"
					+ " attending screening.");
		if (score < 4)
			out.println("<br>The person is not at risk of NCDs and doesn't need screening.");

		out.println("</h2><br><br><button onclick=location.href='checklist.jsp'; align=center>Previous</button>");
		out.println(
				"<button onclick=location.href='index.jsp'; align=center>Back to Registration Page</button>");
		out.println("</body>");
		out.println("</html>");
	}

}
