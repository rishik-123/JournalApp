package com.Rishik.JournalingApp.service;

import com.Rishik.JournalingApp.entity.UserEntry;
import com.Rishik.JournalingApp.repository.UserEntryRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserEntryRepo userEntryRepo;
    private UserEntry userEntry;

    @ParameterizedTest
    @CsvSource({
            "Ram",
            "Shyam","Ramesh"
    })
    public void testFindByUserName(String name) {
        UserEntry user = userEntryRepo.findByUsername(name).orElse(null);
        if (user == null) {
            user = new UserEntry(name, "password");
            userEntryRepo.save(user);
        }
        userEntry = user;
        assertNotNull(userEntry);
        assertTrue(userEntry.getJournalEntries() == null || userEntry.getJournalEntries().isEmpty(),"Failed for:"+name);
    }
    @ParameterizedTest
    @CsvSource({
            "1,1,2","2,10,12","3,3,6"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}