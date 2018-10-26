package com.guyhowcroft.kalaha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KalahaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KalahaApplication.class, args);
		test();
	}
	
	public static void test(){
		System.out.println("Started");
	}
}
