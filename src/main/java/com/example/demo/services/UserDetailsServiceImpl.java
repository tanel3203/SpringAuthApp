package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.example.demo.entities.AppUser;
import com.example.demo.repositories.AppRoleRepository;
import com.example.demo.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    private final AppRoleRepository appRoleRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return loadUserByUsernameFn("User not found! " + userName,
                                    this.appUserRepository.findByUserName(userName),
                                    (u) -> this.appRoleRepository.getRoleNames(u));
    }

    public UserDetails loadUserByUsernameFn(String onErrDesc, AppUser appUser, Function<Long, List<String>> getRoleNamesFn) {

        if (appUser == null) {
            System.out.println(onErrDesc);
            throw new UsernameNotFoundException(onErrDesc);
        }

        System.out.println("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = getRoleNamesFn.apply(appUser.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                                                         appUser.getEncrytedPassword(), grantList);

        return userDetails;
    }

}