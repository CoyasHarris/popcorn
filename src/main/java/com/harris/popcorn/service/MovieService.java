/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harris.popcorn.service;

import com.harris.popcorn.entity.Movie;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public interface MovieService {
    
    public  Movie getMovie(Long id);
    public  Movie saveMovie(Movie movie);
    public List<Movie> listAll();
    void deleteMovie(Long id);
}
