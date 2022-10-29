package com.araa.ncd.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.araa.ncd.controller.RegistrationServlet;
import com.araa.ncd.model.Result;


public class ResultDao {

	public int resultPatient(Result result) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "UPDATE patientinfo SET" +
            "  score = ? , screening = ? WHERE aadhaar_uid = ? ;" ;
        
        
        int statement = 0;
        		
        Class.forName("com.mysql.jdbc.Driver");
        

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/ncddb?useSSL=false", "ncdw", "1234");

            //Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, result.getScore());
            preparedStatement.setString(2, result.getScreening() );
            preparedStatement.setString(3, RegistrationServlet.aadhaar);

            System.out.println(preparedStatement);
            // Execute the query or update query
            statement = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return statement;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
	
}
