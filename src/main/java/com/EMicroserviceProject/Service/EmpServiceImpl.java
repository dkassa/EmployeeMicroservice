package com.EMicroserviceProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.EMicroserviceProject.Dto.Departement;
import com.EMicroserviceProject.Model.Employee;
import com.EMicroserviceProject.Repository.EmpRepository;
import com.EMicroserviceProject.ReturnObject.Returnobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	EmpRepository empRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepository.save(employee);
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		
		Optional<Employee> optionalEmployee=empRepository.findById(id);
		
		if(optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		}
		
		return null;
		
		
		
	}

	@Override
	public List<Employee> findAllEmployee() {
		// TODO Auto-generated method stub
		return empRepository.findAll();
	}

	@Override
	public String deleteEmployee(int id) {
		// TODO Auto-generated method stub
		Optional<Employee> deltedEmployee=empRepository.findById(id);
		
		if(deltedEmployee.isPresent()) {
			
			 empRepository.deleteById(id);
		}
		
		return "delted";
		
		
		}
	
	
	@Autowired
	RestTemplate restTemplate;
	
	public Returnobjects returnobject(int id) {
		
		Optional<Employee> emp=empRepository.findById(id);
		
		Departement departement=restTemplate.getForObject("http://localhost:8086/api/v1/departement/" + emp.get().getDepId() , Departement.class);
		
		Returnobjects returnobj=new Returnobjects();
		
		returnobj.setEmployee(emp.get());
		returnobj.setDepartement(departement);
		
		return returnobj;
		
		
		
		
	}

	
	

}
