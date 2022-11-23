

package com.harris.popcorn.repository;

import com.harris.popcorn.entity.Movie;
import com.harris.popcorn.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{
    
    List<Movie> findByGenre(String genre);
    
 
    List<Movie> findMoviesByUsersId(Long users_id);
    
    
    
    @Query(
     value = "SELECT count(movie_id) FROM popcorn.movie_users WHERE users_id=?1", 
     nativeQuery = true)
    Integer  numberOfMoviesAdded(Long users_id);
}
