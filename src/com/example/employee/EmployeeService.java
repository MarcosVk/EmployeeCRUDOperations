package com.example.employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
	
	private final EmployeeDAO employeeDAO;
	
	public EmployeeService() {
		this.employeeDAO=new EmployeeDAO();
	}
	
	public void createEmployeeTable() throws SQLException{
		employeeDAO.createEmployeeTable();
	}
	
	public void addEmployee(String name,String department,double salary) throws SQLException{
		Employee employee=new Employee(name,department,salary);
		employeeDAO.addEmployee(employee);
	}
	public void updateEmployee(int id,String name,String department,double salary) throws SQLException{
		Employee existingEmployee=GetEmployeeByID.getEmployeeByID(id);
		
		if(existingEmployee==null) {
			System.out.println("Employee not found");
			return;
		}
		if(!name.isEmpty()) {
			existingEmployee.setEmployeeName(name);
		}
		if(!department.isEmpty()) {
			existingEmployee.setEmployeeDepartment(department);
		}
		if(salary!=0) {
			existingEmployee.setEmployeeSalary(salary);
		}
		employeeDAO.updateEmployee(existingEmployee);
	}
	public void deleteEmployee(int id) throws SQLException{
		employeeDAO.deleteEmployee(id);
	}
	public List<Employee> getAllEmployees()throws SQLException{
		return employeeDAO.getAllEmployees();
	}
}
