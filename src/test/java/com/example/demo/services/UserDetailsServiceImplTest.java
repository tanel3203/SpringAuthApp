package com.example.demo.services;

import com.example.demo.repositories.AppRoleRepository;
import com.example.demo.repositories.AppUserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static com.example.demo.TestConstants.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class UserDetailsServiceImplTest {


    AppUserRepository appUserRepository = mock(AppUserRepository.class);

    AppRoleRepository appRoleRepository = mock(AppRoleRepository.class);

    UserDetailsServiceImpl userDetailsService =
            new UserDetailsServiceImpl(appUserRepository, appRoleRepository);

    @BeforeEach
    public void setup() {


    }

    @Test
    public void verifyUserDetailsThrows_whenNotProperlySetUp() {

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsernameFn(ON_ERR_DESCRIPTION,
                                                    APP_USER_NULL,
                                                    (u) -> List.of());
        });

        assertTrue(exception.getMessage().contains(ON_ERR_DESCRIPTION));
    }

    @Test
    public void verifyUserDetailsReturned_whenProperlySetUp() {
        UserDetails userDetails = userDetailsService.loadUserByUsernameFn(ON_ERR_DESCRIPTION,
                                                                          APP_USER,
                                                                          (u) -> List.of(ROLE_TEST));

        assertTrue(userDetails.getAuthorities()
                              .stream()
                              .anyMatch(p -> p.getAuthority().equalsIgnoreCase(ROLE_TEST)));
    }

    @Test
    public void verifyUserDetailsReturnedWithNoAuthorities_whenProperlySetUpButNoAuthorities() {
        UserDetails userDetails = userDetailsService.loadUserByUsernameFn(ON_ERR_DESCRIPTION,
                                                                          APP_USER,
                                                                          (u) -> List.of());

        assertTrue(userDetails.getAuthorities().isEmpty());
    }

}
