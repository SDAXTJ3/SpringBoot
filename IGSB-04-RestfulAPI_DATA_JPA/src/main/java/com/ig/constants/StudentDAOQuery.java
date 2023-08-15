package com.ig.constants;

public class StudentDAOQuery {
	
	public final static String INSERT_QUERY = "INSERT INTO Student(name,dept) VALUES (?,?)";
	
	public final static String RETRIEVE_QUERY = "SELECT * FROM STUDENT WHERE rollNo = ?";
	
	public final static String RETRIEVE_ALL_QUERY = "SELECT * FROM STUDENT";
	
	public final static String DELETE_QUERY = "DELETE FROM STUDENT WHERE rollNo = ?";
	
	public final static String DELETE_ALL_QUERY = "DELETE FROM STUDENT";
	
    public final static String UPDATE_DEPT_QUERY = "UPDATE STUDENT SET dept = ? WHERE rollNo = ?";

}
