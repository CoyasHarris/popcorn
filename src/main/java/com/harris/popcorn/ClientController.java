/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harris.popcorn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")

public class ClientController {

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    
    
    @GetMapping
    public String showHome() {
        return "home";
    }

    @GetMapping("/form")
    public String showForm(Model model, @RequestParam(required = false) Long id, Client client) {

        for (int i = 0; i < clientServiceImpl.listAll().size(); i++) {

            if (clientServiceImpl.listAll().get(i).getId().equals(id)) {
                model.addAttribute("client", clientServiceImpl.getClient(id));
              } else {
                model.addAttribute("client", new Client());

            }
        }
          return "form";
    }


    @PostMapping("/submit")
    public String saveClient(Model model, Client client) {
        clientServiceImpl.saveClient(client);
        String message = "Succesfully submitted";
        model.addAttribute("message",message);
        return "redirect:/clients";

    }

    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", clientServiceImpl.listAll());
        return "clientsList";
    }
}
