package com.ig.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ig.dto.Student;
import com.ig.service.StudentService;

@RestController()
public class StudentController {

	@Autowired
	StudentService service;
	
	
	@PostMapping("/register")  //service endpoint
	public String registerStudent(@RequestBody Student std) {
		boolean status = false;
		String message = "";
		 String statusCode = ""; /// 000 - successful, 111- failed

		 String stdName = "";
		
		if(std!=null) {
			try {
				status = service.registerStudent(std);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		if(status) {
			
		}
		
		
		
		return null;
	}
	
	

}
