package com.harris.popcorn.controller;

import com.harris.popcorn.entity.Movie;
import com.harris.popcorn.service.MovieServiceImpl;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieServiceImpl;

    @GetMapping
    public String showHome() {
        return "movieHome";
    }

    @GetMapping("/addmovieform")
    public String showForm(Model model, @RequestParam(required = false) Long id, Movie movie) {

        for (int i = 0; i < movieServiceImpl.listAll().size(); i++) {

            if (movieServiceImpl.listAll().get(i).getId().equals(id)) {
                model.addAttribute("movie", movieServiceImpl.getMovie(id));

            } else {
                model.addAttribute("movie", new Movie());
            }
        }
        return "addMovieForm";
    }

    @PostMapping("/movie/submit")
    public String saveMovie(Model model, Movie movie) {
        movieServiceImpl.saveMovie(movie);
//        String message = "Succesfully submitted";
//        model.addAttribute("message",message);
        return "redirect:/movie/movies";

    }

    @GetMapping("/movies")
    public String getMovies(HttpServletRequest request, Model model) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("movies", movieServiceImpl.listAll());
            return "moviesListAdmin";
        }
        model.addAttribute("movies", movieServiceImpl.listAll());
        return "moviesListUser";
    }

    @GetMapping("/delete")
    public String deleteMovie(@RequestParam Long id) {
        movieServiceImpl.deleteMovie(id);
        return "redirect:/movie/movies";
    }

}
