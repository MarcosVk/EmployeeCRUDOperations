package com.example.employee;

public class Employee {
	
	private int employyeId;
	private String employeeName;
	private String employeeDepartment;
	private double employeeSalary;
	
	public Employee(String employeeName,String employeeDepartment,double employeeSalary) {
		this.employeeName=employeeName;
		this.employeeDepartment=employeeDepartment;
		this.employeeSalary=employeeSalary;
	}
	
	public Employee(int employyeId,String employeeName,String employeeDepartment,double employeeSalary) {
		this.employyeId=employyeId;
		this.employeeName=employeeName;
		this.employeeDepartment=employeeDepartment;
		this.employeeSalary=employeeSalary;
	}
	
	public int getEmployeeId() {
		return employyeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public String getEmployeeDepartment() {
		return employeeDepartment;
	}
	public double getEmployeeSalary() {
		return employeeSalary;
	}
	
	public void setEmployeeId(int employyeId) {
		this.employyeId=employyeId;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName=employeeName;
	}
	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment=employeeDepartment;
	}
	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary=employeeSalary;
	}
}
