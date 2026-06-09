package com.Rishik.JournalingApp.controller;

import com.Rishik.JournalingApp.entity.UserEntry;
import com.Rishik.JournalingApp.service.UserEntryService;
import com.Rishik.JournalingApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public")
public class PublicController {
    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/health-check")
    public String HealthCheck(){
        return "Ok";
    }
    @PostMapping("/createuser")
    public void createUser(@RequestBody UserEntry userentry){
        userEntryService.saveNewUser(userentry);
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<?> getWeather(@PathVariable String city) {
        try {
            return new ResponseEntity<>(weatherService.getWeather(city), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching weather: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
