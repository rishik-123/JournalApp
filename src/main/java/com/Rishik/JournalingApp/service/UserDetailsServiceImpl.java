package com.Rishik.JournalingApp.service;

import com.Rishik.JournalingApp.entity.UserEntry;
import com.Rishik.JournalingApp.repository.UserEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserEntryRepo userEntryRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userEntryRepo.findByUsername(username).orElse(null);
        if (user != null) {
            org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword());
            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                builder.roles(user.getRoles().toArray(new String[0]));
            } else {
                builder.roles("USER");
            }
            return builder.build();
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}

