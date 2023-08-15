package com.ig.dto;

import lombok.Data;

@Data
public class Student {

	private Integer rollNo;

	private String name;

	private String dept;

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", dept=" + dept + "]";
	}
	
	

}
