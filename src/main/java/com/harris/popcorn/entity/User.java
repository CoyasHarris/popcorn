

package com.harris.popcorn.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "users")
public class User {
    
//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;
    
     
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @NonNull
    @Column (name = "name" , nullable = false)
    private String name;
    
    @NonNull
    @Column (name = "address" , nullable = false)
    private String address;
    
    @NonNull
    @Column (name = "email" , nullable = false)
    private String email;
    
    @NonNull
    @Column (name = "password" , nullable = false)
    private String password;
    
     
    
    @JoinTable (
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
     @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     private List<Role> roles = new ArrayList<>();
    
    

    }
    
    
    

  

    
    
//    @OneToMany
//    private List <Movie>moviesToWatch;

    
