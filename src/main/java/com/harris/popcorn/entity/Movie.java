package com.harris.popcorn.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="movie")
public class Movie {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column (name = "title" , nullable = false)
    private String title;
    
    @NonNull
    @Column (name = "year" , nullable = false)
    private Integer year;
    
    @NonNull
    @Column (name = "duration" , nullable = false)
    private Integer duration;
    
    @NonNull
    @Column (name = "genre" , nullable = false)
    private String genre;
    
    @NonNull
    @Column (name = "director" , nullable = false)
    private String director;
    
    
    @NonNull
    @Column (name = "rating" , nullable = false)
    private Double rating;
    
    @NonNull
    @Column (name = "summary" , nullable = false)
    private String summary;
    
    @Column(length = 64 ,nullable = true )
    private String photos;
    
    //WATCHLIST FUNCTIONALITY
    @ManyToMany (mappedBy = "movie")
    private List<User> users = new ArrayList<>();

    
    @Transient
    public String getPhotosImagePath(){
    if (photos == null || id == null)
        return null;
    return "/movie-photos/" + id + "/" + photos;
    }
    
    
    
    
    }
    
    
    //TODO MAKE LIST OF actors
//    private List<Actor> actors;
    
    //TODO MAKE LIST OF RATINGS
//    private List<Rating> ratings;
    
   
    
    
    
    

