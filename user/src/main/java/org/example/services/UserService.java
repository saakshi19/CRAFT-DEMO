package org.example.services;

import org.example.dao.UserDao;
import org.example.models.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserProfile getUser(String emailId) {
        return userDao.getUserProfile(emailId);
    }

    public UserProfile addUser(UserProfile user) {
        userDao.addUser(user);
        return user;
    }
}
