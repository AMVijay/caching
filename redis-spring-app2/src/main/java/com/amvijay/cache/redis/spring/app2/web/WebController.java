package com.amvijay.cache.redis.spring.app2.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amvijay.cache.redis.spring.app2.entity.Employee;
import com.amvijay.cache.redis.spring.app2.service.EmployeeService;


@RestController
public class WebController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	@RequestMapping(path = "/createEmployee")
	public void createEmployee(@RequestParam(name = "name") String employeeName) {
		employeeService.createEmployee(employeeName);
	}
	
	@RequestMapping(path = "/fetchEmployee")
	public Employee fetchEmployeeDetails(@RequestParam(name = "id") Long id) {
		return employeeService.fetchEmployeeDetails(id).get();
	}
	
	@RequestMapping(path = "/listkeys")
	public void listRedisKeys() {
		System.out.println("We are in listRedisKeys method");
		Set<String> keys = redisTemplate.keys("*");
		keys.forEach(element -> {
			System.out.println("Key Value :: " +  element);
		}); 
		 
	}
}
