package com.ig.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ig.dto.Student;
import com.ig.dto.StudentResponse;
import com.ig.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService service;

	@PostMapping("/register")
	public ResponseEntity<StudentResponse> registerStudent(@RequestBody Student std) throws SQLException {
		boolean status = false;
		ResponseEntity<StudentResponse> response = null;

		List<Student> students = new ArrayList<>();
		StudentResponse dto = new StudentResponse();
		if (std.getName() == null) {
			dto.setMessage("Failed");
			dto.setStatusCode("111");
			students.add(std);
			dto.setStudents(students);

			response = new ResponseEntity<StudentResponse>(dto, HttpStatus.NOT_IMPLEMENTED); //
		} else {
			try {
				status = service.registerStudent(std);
			} catch (SQLException e) {
				e.printStackTrace(); // Exception Propagation
				throw e;

			}
			if (status) {
				dto.setMessage("Success");
				dto.setStatusCode("000");
				students.add(std);
				dto.setStudents(students);

				response = new ResponseEntity<StudentResponse>(dto, HttpStatus.OK); // 200 - OK

			} else {
				dto.setMessage("Failed");
				dto.setStatusCode("111");
				students.add(std);
				dto.setStudents(students);

				response = new ResponseEntity<StudentResponse>(dto, HttpStatus.NOT_IMPLEMENTED); // 200 - OK
			}
		}

		return response;
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<StudentResponse> getStudentById(@PathVariable("id") Integer id) {

		StudentResponse stdResponse = new StudentResponse();
		ResponseEntity<StudentResponse> response = null;
		List<Student> students = new ArrayList<>();
		Student std = new Student();
		if (id != null) {

			try {
				std = service.retrieveStudentById(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (std.getRollNo() != null) {

				stdResponse.setMessage("Success");
				stdResponse.setStatusCode("000");

				students.add(std);
				stdResponse.setStudents(students);

				response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.OK);
			}

			else {
				stdResponse.setMessage("Record Not Found!!");
				stdResponse.setStatusCode("404");

				students.add(std);
				stdResponse.setStudents(students);
				response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.NOT_FOUND);

			}

		} else {
			stdResponse.setMessage("Please enter student Roll Number ");
			stdResponse.setStatusCode("111");
			students.add(std);
			stdResponse.setStudents(students);
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping("/students")
	public ResponseEntity<StudentResponse> getAllStudents() {
		ResponseEntity<StudentResponse> response = null;

		List<Student> students = null;

		StudentResponse stdResponse = new StudentResponse();
		try {
			students = service.retriveAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (students != null && !students.isEmpty()) {
			stdResponse.setStudents(students);
			stdResponse.setStatusCode("000");
			stdResponse.setMessage("Success");
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.OK);
		}

		else {
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@DeleteMapping("/student/{id}")
	public ResponseEntity<StudentResponse> deleteStudentById(@PathVariable Integer id) {

		ResponseEntity<StudentResponse> response = null;

		StudentResponse stdResponse = new StudentResponse();
		boolean status = false;
		try {
			
			 service.deleteStudentById(id);
			 status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (status) {
			stdResponse.setMessage("Success");
			stdResponse.setStatusCode("000");
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.OK);
		} else {
			stdResponse.setMessage("Not found");
			stdResponse.setStatusCode("404");
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@DeleteMapping("/students")
	public ResponseEntity<StudentResponse> deleteAllStudents() {

		ResponseEntity<StudentResponse> response = null;

		StudentResponse stdResponse = new StudentResponse();
		boolean status = false;

		try {
		service.deleteAll();
		status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (status) {
			stdResponse.setMessage("Success");
			stdResponse.setStatusCode("000");
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.OK);
		} else {
			stdResponse.setMessage("Failed");
			stdResponse.setStatusCode("111");
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.NOT_IMPLEMENTED);

		}

		return response;

	}

	@PostMapping("/students")
	public ResponseEntity<StudentResponse> insertBulkStudentRecord(@RequestBody List<Student> students) {

		boolean status = false;
		ResponseEntity<StudentResponse> response = null;

		StudentResponse stdResponse = new StudentResponse();

		try {
			status = service.insertBatchStudents(students);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (status) {

			stdResponse.setMessage("Success");
			stdResponse.setStatusCode("000");
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.OK);
		} else {
			stdResponse.setMessage("Failed");
			stdResponse.setStatusCode("111");
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.NOT_IMPLEMENTED);
		}
		return response;

	}

	@PutMapping("/student")
	public ResponseEntity<StudentResponse> updateStudentDeptById(@RequestBody Student std) {

		ResponseEntity<StudentResponse> response = null;
		StudentResponse stdResponse = new StudentResponse();
		Student student = null;
		try {
			student = service.updateStudentDeptById(std);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (student != null) {

			List<Student> students = new ArrayList<>();
			students.add(student);
			stdResponse.setMessage("Success");
			stdResponse.setStatusCode("000");
			stdResponse.setStudents(students);
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.OK);
		} else {
			stdResponse.setMessage("Failed");
			stdResponse.setStatusCode("111");
			response = new ResponseEntity<StudentResponse>(stdResponse, HttpStatus.NOT_IMPLEMENTED);
		}

		return response;

	}

}
