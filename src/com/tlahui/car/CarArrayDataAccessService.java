package com.tlahui.car;

import com.tlahui.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class CarArrayDataAccessService implements ICarDAO {
    private static Car[] carsSeedDataArray;

    static {
        try {
            //Indications mentioned the following path "src/com/amigoscode/users.csv"
            // however I had already named my project "tlahui", change if necessary
            String filePath = "src\\com\\tlahui\\cars.csv";
            String[] lines = Files.readAllLines(Path.of(filePath))
                    .toArray(new String[0]);
            carsSeedDataArray = new Car[lines.length];
            // Parse each line and create a User object.
            for (int i = 0; i < lines.length; i++) {
                String[] values = lines[i].split(",", 5);

                UUID id = UUID.fromString(values[0].trim());
                String regNumber = values[1].trim();
                double price = Double.parseDouble(values[2].trim());
                Brand brand = switch (values[3].trim()) {
                    case "FORD" -> Brand.FORD;
                    case "CHEVROLET" -> Brand.CHEVROLET;
                    case "VOLKSWAGEN" -> Brand.VOLKSWAGEN;
                    case "TOYOTA" -> Brand.TOYOTA;
                    case "BYD" -> Brand.BYD;
                    default -> Brand.UNKNOWN;
                };
                boolean isElectric = Boolean.parseBoolean(values[4].trim());

                carsSeedDataArray[i] = new Car(id, regNumber, price, brand, isElectric);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }


    public Car[] getCars() {
        return carsSeedDataArray;
    }
}
