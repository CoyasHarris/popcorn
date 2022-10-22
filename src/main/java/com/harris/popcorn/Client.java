

package com.harris.popcorn;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="client")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
//TODO ΝΑ ΒΑΛΩ ΜΥΝΗΜΑ ΕΑΝ ΠΑΕΙ ΝΑ ΠΑΡΕΙ ΚΕΝΑ ΟΝΟΜΑ ΚΑΙ ΔΙΕΥΘΥΝΣΗ
public class Client {
    
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
    
//    @OneToMany
//    private List <Movie>moviesToWatch;
 
    
}