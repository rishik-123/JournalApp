package com.Rishik.JournalingApp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "userentries")//stores in table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntry {
    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    @DBRef
    @Builder.Default
    private List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String>roles;
}