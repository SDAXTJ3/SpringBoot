package com.ig.service;

import java.sql.SQLException;
import java.util.List;

import com.ig.dto.Student;

public interface StudentService {
	
	boolean registerStudent(Student std) throws SQLException;  //POST

	 Student retrieveStudentById(Integer rollNo) throws SQLException; //GET
	 
	 List<Student> retriveAll()  throws SQLException; // GET
	 
	 boolean deleteStudentById(Integer id) throws SQLException; //DELETE
	 
	 boolean deleteAll() throws SQLException; //DELETE
	 
	 Student updateStudentById(Student std) throws SQLException; //PUT
}
