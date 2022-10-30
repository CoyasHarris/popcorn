
package com.harris.popcorn.repository;

import com.harris.popcorn.entity.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName (String name);
    
}
