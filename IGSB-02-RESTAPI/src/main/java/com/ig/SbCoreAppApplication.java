package com.ig;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ig.dto.Student;
import com.ig.service.StudentService;

@SpringBootApplication()
public class SbCoreAppApplication {

	
	@Autowired
	Environment env;
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SbCoreAppApplication.class, args);
		//bootstrap SB Application
		
		Student std1 = new Student();
		
		std1.setDept("Mechanical");
		std1.setName("Shreya");
		boolean status = false;
		
		StudentService service = ctx.getBean(StudentService.class);
		
		try {
			status	 = service.registerStudent(std1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Is student Registered ?? "+status);
		
	}
	
	@Bean("datasource")// to mark predefined drivermanagerdatasource as Spring bean
	public DriverManagerDataSource datasource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		//dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));	
		
		System.out.println("Our own manual configuration ----------------$$$$$$$$$$$$$$$$$$$$");
		return dataSource;
		
	}

}
