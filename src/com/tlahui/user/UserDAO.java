package com.tlahui.user;

import java.util.UUID;

public class UserDAO {

    private static final User[] usersSeedDataArray;

    static {
        usersSeedDataArray = new User[]{
                new User(UUID.fromString("783ffe6c-82a9-47f5-a153-c24b902258d9"), "User-1"),
                new User(UUID.fromString("a7acec56-3629-4a7e-9229-d69162ba1d63"), "User-2"),
                new User(UUID.fromString("d6720c5c-3753-4882-84cf-a4c71a1ff068"), "User-3"),
                new User(UUID.fromString("8f9bb8a7-8524-416e-a065-3e0a2a8793ee"), "User-4"),
                new User(UUID.fromString("416b4350-ec0c-428b-8f85-b56c60ea5e88"), "User-5"),
                new User(UUID.fromString("4811910c-b3ad-488f-b862-220d97897880"), "User-6"),
                new User(UUID.fromString("ea0803bc-6320-4344-be30-498759dea37b"), "User-7"),
                new User(UUID.fromString("3843e83c-31eb-41de-b349-616d3ace4bb6"), "User-8")

        };
    }

    public User[] getUsers() {
        return usersSeedDataArray;
    }
}
