package com.harris.popcorn.controller;

import com.harris.popcorn.entity.Role;
import com.harris.popcorn.entity.User;
import com.harris.popcorn.repository.RoleRepository;
import com.harris.popcorn.repository.UserRepository;
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
    public String showForm(Model model, @RequestParam(required = false) Long id, User user) {

        for (int i = 0; i < userServiceImpl.listAll().size(); i++) {

            if (userServiceImpl.listAll().get(i).getId().equals(id)) {
                model.addAttribute("user", userServiceImpl.getUser(id));
            } else {
                model.addAttribute("user", new User());

            }
        }
        return "form";
    }

    @PostMapping("/submit")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {

        String mail = user.getEmail();

        User existingUser = userServiceImpl.findUserByEmail(mail);

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
//              model.addAttribute("accountExists", "There is already an account registered with this email.Please Log In.");
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            return "redirect:/login";
        }
        model.addAttribute("registerSuccess", "Registered Successfully.Please Log In.");
        Role role = roleServiceImpl.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(role));
        userServiceImpl.saveUser(user);

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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
