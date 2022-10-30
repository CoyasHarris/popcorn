

package com.harris.popcorn.service;

import com.harris.popcorn.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;




@Service
public interface UserService {
    
    public  User getUser(Long id);
    public User findUserByEmail(String email);
    public  User saveUser(User user);
    public List<User> listAll();
    void deleteUser(Long id);
    
    
}
