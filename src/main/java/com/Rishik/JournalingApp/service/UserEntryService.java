package com.Rishik.JournalingApp.service;

import com.Rishik.JournalingApp.controller.JournalEntryControllerV2;
import com.Rishik.JournalingApp.entity.UserEntry;
import com.Rishik.JournalingApp.repository.UserEntryRepo;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Component
@Slf4j
public class UserEntryService {
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryControllerV2.class);

    @Autowired
    private UserEntryRepo userEntryRepo;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(UserEntry userEntry) {
        userEntryRepo.save(userEntry);
    }

    public boolean saveNewUser(UserEntry userEntry) {
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USER"));
            userEntryRepo.save(userEntry);
            return true;
        } catch (Exception e) {
            log.error("ERROR OCCURED",e);
            log.warn("HAHAHA");
            log.debug("HAHAHA");
            log.info("HAHAHA");
            log.trace("HAHAHA");
            return false;
        }
    }

public void saveAdmin(UserEntry userEntry) {
    userEntry.setPassword(UserEntryService.passwordEncoder.encode(userEntry.getPassword()));
    userEntry.setRoles(Arrays.asList("USER", "ADMIN"));
    userEntryRepo.save(userEntry);
}

public List<UserEntry> getAll() {
    return userEntryRepo.findAll();
}

public Optional<UserEntry> findById(ObjectId id) {
    return userEntryRepo.findById(id);
}

public void deleteById(ObjectId id) {
    userEntryRepo.deleteById(id);
}

public Optional<UserEntry> findByUsername(String username) {
    return userEntryRepo.findByUsername(username);
}

public void deleteByUsername(String username) {
    userEntryRepo.deleteByUsername(username);
}
}
