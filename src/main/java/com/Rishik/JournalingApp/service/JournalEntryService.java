package com.Rishik.JournalingApp.service;

import com.Rishik.JournalingApp.api.response.WeatherResponse;
import com.Rishik.JournalingApp.entity.JournalEntry;
import com.Rishik.JournalingApp.entity.UserEntry;
import com.Rishik.JournalingApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private WeatherService weatherService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        Optional<UserEntry> userOptional = userEntryService.findByUsername(username);
        try {
            if (userOptional.isPresent()) {
                String weatherDetails = getWeather("Mumbai");
                if (weatherDetails != null) {
                    journalEntry.setWeather(weatherDetails);
                }
                JournalEntry saved = journalEntryRepo.save(journalEntry);
                UserEntry user = userOptional.get();
                user.getJournalEntries().add(saved);
                userEntryService.saveEntry(user);
            }
        } catch (Exception e) {
            logger.error("ERROR OCCURED", e);
            throw new RuntimeException("ERROR OCCURED");
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        Optional<UserEntry> userOptional = userEntryService.findByUsername(username);
        if (userOptional.isPresent()) {
            UserEntry user = userOptional.get();
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userEntryService.saveEntry(user);
                journalEntryRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }

    public List<JournalEntry> findByUsername(String username) {
        Optional<UserEntry> user = userEntryService.findByUsername(username);
        return user.map(UserEntry::getJournalEntries).orElse(null);
    }

    public String getWeather(String city) {
        try {
            WeatherResponse weatherResponse = weatherService.getWeather(city);
            if (weatherResponse != null && weatherResponse.getMain() != null) {
                return "Temp: " + weatherResponse.getMain().getTemp() + "°C, Feels like: " + weatherResponse.getMain().getFeelsLike() + "°C, Humidity: " + weatherResponse.getMain().getHumidity() + "%, Weather: " + weatherResponse.getWeather().get(0).getDescription();
            }
        } catch (Exception e) {
            logger.error("Error fetching weather in JournalEntryService", e);
        }
        return null;
    }
}
