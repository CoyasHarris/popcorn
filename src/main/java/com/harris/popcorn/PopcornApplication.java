package com.harris.popcorn;



import com.harris.popcorn.entity.Role;
import com.harris.popcorn.entity.User;
import com.harris.popcorn.repository.RoleRepository;
import com.harris.popcorn.repository.UserRepository;
import com.harris.popcorn.service.UserServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class PopcornApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopcornApplication.class, args);
                
            
            
	}

}
