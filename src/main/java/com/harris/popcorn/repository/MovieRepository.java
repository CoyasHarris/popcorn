

package com.harris.popcorn.repository;

import com.harris.popcorn.entity.Movie;
import com.harris.popcorn.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{
    
    List<Movie> findByGenre(String genre);
}
