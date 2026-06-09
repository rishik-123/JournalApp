package com.Rishik.JournalingApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries") // stores in table
@Data
public class JournalEntry {
    @Id // primary key
    private ObjectId id;
    private LocalDateTime date;
    @NonNull
    private String title;
    private String content;
    private String weather;
}