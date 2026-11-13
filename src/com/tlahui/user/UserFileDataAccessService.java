package com.tlahui.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFileDataAccessService implements IUserDAO{
    private static User[] usersSeedDataArray;

    @Override
    public User[] getUsers() {
        List<User> usersSeedData = new ArrayList<>();
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
            return usersSeedDataArray;
        } catch ( FileNotFoundException e){
            System.err.println("File doesn't exist yet.");
        } catch (IOException e) {
            System.err.println("Error reading users from file.");
        }
        return new User[0];
    }
}
