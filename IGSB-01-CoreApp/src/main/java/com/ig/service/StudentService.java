package com.ig.service;

import java.sql.SQLException;
import java.util.List;

import com.ig.dto.Student;

public interface StudentService {
	
	boolean registerStudent(Student std) throws SQLException;

	 Student retrieveStudentById(Integer rollNo) throws SQLException;
	 
	 List<Student> retriveAll()  throws SQLException;
	 
	 boolean deleteStudentById(Integer id) throws SQLException;
	 
	 boolean deleteAll() throws SQLException;
	 
	 Student updateStudentById(Student std) throws SQLException;
}
