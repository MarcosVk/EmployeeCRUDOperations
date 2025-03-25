package com.example.employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeApp {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		EmployeeDAO employeeDAO=new EmployeeDAO();
		
		employeeDAO.createEmployeeTable();
		Employee employee1=new Employee("Vicky", "Dev", 100000.00);
		Employee employee2=new Employee("Jim", "QA", 70000.00);
		
		employeeDAO.addEmployee(employee1);
		employeeDAO.addEmployee(employee2);
		
		printAllEmployees(employeeDAO);
		
		List<Employee> employees=employeeDAO.getAllEmployees();
		Employee update=employees.get(0);
		update.setEmployeeSalary(200000.00);
		employeeDAO.updateEmployee(update);
		
		printAllEmployees(employeeDAO);
		
		employeeDAO.deleteEmployee(employee2.getEmployeeId());
		
		printAllEmployees(employeeDAO);
	}
	
	public static void printAllEmployees(EmployeeDAO dao) throws SQLException{
		List<Employee> employees=dao.getAllEmployees();	
		System.out.println("[");
	    for (int i = 0; i < employees.size(); i++) {
	        Employee emp = employees.get(i);
	        System.out.println("  {");
	        System.out.println("    \"id\": " + emp.getEmployeeId() + ",");
	        System.out.println("    \"name\": \"" + emp.getEmployeeName() + "\",");
	        System.out.println("    \"department\": \"" + emp.getEmployeeDepartment() + "\",");
	        System.out.println("    \"salary\": " + emp.getEmployeeSalary());
	        System.out.print("  }");
	        System.out.println(i < employees.size() - 1 ? "," : "");
	    }
	    System.out.println("]");
		}

}
