package com.amvijay.cache.redis.spring.app1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.amvijay.cache.redis.spring.app1.entity.Employee;
import com.amvijay.cache.redis.spring.app1.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeDao;
	
	public void createEmployee(String employeeName) {
		Employee employee = new Employee();
		employee.setName(employeeName);
		employeeDao.save(employee);
	}

	@Cacheable(cacheNames  = "employee", key = "#id")
	public Optional<Employee> fetchEmployeeDetails(Long id) {
		return employeeDao.findById(id);
	}

	
}
