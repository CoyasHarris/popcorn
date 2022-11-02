



package com.harris.popcorn.service;

import com.harris.popcorn.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
     public  Role setRoleName(String roleName);
     public  Role findByName(String roleName);
}
