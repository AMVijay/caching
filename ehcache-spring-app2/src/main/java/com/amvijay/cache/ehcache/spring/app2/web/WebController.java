package com.amvijay.cache.ehcache.spring.app2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amvijay.cache.ehcache.spring.app2.entity.Employee;
import com.amvijay.cache.ehcache.spring.app2.service.EmployeeService;


@RestController
public class WebController {
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(path = "/createEmployee")
	public void createEmployee(@RequestParam(name = "name") String employeeName) {
		employeeService.createEmployee(employeeName);
	}
	
	@RequestMapping(path = "/fetchEmployee")
	public Employee fetchEmployeeDetails(@RequestParam(name = "id") Long id) {
		return employeeService.fetchEmployeeDetails(id).get();
	}
}
