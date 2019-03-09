package com.example.demo.repositories;

import com.example.demo.entities.AppRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepository extends CrudRepository<AppRole, Long> {

//    @Query("SELECT ar.roleName FROM AppRole ar JOIN AppUser au ON au.roleId = ar.roleId WHERE au.userId = ?1")
//    List<String> getRoleNames(Long userId);


    @Query("SELECT ur.appRole.roleName FROM UserRole ur WHERE ur.appUser.userId = ?1")
    List<String> getRoleNames(Long userId);
}
