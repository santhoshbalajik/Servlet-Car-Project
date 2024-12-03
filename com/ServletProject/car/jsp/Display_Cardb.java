package com.ServletProject_CarDB;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Display_Cardb extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int carId = Integer.parseInt(req.getParameter("carid"));
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb", "root", "root");

			PreparedStatement pst =con.prepareStatement("SELECT * FROM car WHERE carId=?");
			pst.setInt(1, carId);

			ResultSet rs  = pst.executeQuery();

			PrintWriter out = res.getWriter();
			while(rs.next()) {
				out.print("<h1>CarId= "+ rs.getInt(1)+ "</h1>");
				out.print("<h1>CarModel= "+ rs.getString(2)+ "</h1>");
				out.print("<h1>CarBrand= "+ rs.getString(3)+ "</h1>");
				out.print("<h1>CarPrice= "+ rs.getString(4)+ "</h1>");
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
