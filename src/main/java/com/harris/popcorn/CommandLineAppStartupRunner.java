/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harris.popcorn;

import com.harris.popcorn.entity.Role;
import com.harris.popcorn.entity.User;
import com.harris.popcorn.repository.RoleRepository;
import com.harris.popcorn.service.RoleServiceImpl;
import com.harris.popcorn.service.UserServiceImpl;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private RoleServiceImpl roleServiceImpl;
  

    @Override
    public void run(String... args) throws Exception {

        //Instantiating and Saving to DB An Admin
//        Role role = roleServiceImpl.setRoleName("ADMIN");
//        User admin = new User();
//        admin.setAddress("adminaddress");
//        admin.setEmail("admin@admin.com");
//        admin.setName("Harris");
//        admin.setRoles(Arrays.asList(role));
//        admin.setPassword("1234");
//        userServiceImpl.saveUser(admin);
        
        //Instantiating USER ROLE
//        roleServiceImpl.setRoleName("USER");

    }
}
