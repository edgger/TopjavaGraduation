package guru.optimal.topjava.graduation.dao;

import guru.optimal.topjava.graduation.model.User;

import java.util.List;

public interface UserDAO {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    List<User> getAll();
}