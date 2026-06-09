package com.Rishik.JournalingApp.controller;

import com.Rishik.JournalingApp.entity.UserEntry;
import com.Rishik.JournalingApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("allusers")
    public ResponseEntity<?> getAllUsers() {
        List<UserEntry> all = userEntryService.getAll();
        if (all != null && !all.isEmpty()) {
            List<UserEntry> admins = all.stream()
                    .filter(user -> user.getRoles() != null && user.getRoles().contains("ADMIN"))
                    .collect(Collectors.toList());
            if (!admins.isEmpty()) {
                return new ResponseEntity<>(admins, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin-user")
    public ResponseEntity<?> createAdminUser(@RequestBody UserEntry user) {
        try {
            userEntryService.saveAdmin(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
