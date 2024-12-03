package com.ServletProject_CarDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Add_CarDetails extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// Extracting the values coming from the UI
		int carId = Integer.parseInt(req.getParameter("carid"));
		String carModel=req.getParameter("carmodel");
		String carBrand = req.getParameter("carbrand");
		int carPrice = Integer.parseInt(req.getParameter("carprice"));
		
		// Jdbc logic
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb","root","root");
			
			PreparedStatement pst = con.prepareStatement("INSERT INTO car VALUES(?,?,?,?)");
		    pst.setInt(1, carId);
		    pst.setString(2, carModel);
		    pst.setString(3, carBrand);
		    pst.setInt(4, carPrice);
		    
		    int rowsInserted = pst.executeUpdate();
		    PrintWriter out = res.getWriter();//PrintWriter is used to give some responses to client side
		    if(rowsInserted>0) {
		    	out.print("<h1>"+rowsInserted +" inserted SuccessFully");
		    }
		    else {
		    	out.print("Data Not Inserted");
		    }
		    
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
	}
	
	
	
}
