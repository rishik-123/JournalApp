package com.Rishik.JournalingApp.repository;

import com.Rishik.JournalingApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntryRepo extends MongoRepository<UserEntry, ObjectId> {
    Optional<UserEntry> findByUsername(String username);
    void deleteByUsername(String username);
}
