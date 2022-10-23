package com.harris.popcorn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="movie")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Movie {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    
    @NonNull
    @Column (name = "title" , nullable = false)
    private String title;
    
    @NonNull
    @Column (name = "xronia" , nullable = false)
    private int xronia;
    
    @NonNull
    @Column (name = "duration" , nullable = false)
    private int duration;
    
    @NonNull
    @Column (name = "genre" , nullable = false)
    private String genre;
    
    @NonNull
    @Column (name = "director" , nullable = false)
    private String director;
    
    
    @NonNull
    @Column (name = "rating" , nullable = false)
    private double rating;
    
    @NonNull
    @Column (name = "summary" , nullable = false)
    private String summary;
    
    //TODO MAKE LIST OF actors
//    private List<Actor> actors;
    
    //TODO MAKE LIST OF RATINGS
//    private List<Rating> ratings;
    
   
    
    
    
    
}
