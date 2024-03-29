package com.EMicroserviceProject.Service;

import java.util.List;

import com.EMicroserviceProject.Model.Employee;


public interface EmpService {
	
	Employee createEmployee(Employee employee);
	Employee findById(int id);
	List<Employee> findAllEmployee();
	String deleteEmployee(int id);
	

}
