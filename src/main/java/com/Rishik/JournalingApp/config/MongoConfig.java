package com.Rishik.JournalingApp.config;

import com.Rishik.JournalingApp.entity.JournalEntry;
import com.Rishik.JournalingApp.entity.UserEntry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initCollections() {
        if (!mongoTemplate.collectionExists(JournalEntry.class)) {
            mongoTemplate.createCollection(JournalEntry.class);
        }
        if (!mongoTemplate.collectionExists(UserEntry.class)) {
            mongoTemplate.createCollection(UserEntry.class);
        }
    }
}
