package com.example.employee;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService {
	
	private final EmployeeDAO employeeDAO;
	
	public EmployeeService() {
		this.employeeDAO=new EmployeeDAO();
	}
	
	public void createEmployeeTableServ() throws SQLException{
		employeeDAO.createEmployeeTableDB();
	}
	
	public int addEmployeeServ(String name,String department,double salary) throws SQLException{
		Employee employee=new Employee(name,department,salary);
		int employeeId=employeeDAO.addEmployeeDB(employee);
		return employeeId;
	}
	public void updateEmployeeServ(int id,String name,String department,String salary) throws SQLException{
		Employee existingEmployee=EmployeeDAO.getEmployeeByIDDB(id);
		
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
		if(!salary.isEmpty()) {
			try {
				double salaryDouble=Double.parseDouble(salary);
				existingEmployee.setEmployeeSalary(salaryDouble);
			}catch(Exception e) {
				throw new NumberFormatException("Invaild salary data");
			}
		}
		employeeDAO.updateEmployeeDB(existingEmployee);
	}
	public void deleteEmployeeServ(int id) throws SQLException{
		employeeDAO.deleteEmployeeDB(id);
	}
	public List<Employee> getAllEmployeesServ()throws SQLException{
		return employeeDAO.getAllEmployeesDB();
	}
	public Employee getEmployeeByIDServ(int id)throws SQLException{
		return employeeDAO.getEmployeeByIDDB(id);
	}
	public Employee getEmployeewithHighestSalaryServ() throws SQLException{
		List<Employee> employee=employeeDAO.getAllEmployeesDB();
		Optional<Employee> highestSalaryOptional=employee.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getEmployeeSalary)));
		Employee highestSalary=highestSalaryOptional.get();
		return highestSalary;
	}
	public void getEmployeeNameAndDepartmentServ() throws SQLException{
		List<Employee> employee=employeeDAO.getAllEmployeesDB();
		Map<String, List<Employee>> employeeByDepartment=employee.stream().collect(Collectors.groupingBy(Employee::getEmployeeDepartment));
		Set<Entry<String,List<Employee>>> entrySet=employeeByDepartment.entrySet();
		for(Entry<String,List<Employee>> entry:entrySet) {
			System.out.println(entry.getKey()+": ");
			
			List<Employee> employeeList=entry.getValue();
			for(Employee emp:employeeList) {
				System.out.println(emp.getEmployeeName());
			}
			System.out.println("---------------------------------------");
		}
		
	}
}
