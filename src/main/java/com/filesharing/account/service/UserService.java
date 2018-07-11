package com.filesharing.account.service;

import com.filesharing.account.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}