package com.ig.dao;

import java.sql.SQLException;
import java.util.List;

import com.ig.dto.Student;

public interface StudentDAO {
	
	 int registerStudent(Student std) throws SQLException;

	 Student retrieveStudentById(Integer rollNo) throws SQLException;
	 
	 List<Student> retriveAll() throws SQLException;
	 
	 boolean deleteStudentById(Integer id)  throws SQLException;
	 
	 boolean deleteAll() throws SQLException;
	 
	 Student updateStudentDeptById(Student std) throws SQLException;

	boolean insertBatchStudents(List<Student> students) throws SQLException;
}
