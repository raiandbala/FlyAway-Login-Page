package com.Prototype;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Code1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String userId = req.getParameter("uid");
		String password = req.getParameter("pwd");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/flyaway", "root", "root");
			PreparedStatement pst= con.prepareStatement("select * from admin where name = ?");
			
			pst.setString(1, name);
			
		ResultSet rs = 	pst.executeQuery();
		if(rs.next() && name.equalsIgnoreCase(rs.getString(1))&& userId.equalsIgnoreCase(rs.getString(2))&& password.equals(rs.getString(3))    ) {
			out.println("<h1>");
			out.println("Welcome to FlyAway Mr./Ms."+ name);
			out.println("</h1>");
		
		}
		else {
		
			RequestDispatcher rd = req.getRequestDispatcher("/form2.html");
			rd.forward(req,res);	
			
		}
		}
		catch(Exception e){
			out.println(e);
		}
		
	}

}
