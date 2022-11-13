/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harris.popcorn.security;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Exposing the directory containing the uploaded files so the clients (web browsers) can access.

@Configuration
public class MvcConfig implements WebMvcConfigurer{
    
    
    @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("movie-photos", registry);
    }
    
    private void exposeDirectory(String dirName,ResourceHandlerRegistry registry){
    Path uploadDir = Paths.get(dirName);
    String uploadPath =uploadDir.toFile().getAbsolutePath();
    if(dirName.startsWith("../")) dirName= dirName.replace("../", "");
    registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    
    }
}
