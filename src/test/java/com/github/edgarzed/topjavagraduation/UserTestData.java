package com.github.edgarzed.topjavagraduation;

import com.github.edgarzed.topjavagraduation.model.Role;
import com.github.edgarzed.topjavagraduation.model.User;

public class UserTestData {
    private UserTestData() {
    }

    public static final User ADMIN = new User(1000, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final User USER1 = new User(1001, "UserAAA", "userAAA@yandex.ru", "password", Role.ROLE_USER);
    public static final User USER2 = new User(1002, "UserBBB", "userBBB@yandex.ru", "password", Role.ROLE_USER);
    public static final User USER3 = new User(1003, "UserCCC", "userCCC@yandex.ru", "password", Role.ROLE_USER);


}