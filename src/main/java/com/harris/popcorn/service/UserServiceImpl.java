package com.harris.popcorn.service;

import com.harris.popcorn.entity.Role;
import com.harris.popcorn.entity.User;
import com.harris.popcorn.repository.RoleRepository;
import com.harris.popcorn.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User saveUser(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            //Adding User role by default
            Role role = roleRepository.findByName("ROLE_USER");
            if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
            
        return userRepository.save(user);
    }

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

}
