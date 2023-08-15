package com.ig.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.ig.constants.StudentDAOQuery;
import com.ig.dto.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	DataSource datasource;

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	@Override
	public int registerStudent(Student std) throws SQLException {

		Connection connection = getConnection();
		String name = null;
		String dept = null;
		if (!ObjectUtils.isEmpty(std)) {

			name = std.getName();
			dept = std.getDept();
		}
		PreparedStatement st = connection.prepareStatement(StudentDAOQuery.INSERT_QUERY);
		st.setString(1, name);
		st.setString(2, dept);

		return st.executeUpdate();
	}

	@Override
	public Student retrieveStudentById(Integer rollNo) throws SQLException {

		Student std = new Student();

		if (rollNo != null) {

			Connection con = getConnection();

			PreparedStatement pst = con.prepareStatement(StudentDAOQuery.RETRIEVE_QUERY);
			pst.setInt(1, rollNo);

			ResultSet resultSet = pst.executeQuery(); // executeQuery - we can use for retrieve operation

			while (resultSet.next()) {

				std.setRollNo(resultSet.getInt("rollNo"));
				std.setName(resultSet.getString("name"));
				std.setDept(resultSet.getString("dept"));

			}

		}

		return std;
	}

	@Override
	public List<Student> retriveAll() throws SQLException {

		Connection con = getConnection();
		List<Student> students = new ArrayList<>();

		PreparedStatement pst = con.prepareStatement(StudentDAOQuery.RETRIEVE_ALL_QUERY);

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Student std = new Student();

			std.setRollNo(rs.getInt(1));
			std.setName(rs.getString(2));
			std.setDept(rs.getString(3));
			students.add(std);
		}
		return students;
	}

	@Override
	public boolean deleteStudentById(Integer id) throws SQLException {

		Connection con = getConnection();

		PreparedStatement pst = con.prepareStatement(StudentDAOQuery.DELETE_QUERY);

		pst.setInt(1, id);

		int executeUpdate = pst.executeUpdate();

		if (executeUpdate != 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteAll() throws SQLException {

		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement(StudentDAOQuery.DELETE_ALL_QUERY);
		int status = pst.executeUpdate();

		return (status > 0 ? true : false);
	}

	@Override
	public Student updateStudentDeptById(Student std)throws SQLException {
		
		Student std1 = null;
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement(StudentDAOQuery.UPDATE_DEPT_QUERY);
		pst.setString(1, std.getDept());
		pst.setInt(2, std.getRollNo());
		
		int result = pst.executeUpdate();
		if(result>0) {
			 std1 = retrieveStudentById(std.getRollNo());
		}
		
		return std1;
	}

	@Override
	public boolean insertBatchStudents(List<Student> students) throws SQLException {
	
		boolean flag = false;
		
		Connection con = getConnection();
		
		int status = 0;
		
		for(Student std: students) {
			
			status = jdbcTemplate.update(StudentDAOQuery.INSERT_QUERY,std.getName(),std.getDept());
		}
	
		return status>0?true:false;
	}

}
