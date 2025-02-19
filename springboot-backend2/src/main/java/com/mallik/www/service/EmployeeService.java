package com.mallik.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mallik.www.exception.ResourceNotFoundException;
import com.mallik.www.model.Employee;
import com.mallik.www.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}

	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	public Employee getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found"));
	}

	public ResponseEntity<Employee> updateEmployee(Long id, Employee employee) {
		// TODO Auto-generated method stub
		Employee emp=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Does not exist"));
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		Employee updateEmp=employeeRepository.save(emp);
		return ResponseEntity.ok(updateEmp);
	}

	public ResponseEntity<HttpStatus> deleteEmployee(long id) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee Does not Exit"));
		employeeRepository.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
