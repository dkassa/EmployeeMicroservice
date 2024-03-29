package com.EMicroserviceProject.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EMicroserviceProject.Model.Employee;
import com.EMicroserviceProject.ReturnObject.Returnobjects;
import com.EMicroserviceProject.Service.EmpService;
import com.EMicroserviceProject.Service.EmpServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



@RestController
@Controller
@RequestMapping("/api/v1")
public class EmployeeController {
	
	
	private static final String employee = "employee";

	@Autowired
	EmpService empService;
	
	@Autowired
	EmpServiceImpl empImplService;
	
	@PostMapping("/createEmployee")
	//@PreAuthorize("hasAnyAuthority("ROLE_ADMIN","ROLE_USER")") all roles can access
	//@PreAuthorize("hasAuthority("ROLE_ADMIN") only admin can access
	
	public ResponseEntity<Employee>createEmployee(@RequestBody Employee employee){
		
		return new ResponseEntity<>(empService.createEmployee(employee),HttpStatus.OK);
		
		
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee>getById(@PathVariable int id){
		
		return new ResponseEntity<>(empService.findById(id),HttpStatus.FOUND);
	}
	
	@GetMapping("/employee")
	
	public List<Employee> employeeList(){
		
		return empService.findAllEmployee();
		
	}
	
	@DeleteMapping("/employee/{id}")
	public String delteEmployee(@PathVariable int id){
		
		return empService.deleteEmployee(id);
	}
	
	@GetMapping("/Employee/{id}")
	@CircuitBreaker(name=employee,fallbackMethod="fallBack")
	public ResponseEntity<?>empDepInfoById(@PathVariable int id){
		
		return new ResponseEntity<>(empImplService.returnobject(id),HttpStatus.OK);
	}
	
	public ResponseEntity<?> fallBack(Exception e){
		
		return new ResponseEntity<>("try again later",HttpStatus.OK);
	}

}
