package com.harris.popcorn.service;

import com.harris.popcorn.entity.Role;
import com.harris.popcorn.entity.User;
import com.harris.popcorn.repository.RoleRepository;
import com.harris.popcorn.repository.UserRepository;
import java.util.ArrayList;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveUser(User user) {
        //Encoding password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

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

    public boolean passwordCorrect(String givenPassword, String encodedPassword) {
        boolean correct;
        correct = passwordEncoder.matches(givenPassword, encodedPassword);
        return correct;
    }
}
