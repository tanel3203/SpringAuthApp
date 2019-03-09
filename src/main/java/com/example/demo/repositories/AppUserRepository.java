package com.example.demo.repositories;

import com.example.demo.entities.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByUserName(String userName);
}
