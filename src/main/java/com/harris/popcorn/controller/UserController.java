package com.harris.popcorn.controller;

import com.harris.popcorn.entity.User;
import com.harris.popcorn.repository.UserRepository;
import com.harris.popcorn.service.UserServiceImpl;
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
    private UserRepository userRepository;

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
        System.out.println("TO MAIL EINAIIII" + mail);

        User existingUser = userServiceImpl.findUserByEmail(mail);

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            return "redirect:/login";
        }
        userServiceImpl.saveUser(user);

        return "redirect:/users";

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