package com.harris.popcorn.controller;

import com.harris.popcorn.entity.Movie;
import com.harris.popcorn.entity.User;
import com.harris.popcorn.service.MovieServiceImpl;
import com.harris.popcorn.service.UserServiceImpl;
import com.harris.util.FileUploadUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

//    @GetMapping
//    public String showHome() {
//        return "movieHome";
//    }
    @GetMapping("/addmovieform")
    public String showForm(Model model, Movie movie) {
        model.addAttribute("movie", new Movie());
        System.out.println("ena ena ena TO MOVIE ID EINAI TO " + movie.getId());

        return "addmovieform";
    }

    @PostMapping("/movie/submit")
    public String saveMovie(@RequestParam("image") MultipartFile multipartFile, Model model, Movie movie, RedirectAttributes rm) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        movie.setPhotos(fileName);
        movieServiceImpl.saveMovie(movie);
        String uploadDir = "movie-photos/" + movie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        rm.addFlashAttribute("movieAdded", "Movie successfully submitted");
        return "redirect:/movie/movies";

    }

    @GetMapping("/movieDetails")
    public String getMovie(@RequestParam(required = true) Long id, Model model) {
        Movie movie = movieServiceImpl.getMovie(id);
        model.addAttribute("movie", movie);
        return "movie";
    }

    @GetMapping("/movies")
    public String getMovies(HttpServletRequest request, Model model, User user) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("movies", movieServiceImpl.listAll());
            return "moviesListAdmin";
        }
        String activeUserMail = request.getUserPrincipal().getName();
        User activeUser = userServiceImpl.findUserByEmail(activeUserMail);
        model.addAttribute("activeUser", activeUser);
        model.addAttribute("movies", movieServiceImpl.listAll());

        return "moviesListUser";
    }

    @GetMapping("/delete")
    public String deleteMovie(@RequestParam Long id, RedirectAttributes rm) {
        movieServiceImpl.deleteMovie(id);
        rm.addFlashAttribute("movieDeleted", "Movie successfully Deleted");
        return "redirect:/movie/movies";
    }

    @GetMapping("/updateMovie")
    public String updateMovie(@RequestParam(required = true) Long id, Movie movie, Model model) {
        for (int i = 0; i < movieServiceImpl.listAll().size(); i++) {
            movieServiceImpl.listAll().get(i).getId().equals(id);
            model.addAttribute("movie", movieServiceImpl.getMovie(id));

        }
        return "updatemovieform";
    }

    @PostMapping("/update")
    public String updateMovieFields(@RequestParam(required = true) Long id, Movie movie, RedirectAttributes rm) {

        Movie updatedMovie = movieServiceImpl.getMovie(id);

        updatedMovie.setTitle(movie.getTitle());
        updatedMovie.setYear(movie.getYear());
        updatedMovie.setDuration(movie.getDuration());
        updatedMovie.setGenre(movie.getGenre());
        updatedMovie.setDirector(movie.getDirector());
        updatedMovie.setRating(movie.getRating());
        updatedMovie.setSummary(movie.getSummary());
        movieServiceImpl.saveMovie(updatedMovie);
        rm.addFlashAttribute("movieUpdated", "Movie successfully updated");
        return "redirect:/movie/movies";
    }

    @RequestMapping("/addtowatchlist")
    public String addToWatchList(@RequestParam(required = true) Long movie_id, @RequestParam(required = true) Long user_id, RedirectAttributes rm) {

        for (int i = 0; i < movieServiceImpl.getMovie(movie_id).getUsers().size(); i++) {
            if (movieServiceImpl.getMovie(movie_id).getUsers().get(i).getId().equals(user_id)) {
            }
            rm.addFlashAttribute("allreadyAdded", "Movie is Allready in your Watchlist");
            return "redirect:/movie/movies";
        }
        movieServiceImpl.addToWatchlist(movie_id, user_id);
        rm.addFlashAttribute("addedToWatchlist", "Movie has been Added to your Watchlist");
        return "redirect:/movie/movies";
    }

    @GetMapping("/getByGenre")
    public String getMoviesByGenre(Model model, @RequestParam(required = false) String movie_genre) {

        movieServiceImpl.listByGenre(movie_genre);
        model.addAttribute("moviesByGenre", movieServiceImpl.listByGenre(movie_genre));
        return "moviesByGenre";
    }
}
