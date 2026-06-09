package com.Rishik.JournalingApp.service;

import com.Rishik.JournalingApp.entity.UserEntry;
import com.Rishik.JournalingApp.repository.UserEntryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplIntegrationTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @MockitoBean
    private UserEntryRepo userEntryRepo;

    @Test
    void loadByUsernameTest() {
        when(userEntryRepo.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(UserEntry.builder()
                        .username("ram")
                        .password("ajdjhf")
                        .roles(new ArrayList<>())
                        .build()));

        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }
}
