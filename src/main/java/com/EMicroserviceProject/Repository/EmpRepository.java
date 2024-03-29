package com.EMicroserviceProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EMicroserviceProject.Model.Employee;

public interface EmpRepository extends JpaRepository<Employee,Integer>{

}
