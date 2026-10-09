package com.tlahui.user;

import java.util.UUID;

public class UserDAO {

    private static final User[] usersSeedDataArray;

    static {
        usersSeedDataArray = new User[]{
                new User(1, "User1"),
                new User(2, "User2"),
                new User(3, "User3"),
                new User(4, "User4"),
                new User(5, "User5"),
                new User(6, "User6"),
                new User(7, "User7"),
                new User(8, "User8")

        };
    }

    public User[] getUsers() {
        return usersSeedDataArray;
    }
}
