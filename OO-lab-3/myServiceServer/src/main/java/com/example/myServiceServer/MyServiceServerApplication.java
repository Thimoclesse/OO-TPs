package com.example.myServiceServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.myServiceServer"})
public class MyServiceServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyServiceServerApplication.class, args);
	}
}
