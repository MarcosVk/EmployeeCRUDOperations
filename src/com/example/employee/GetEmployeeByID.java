package com.example.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetEmployeeByID {
	
	public static Employee getEmployeeByID(int id) throws SQLException{
		String sql="SELECT employeeId,employeeName,employeeDepartment,employeeSalary FROM employees WHERE employeeId=?";
		
		try(Connection conn=EmployeeDBConn.getConnection();
				PreparedStatement preparedStatement=conn.prepareStatement(sql);){
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet=preparedStatement.executeQuery();){
				if(resultSet.next()) {
					return new Employee(
							resultSet.getInt("employeeId"),
							resultSet.getString("employeeName"),
							resultSet.getString("employeeDepartment"),
							resultSet.getDouble("employeeSalary")
							);
				}
				return null;
				
			}
			
			
			
		}
	}

}
