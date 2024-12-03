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

public class Delete_Cardb extends GenericServlet{
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		// Extracting the values coming from the UI
				int carId = Integer.parseInt(req.getParameter("carid"));
				
				
				// Jdbc logic
				
				Connection con = null;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb","root","root");
					
					PreparedStatement pst = con.prepareStatement("DELETE FROM  car WHERE carId = ?");
				    pst.setInt(1, carId);
				  
				    
				    int rowsInserted = pst.executeUpdate();
				    PrintWriter out = res.getWriter();
				    if(rowsInserted>0) {
				    	out.print("<h1>"+rowsInserted +" deleted SuccessFully");
				    }
				    else {
				    	out.print("data not deleted");
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
