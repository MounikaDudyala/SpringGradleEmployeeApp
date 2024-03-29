package com.gradleemployeeapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gradleemployeeapp.dao.AbstractDBConnection;
import com.gradleemployeeapp.dao.EmployeeDao;
import com.gradleemployeeapp.modal.Employee;

@Repository
public class EmployeeDaoImpl extends AbstractDBConnection implements EmployeeDao {
	public boolean createEmployee(Employee emp) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into employee(FirstName,LastName,ManagerId) values(?,?,?)");
			pstmt.setString(1, emp.getFirstName());
			pstmt.setString(2, emp.getLastName());
			pstmt.setInt(3, emp.getManagerId());
			int i = pstmt.executeUpdate();
			if (i == 1)
				return true;
			if (i == 0)
				return false;
		} catch (SQLException e) {
			System.out.println("SQLException caught: " + e.getMessage());
		} catch (Exception e) {

			System.out.println("message");
		}
		return false;
	}

	public Employee fetchEmployee(int empId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from employee where empId=?");
			pstmt.setInt(1, empId);
			ResultSet rs = pstmt.executeQuery();
			Employee emp = new Employee();
			while (rs.next()) {
				emp.setEmpId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setManagerId(rs.getInt(4));
			}

			return emp;
		} catch (SQLException e) {
			System.out.println("SQLException caught: " + e.getMessage());

		} catch (Exception e) {

			System.out.println("message");
		}
		return null;
	}

	public List<Employee> fetchEmployees() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			String query = "select * from employee";
			ResultSet rs = st.executeQuery(query);
			List<Employee> list = new ArrayList<Employee>();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setManagerId(rs.getInt(4));
				list.add(emp);
			}

			return list;
		} catch (SQLException e) {
			System.out.println("SQLException caught: " + e.getMessage());

		} catch (Exception e) {

			System.out.println("message");
		}
		return null;
	}

	public boolean deleteEmployee(int empId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from employee where empId=?");
			pstmt.setInt(1, empId);
			int i = pstmt.executeUpdate();
			if (i == 1)
				return true;
			if (i == 0)
				return false;
		} catch (SQLException e) {
			System.out.println("SQLException caught: " + e.getMessage());
		} catch (Exception e) {

			System.out.println("message");
		}
		return false;
	}

	public boolean updateEmployee(Employee emp) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update employee set FirstName=?,LastName=?,ManagerId=? where EmpId=?");
			pstmt.setString(1, emp.getFirstName());
			pstmt.setString(2, emp.getLastName());
			pstmt.setInt(3, emp.getManagerId());
			pstmt.setInt(4, emp.getEmpId());
			int i = pstmt.executeUpdate();
			if (i == 0) {
				return false;
			} else
				return true;

		} catch (SQLException e) {
			System.out.println("SQLException caught: " + e.getMessage());
		} catch (Exception e) {

			System.out.println("message");
		}
		return false;
	}
}
