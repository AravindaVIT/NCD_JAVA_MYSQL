package com.araa.ncd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.araa.ncd.model.Registration;

public class RegistrationDao {

	 public int registerPatient(Registration register) throws ClassNotFoundException {
	        String INSERT_USERS_SQL = "INSERT INTO patientinfo" +
	            "  (id, first_name, last_name, gender, aadhaar_uid, phone_no, dob, pincode) VALUES " +
	            " (?, ?, ?, ?, ?,?,?,?);";

	        int result = 0;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/ncddb?useSSL=false", "ncdw", "1234");

	            //Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setInt(1, 1);
	            preparedStatement.setString(2, register.getFirstname());
	            preparedStatement.setString(3, register.getLastname());
	            preparedStatement.setString(4, register.getGender());
	            preparedStatement.setString(5, register.getAadhaar());
	            preparedStatement.setString(6, register.getPhone());
	            preparedStatement.setString(7, register.getBirthday());
	            preparedStatement.setInt(8, register.getPincode());

	            System.out.println(preparedStatement);
	            // Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
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
