package com.example.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	
	public void createEmployeeTableDB() throws SQLException{
		String sql="CREATE TABLE IF NOT EXISTS employees("
				+ "employeeId INT AUTO_INCREMENT PRIMARY KEY,"
				+ "employeeName VARCHAR(100) NOT NULL,"
				+ "employeeDepartment VARCHAR(100) NOT NULL,"
				+ "employeeSalary DECIMAL(10,2))";
		
		try(Connection conn=EmployeeDBConn.getConnection();
				Statement statement=conn.createStatement()){
			statement.execute(sql);			
		}
	}
	public int addEmployeeDB(Employee emp) throws SQLException{
		String sql="INSERT INTO employees(employeeName,employeeDepartment,employeeSalary) VALUES (?,?,?)";
		try(Connection conn=EmployeeDBConn.getConnection();
				PreparedStatement preparedStatement=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			preparedStatement.setString(1,emp.getEmployeeName());
			preparedStatement.setString(2,emp.getEmployeeDepartment());
			preparedStatement.setDouble(3, emp.getEmployeeSalary());
			int affectedRows=preparedStatement.executeUpdate();	
			if(affectedRows>0) {
				try(ResultSet generatedKeys=preparedStatement.getGeneratedKeys()){
					if(generatedKeys.next()) {
						int employeeId=generatedKeys.getInt(1);
						return employeeId;
					}
				}
			}
			 throw new SQLException("Insertion failed, no ID obtained.");
			
		}
	}
	public List<Employee> getAllEmployeesDB() throws SQLException{
		List<Employee> employees=new ArrayList<Employee>();
		String sql="SELECT employeeId,employeeName,employeeDepartment,employeeSalary FROM employees";
		
		try(Connection conn=EmployeeDBConn.getConnection();
				Statement statement=conn.createStatement()){
				ResultSet resultSet=statement.executeQuery(sql);
				
				while(resultSet.next()) {
					employees.add(new Employee(
							resultSet.getInt("employeeId"),
							resultSet.getString("employeeName"),
							resultSet.getString("employeeDepartment"),
							resultSet.getDouble("employeeSalary")
							));
				}
				return employees;
		}
	}
	
	public void updateEmployeeDB(Employee emp) throws SQLException{
		String sql="UPDATE employees SET employeeName=?,employeeDepartment=?,employeeSalary=? WHERE employeeId=?";
		
		try(Connection conn=EmployeeDBConn.getConnection();
				PreparedStatement preparedStatement=conn.prepareStatement(sql)){
			    preparedStatement.setString(1, emp.getEmployeeName());
			    preparedStatement.setString(2, emp.getEmployeeDepartment());
			    preparedStatement.setDouble(3, emp.getEmployeeSalary());
			    preparedStatement.setInt(4, emp.getEmployeeId());
			    preparedStatement.executeUpdate();
		}
	}
	public void deleteEmployeeDB(int id) throws SQLException{
		String sql="DELETE FROM employees WHERE employeeId=?";
		try(Connection conn=EmployeeDBConn.getConnection();
				PreparedStatement preparedStatement=conn.prepareStatement(sql)){
			    preparedStatement.setInt(1, id);
			    preparedStatement.executeUpdate();
		}
	}
	public static Employee getEmployeeByIDDB(int id) throws SQLException{
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
