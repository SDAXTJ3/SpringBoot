package com.ig.dao;

import org.springframework.data.repository.CrudRepository;

import com.ig.dto.Student;

public interface StudentRepository  extends CrudRepository<Student, Integer>{

}
