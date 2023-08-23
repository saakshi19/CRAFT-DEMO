package org.example.dao;

import org.example.models.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {

    private final Map<String, UserProfile> users = new HashMap<>();

    public void addUser(UserProfile userProfile) {
        users.put(userProfile.getEmailId(), userProfile);
    }

    public UserProfile getUserProfile(String emailId) {
        return users.get(emailId);
    }
}
