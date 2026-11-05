package com.tlahui.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class UserArrayDataAccessService implements IUserDAO {
    private static User[] usersSeedDataArray = null;

    static {
        try {
            //Indications mentioned the following path "src/com/amigoscode/users.csv"
            // however I had already named my project "tlahui", change if necessary
            Path filePath = Path.of("src","com","tlahui","users.csv");
            String[] lines = Files.readAllLines(filePath)
                    .toArray(new String[0]);
            usersSeedDataArray = new User[lines.length];
            // Parse each line and create a User object.
            for (int i = 0; i < lines.length; i++) {
                String[] values = lines[i].split(",", 2);

                UUID id = UUID.fromString(values[0].trim());
                String name = values[1].trim();

                usersSeedDataArray[i] = new User(id, name);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading users file", e);
        }
    }

    public User[] getUsers() {
        return usersSeedDataArray;
    }
}
