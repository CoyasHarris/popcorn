package com.harris.popcorn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication 
public class PopcornApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopcornApplication.class, args);
	}

}
