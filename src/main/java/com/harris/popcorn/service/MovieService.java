package com.harris.popcorn.service;

import com.harris.popcorn.entity.Movie;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    public Movie getMovie(Long id);

    public Movie saveMovie(Movie movie);

    public List<Movie> listAll();
    
    public List<Movie> listByGenre(String movie_genre);

    void deleteMovie(Long id);

    public Movie addToWatchlist(Long user_id, Long movie_id);
    
    public Movie removeFromWatchlist(Long user_id, Long movie_id);
    
}
