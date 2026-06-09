package com.Rishik.JournalingApp.service;

import com.Rishik.JournalingApp.entity.UserEntry;
import com.Rishik.JournalingApp.repository.UserEntryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserEntryRepo userEntryRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        when(userEntryRepo.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(UserEntry.builder()
                        .username("ram")
                        .password("irjgn")
                        .roles(new ArrayList<>())
                        .build()));

        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
        assertNotNull(userDetails);
    }
}
