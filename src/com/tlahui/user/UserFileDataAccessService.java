package com.tlahui.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFileDataAccessService implements IUserDAO{

    @Override
    public List<User> getUsers() {
        List<User> userSeedData = new ArrayList<>();

        try {
            Path filePath = Path.of("src","com","tlahui","users.csv");
            String[] lines = Files.readAllLines(filePath)
                    .toArray(new String[0]);

            // Parse each line and create a User object.
            for (int i = 0; i < lines.length; i++) {
                String[] values = lines[i].split(",", 2);

                UUID id = UUID.fromString(values[0].trim());
                String name = values[1].trim();

                userSeedData.add(new User(id, name));
            }
            return userSeedData;
        } catch ( FileNotFoundException e){
            System.err.println("File doesn't exist yet.");
        } catch (IOException e) {
            System.err.println("Error reading users from file.");
        }
        throw new RuntimeException("There was an Exception while trying to read from file.");
    }
}
