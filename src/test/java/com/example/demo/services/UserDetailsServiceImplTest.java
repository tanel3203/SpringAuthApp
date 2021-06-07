package com.example.demo.services;

import com.example.demo.entities.AppUser;
import com.example.demo.repositories.AppRoleRepository;
import com.example.demo.repositories.AppUserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class UserDetailsServiceImplTest {


    AppUserRepository appUserRepository = mock(AppUserRepository.class);

    AppRoleRepository appRoleRepository = mock(AppRoleRepository.class);

    UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(appUserRepository, appRoleRepository);
    @BeforeEach
    public void setup() {


    }

    @Test
    public void verifyUserDetailsThrows_whenNotProperlySetUp() {
        String onErrDesc = "Not found";
        AppUser appUser = null;
        Function<Long, List<String>> getRoleNamesFn = (u) -> List.of();

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsernameFn(onErrDesc, appUser, getRoleNamesFn);
        });

        assertTrue(exception.getMessage().contains(onErrDesc));
    }

    @Test
    public void verifyUserDetailsReturned_whenProperlySetUp() {
        String onErrDesc = "Not found";
        AppUser appUser = AppUser.testUser();
        Function<Long, List<String>> getRoleNamesFn = (u) -> List.of("ROLE_TEST");

        UserDetails userDetails = userDetailsService.loadUserByUsernameFn(onErrDesc, appUser, getRoleNamesFn);

        assertTrue(userDetails.getAuthorities()
                              .stream()
                              .anyMatch(p -> p.getAuthority().equalsIgnoreCase("ROLE_TEST")));
    }

    @Test
    public void verifyUserDetailsReturnedWithNoAuthorities_whenProperlySetUpButNoAuthorities() {
        String onErrDesc = "Not found";
        AppUser appUser = AppUser.testUser();
        Function<Long, List<String>> getRoleNamesFn = (u) -> List.of();

        UserDetails userDetails = userDetailsService.loadUserByUsernameFn(onErrDesc, appUser, getRoleNamesFn);

        assertTrue(userDetails.getAuthorities().isEmpty());
    }

}
