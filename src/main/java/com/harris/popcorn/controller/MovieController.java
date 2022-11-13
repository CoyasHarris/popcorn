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
        return "addmovieform";
    }

    @PostMapping("/movie/submit")
    public String saveMovie( @RequestParam("image") MultipartFile multipartFile ,Model model, Movie movie ,RedirectAttributes rm) throws IOException {
        
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        movie.setPhotos(fileName);
        movieServiceImpl.saveMovie(movie);
        rm.addAttribute("movieAdded","Succesfully submitted");
        String uploadDir= "movie-photos/" + movie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName,multipartFile);
        return "redirect:/movie/movies";

    }

    @GetMapping("/movies")
    public String getMovies(HttpServletRequest request, Model model ,@RequestParam(required = false) Long id, User user) {
        if (request.isUserInRole("ROLE_ADMIN")) {
         model.addAttribute("movies", movieServiceImpl.listAll());
            return "moviesListAdmin";
        }
        String activeUserMail = request.getUserPrincipal().getName();
        User activeUser= userServiceImpl.findUserByEmail(activeUserMail);
        model.addAttribute("activeUser", activeUser);
        model.addAttribute("movies", movieServiceImpl.listAll());
       
        return "moviesListUser";
    }

    @GetMapping("/delete")
    public String deleteMovie(@RequestParam Long id) {
        movieServiceImpl.deleteMovie(id);
        return "redirect:/movie/movies";
    }
    
    

}
