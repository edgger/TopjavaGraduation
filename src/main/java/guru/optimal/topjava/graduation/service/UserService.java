package guru.optimal.topjava.graduation.service;

import guru.optimal.topjava.graduation.model.User;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void update(User user) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    List<User> getAll();
}