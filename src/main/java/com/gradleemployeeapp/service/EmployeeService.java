package com.gradleemployeeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradleemployeeapp.dao.EmployeeDao;
import com.gradleemployeeapp.modal.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao employeeDao;

	public List<Employee> fetchEmployees() {
		List<Employee> employeeList = employeeDao.fetchEmployees();
		return employeeList;
	}

	public boolean createEmployee(Employee emp) {
		boolean is_employee_created = employeeDao.createEmployee(emp);
		return is_employee_created;
	}

	public Employee fetchEmployee(int empId) {
		Employee emp = employeeDao.fetchEmployee(empId);
		return emp;
	}

	public boolean updateEmployee(Employee emp) {
		boolean is_employee_updated = employeeDao.updateEmployee(emp);
		return is_employee_updated;
	}

	public boolean deleteEmployee(int empId) {
		boolean is_employee_deleted = employeeDao.deleteEmployee(empId);
		return is_employee_deleted;
	}
}
