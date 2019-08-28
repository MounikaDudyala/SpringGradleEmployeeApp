package com.gradleemployeeapp.dao;

import java.util.List;

import com.gradleemployeeapp.modal.Employee;

public interface EmployeeDao {
	boolean createEmployee(Employee emp);
	Employee fetchEmployee(int empId);
	List<Employee> fetchEmployees();
	boolean deleteEmployee(int empId);
	boolean updateEmployee(Employee emp);
}
