package com.example.demo;

import com.example.demo.entities.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    ClientRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public void demo() {


        // save a couple of customers
//        repository.save(new Client("Jack", "Bauer"));
//        repository.save(new Client("Chloe", "O'Brian"));
//        repository.save(new Client("Kim", "Bauer"));
//        repository.save(new Client("David", "Palmer"));
//        repository.save(new Client("Michelle", "Dessler"));

//        for (Client client : repository.findAll()) {
//            System.out.println(client);
//        }

    }
}
