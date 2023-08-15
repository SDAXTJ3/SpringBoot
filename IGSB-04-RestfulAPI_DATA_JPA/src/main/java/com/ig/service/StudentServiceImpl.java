package com.ig.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ig.dao.StudentDAO;
import com.ig.dao.StudentRepository;
import com.ig.dto.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository repository;

	@Autowired
	Environment env;

	@Override
	public boolean registerStudent(Student std) throws SQLException {

		Student student = repository.save(std);

		System.out.println("Saved Object -----" + student);

		return student != null ? true : false;
	}

	@Override
	public Student retrieveStudentById(Integer rollNo) throws SQLException {

		Optional<Student> response = repository.findById(rollNo);
		if(response.isPresent())

		return response.get();
		else
			return new Student();

	}

	@Override
	public List<Student> retriveAll() throws SQLException {

		List<Student> students = new ArrayList<>();
		Iterable<Student> response = repository.findAll();
		Iterator<Student> itr = response.iterator();

		while (itr.hasNext()) {
			students.add(itr.next());
		}
		return students;
	}

	@Override
	public void deleteStudentById(Integer id) throws SQLException {

		repository.deleteById(id);
	}

	@Override
	public void deleteAll() throws SQLException {

		repository.deleteAll();

	}

	@Override
	public Student updateStudentDeptById(Student std) throws SQLException {
		Student student = null;
		if(std!=null) {
			
			Integer rollNo = std.getRollNo();
			
			Optional<Student> response = repository.findById(rollNo);
			
			if(!response.isEmpty()) {
				student = repository.save(std);
			}
			
		}

		return student;

	}

	@Override
	public boolean insertBatchStudents(List<Student> students) throws SQLException {
		
		
		Iterable<Student> response = repository.saveAll(students);
		
		

		return (response!=null)?true:false;

	}

}
