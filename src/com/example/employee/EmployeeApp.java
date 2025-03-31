package com.example.employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeApp {
	private static Scanner scanner=new Scanner(System.in);
	private static EmployeeService employeeService=new EmployeeService();
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		employeeService.createEmployeeTable();
		showOptions();
		}
	public static void showOptions() throws SQLException{
		
		while(true) {
			System.out.println("1) Add Employees");
			System.out.println("2) Update Employees");
			System.out.println("3) Delete Employees");
			System.out.println("4) List all Employees");
			System.out.println("5) Exit");
			System.out.print("Enter your choice : ");
			
			int option=scanner.nextInt();
			scanner.nextLine();
			switch(option) {
			case 1: addEmployee();
					break;
			case 2: updateEmployee();
					break;
			case 3: deleteEmployee();
					break;
			case 4: listAllEmployee();
					break;
			case 5: System.out.println("Exiting...");			
					return;
			default:
					System.out.println("Invalid option");
			}
		}
	}
	public static void addEmployee() throws SQLException{
		System.out.println("Adding new Employee");
		System.out.print("Enter employee name : ");
		String name=scanner.nextLine();
		
		System.out.print("Enter employee department : ");
		String department=scanner.nextLine();
		
		System.out.print("Enter employee salary : ");
		double salary=scanner.nextDouble();
		scanner.nextLine();
		
		employeeService.addEmployee(name, department, salary);
		System.out.println("Employee added successfully");
	}
	public static void updateEmployee() throws SQLException{
		listAllEmployee();
		System.out.println("Enter emplloyeeID to update : ");
		int id=scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter employee name to update : ");
		String name=scanner.nextLine();
		
		System.out.print("Enter employee department to update : ");
		String department=scanner.nextLine();
		
		System.out.print("Enter employee salary to update : ");
		double salary=scanner.nextDouble();
		scanner.nextLine();
		
		employeeService.updateEmployee(id, name, department, salary);
		System.out.println("Employee updated successfully");
	}
	
	public static void deleteEmployee() throws SQLException{
		listAllEmployee();
		System.out.print("Enter employeeID to update : ");
		int id=scanner.nextInt();
		scanner.nextLine();
		employeeService.deleteEmployee(id);
		System.out.println("Employee deleted successfully");
	}
	public static void listAllEmployee() throws SQLException{
		List<Employee> employee=employeeService.getAllEmployees();
		System.out.println("Employee List : ");
		for(Employee emp:employee) {
			System.out.printf("ID : %d, Name : %s, Department : %s, Salary : %.2f%n",
			emp.getEmployeeId(),
			emp.getEmployeeName(),
			emp.getEmployeeDepartment(),
			emp.getEmployeeSalary()
			);
		}
		
	}
}
