package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.model.Employee;
import com.sample.repository.EmployeeRepository;

@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/sa")
public class TestController {

	@Autowired
	EmployeeRepository employeeRepository;

	@ResponseBody
	@RequestMapping("/hello")
	public String name() throws Exception {
		/*
		Employee employee = new Employee();
		employee.setFirstName("KEY");
		employeeRepository.save(employee);
		*/
		return "hello";
	}
}
