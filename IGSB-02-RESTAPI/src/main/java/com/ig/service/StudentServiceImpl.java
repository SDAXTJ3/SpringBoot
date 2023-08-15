package com.ig.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ig.dao.StudentDAO;
import com.ig.dto.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO dao;
	
	@Autowired
	Environment env;

	@Override
	public boolean registerStudent(Student std) throws SQLException {
		int registerStudent = 0;
		System.out.println("application database url "+env.getProperty("app.url"));
		if (std != null) {
			registerStudent = dao.registerStudent(std);
		}
		if (registerStudent == 0)
			return false;
		else
			return true;
	}

	@Override
	public Student retrieveStudentById(Integer rollNo) throws SQLException {

		return dao.retrieveStudentById(rollNo);

	}

	@Override
	public List<Student> retriveAll() throws SQLException {

		return dao.retriveAll();
	}

	@Override
	public boolean deleteStudentById(Integer id) throws SQLException {

		return dao.deleteStudentById(id);
	}

	@Override
	public boolean deleteAll() throws SQLException {

		return dao.deleteAll();
	}

	@Override
	public Student updateStudentById(Student std) throws SQLException{

		return dao.updateStudentById(std);
		
	}

}
