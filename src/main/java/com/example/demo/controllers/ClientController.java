package com.example.demo.controllers;

import com.example.demo.entities.Client;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/client")
    public String getClient(Client client, Model model) {
        model.addAttribute("countries", clientService.getAllCountries());

        return "client"; // add-client.html
    }

    @GetMapping("/edit/{id}")
    public String getClientByID(@PathVariable("id") long id, Model model) {

        model.addAttribute("client", clientService.getClient(id));
        model.addAttribute("countries", clientService.getAllCountries());

        return "edit"; // edit-client.html
    }

    @PostMapping("/addclient")
    public String addClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "client";
        }

        clientService.addClient(client);
        model.addAttribute("clients", clientService.getAllClients());
        return "userInfoPage";
    }

    @PostMapping("/editclient")
    public String editClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }
        clientService.editClient(client);
        model.addAttribute("clients", clientService.getAllClients());
        return "userInfoPage";
    }
}
