package com.example.employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeApp {
	private static Scanner scanner=new Scanner(System.in);
	private static EmployeeService employeeService=new EmployeeService();
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		employeeService.createEmployeeTableServ();
		showOptions();
		}
	public static void showOptions() throws SQLException{
		
		while(true) {
			System.out.println("1) Add Employees");
			System.out.println("2) Update Employees");
			System.out.println("3) Delete Employees");
			System.out.println("4) List all Employees");
			System.out.println("5) List EmployeeById");
			System.out.println("6) Employee with High Salary");
			System.out.println("7) Employee with name and department");
			System.out.println("8) Exit");
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
			case 5: getEmployeeById();
					break;
			case 6: getEmployeewithHighestSalary();
					break;
			case 7: getEmployeeNameAndDepartment();
					break;
			case 8: System.out.println("Exiting...");			
					return;
			default:
					System.out.println("Invalid option");
			}
		}
	}
	public static void addEmployee() throws SQLException{
		System.out.println("Adding new Employee");
		System.out.print("Enter employee name: ");
		String name=scanner.nextLine();
		
		System.out.print("Enter employee department: ");
		String department=scanner.nextLine();
		
		System.out.print("Enter employee salary: ");
		double salary=scanner.nextDouble();
		scanner.nextLine();
		
		int employeeId=employeeService.addEmployeeServ(name, department, salary);
		System.out.println("Employee with ID: "+employeeId+" added successfully");
	}
	public static void updateEmployee() throws SQLException{
		listAllEmployee();
		System.out.println("Enter emplloyeeID to update: ");
		int id=scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter employee name to update: ");
		String name=scanner.nextLine();
		
		System.out.print("Enter employee department to update: ");
		String department=scanner.nextLine();
		
		System.out.print("Enter employee salary to update: ");
		String salary=scanner.nextLine();
		
		employeeService.updateEmployeeServ(id, name, department, salary);
		System.out.println("Employee with: "+id+" updated successfully");
	}
	
	public static void deleteEmployee() throws SQLException{
		listAllEmployee();
		System.out.print("Enter employeeID to update: ");
		int id=scanner.nextInt();
		scanner.nextLine();
		employeeService.deleteEmployeeServ(id);
		System.out.println("Employee with: "+id+" deleted successfully");
	}
	public static void listAllEmployee() throws SQLException{
		List<Employee> employee=employeeService.getAllEmployeesServ();
		System.out.println("Employee List: ");
		for(Employee emp:employee) {
			System.out.printf("ID : %d, Name : %s, Department : %s, Salary : %.2f%n",
			emp.getEmployeeId(),
			emp.getEmployeeName(),
			emp.getEmployeeDepartment(),
			emp.getEmployeeSalary()
			);
		}
	}
	public static void getEmployeeById() throws SQLException{
		System.out.println("Enter employeeID to display: ");
		int id=scanner.nextInt();
		scanner.nextLine();
		try {
			Employee employee=employeeService.getEmployeeByIDServ(id);
			System.out.printf("ID : %d, Name : %s, Department : %s, Salary : %.2f%n",
			employee.getEmployeeId(),
			employee.getEmployeeName(),
			employee.getEmployeeDepartment(),
			employee.getEmployeeSalary()
				);
		}catch(Exception e) {
			throw new NullPointerException("Entered employee doesn't exist");
		}
	}
	public static void getEmployeewithHighestSalary() throws SQLException{
		System.out.println("Employee with highest salary");
		Employee highSalaryEmployee=employeeService.getEmployeewithHighestSalaryServ();
		System.out.println("ID: "+highSalaryEmployee.getEmployeeId());
		System.out.println("Name: "+highSalaryEmployee.getEmployeeName());
		System.out.println("Department: "+highSalaryEmployee.getEmployeeDepartment());
		System.out.println("Salary: "+highSalaryEmployee.getEmployeeSalary());
	}
	public static void getEmployeeNameAndDepartment() throws SQLException{
		System.out.println("Employees name and department");
		employeeService.getEmployeeNameAndDepartmentServ();
	}
}
