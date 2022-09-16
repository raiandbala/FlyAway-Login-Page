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

public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String userId = req.getParameter("uid");
		String password = req.getParameter("pwd");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3305/flyaway", "root", "root");
			PreparedStatement pst= con.prepareStatement("Insert into admin values(?,?,?)");
			
			pst.setString(1, name);
			pst.setString(2, userId);
			pst.setString(3, password);
			int i = pst.executeUpdate();
			out.println(" updated, number of row changed = "+i);

			RequestDispatcher rd = req.getRequestDispatcher("/form1.html");
			rd.forward(req,res);	
			
		}
		catch(Exception E) {
			
			out.println("SQL Exception");
		}
		
	}

}
