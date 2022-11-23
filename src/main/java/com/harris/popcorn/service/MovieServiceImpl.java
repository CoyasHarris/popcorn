

package com.harris.popcorn.service;

import com.harris.popcorn.entity.Movie;
import com.harris.popcorn.entity.User;
import com.harris.popcorn.repository.MovieRepository;
import com.harris.popcorn.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Movie getMovie(Long id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> listAll() {
        return (List<Movie>) movieRepository.findAll();   
    }

    @Override
    public List<Movie> listByGenre(String movie_genre) {
        return (List<Movie>) movieRepository.findByGenre(movie_genre);
    }
    
    @Override
    public void deleteMovie(Long id) {
            movieRepository.deleteById(id);   
    }

    @Override
    public Movie addToWatchlist(Long movie_id , Long user_id) {
        Movie movie = getMovie(movie_id);
        Optional<User> activeUser = userRepository.findById(user_id);    
        User unwrappedUser = UserServiceImpl.unwrapUser(activeUser, user_id);
        movie.getUsers().add(unwrappedUser);
        return movieRepository.save(movie);
    }

    @Override
    public Movie removeFromWatchlist(Long user_id, Long movie_id) {
        Movie movie = getMovie(movie_id);
        Optional<User> activeUser = userRepository.findById(user_id);    
        User unwrappedUser = UserServiceImpl.unwrapUser(activeUser, user_id);
        movie.getUsers().remove(unwrappedUser);
        return movieRepository.save(movie);
    }

    
    
       }
    
