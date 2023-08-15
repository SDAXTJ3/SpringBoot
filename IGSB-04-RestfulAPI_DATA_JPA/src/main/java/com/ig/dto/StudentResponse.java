package com.ig.dto;

import java.util.List;

import lombok.Data;

@Data
public class StudentResponse {

List<Student> students;

String statusCode;

String message;
	
}
