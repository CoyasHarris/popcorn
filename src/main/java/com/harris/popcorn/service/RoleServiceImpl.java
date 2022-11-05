package com.harris.popcorn.service;

import com.harris.popcorn.entity.Role;
import com.harris.popcorn.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role setRoleName(String roleName) {
        Role role = new Role();
        role.setName("ROLE_" + roleName);
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
