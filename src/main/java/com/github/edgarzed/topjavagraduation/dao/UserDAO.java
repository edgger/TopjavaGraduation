package com.github.edgarzed.topjavagraduation.dao;

import com.github.edgarzed.topjavagraduation.model.User;

import java.util.List;

public interface UserDAO {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}