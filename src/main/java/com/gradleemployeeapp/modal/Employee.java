package com.gradleemployeeapp.modal;
/**
 * @author mounikad
 *
 */
public class Employee {
    private int empId;
	private String firstName;
	private String lastName;
	private int managerId;

	public Employee() {

	}

	public Employee(int empId, String firstName, String lastName, int managerId) {
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerId = managerId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", managerId="
				+ managerId + "]";
	}

}
