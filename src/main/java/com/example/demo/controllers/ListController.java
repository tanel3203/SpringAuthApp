package com.example.demo.controllers;

import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {

//    @Autowired
//    private ClientService clientService;
//
//    @GetMapping("/list")
//    public String index(Model model) {
//
//        model.addAttribute("clients", clientService.getAllClients());
//
//        return "list"; // list.html
//    }
}
