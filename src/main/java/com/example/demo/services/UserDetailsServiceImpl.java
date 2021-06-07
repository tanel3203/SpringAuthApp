package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

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
    Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private final AppUserRepository appUserRepository;

    private final AppRoleRepository appRoleRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    // provision method for usages, handles the side effects
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return loadUserByUsernameFn("User not found! " + userName,
                                    this.appUserRepository.findByUserName(userName),
                                    this.appRoleRepository::getRoleNames);
    }

    // method under test - method that contains complete pure logic provided by this service
    protected UserDetails loadUserByUsernameFn(String onErrDesc,
                                               AppUser appUser,
                                               Function<Long, List<String>> getRoleNamesFn) {

        if (appUser == null) {
            log.warning(onErrDesc);
            throw new UsernameNotFoundException(onErrDesc);
        }

        log.info("Found User: " + appUser);
        return userDetailsOf(appUser,
                             grantListOf(getRoleNamesFn.apply(appUser.getUserId())));
    }

    private List<GrantedAuthority> grantListOf(List<String> roleNames) {
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        return grantList;
    }

    private UserDetails userDetailsOf(AppUser appUser, List<GrantedAuthority> grantList) {
        return (UserDetails) new User(appUser.getUserName(), //
                                      appUser.getEncrytedPassword(), grantList);
    }

}