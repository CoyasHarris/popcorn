/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harris.popcorn.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultController {
    
    @RequestMapping("/default")
    
    
    @GetMapping
    public String defaultAfterLogin (HttpServletRequest request ){
    if(request.isUserInRole("ROLE_ADMIN")){
    return"redirect:/home";
    }
    return"redirect:/movie/movies";
    }
    
    
}
