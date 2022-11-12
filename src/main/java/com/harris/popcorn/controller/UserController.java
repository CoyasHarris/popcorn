package com.harris.popcorn.controller;

import com.harris.popcorn.entity.Role;
import com.harris.popcorn.entity.User;
import com.harris.popcorn.service.RoleServiceImpl;
import com.harris.popcorn.service.UserServiceImpl;
import java.util.Arrays;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")

public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @GetMapping()
    public String showIntro() {
        return "intro";
    }

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/form")
    public String showForm(Model model, User user) {

//        for (int i = 0; i < userServiceImpl.listAll().size(); i++) {
//
//            if (userServiceImpl.listAll().get(i).getId().equals(id)) {
//                model.addAttribute("user", userServiceImpl.getUser(id));
//            } else {
        model.addAttribute("user", new User());

//            }
//        }
        return "form";
    }

    @PostMapping("/submit")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result, Model model,RedirectAttributes rm) {

        String mail = user.getEmail();

        User existingUser = userServiceImpl.findUserByEmail(mail);

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            rm.addFlashAttribute("exists", "There is already an account registered with the same email.Please Log In");
            return "redirect:/login";
        }
        model.addAttribute("registerSuccess", "Registered Successfully.Please Log In.");
        Role role = roleServiceImpl.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(role));
        userServiceImpl.saveUser(user);

        return "login";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userServiceImpl.listAll());
        return "usersList";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/updateName")
    public String updateUserName(String name, String password, Long id, Model model, RedirectAttributes rm) {
        model.getAttribute(name);
        model.getAttribute(password);
        User updatedUser = userServiceImpl.getUser(id);
        boolean correct = userServiceImpl.passwordCorrect(password, userServiceImpl.getUser(id).getPassword());
       if (correct){
        updatedUser.setName(name);
        updatedUser.setPassword(password);
        rm.addFlashAttribute("success", "Name Changed Successfully");
        userServiceImpl.saveUser(updatedUser);}
       else{
       rm.addFlashAttribute("wrong", "Invalid Password");
       }
       
        return "redirect:/movie/movies";
    }

}
