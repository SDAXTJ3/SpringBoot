package com.ig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.ig.config.ServeletConfig;

@SpringBootApplication
@Import(ServeletConfig.class)
public class Igsb03RestfulAPiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Igsb03RestfulAPiApplication.class, args);
	}

}
