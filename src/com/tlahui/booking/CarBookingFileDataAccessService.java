package com.tlahui.booking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CarBookingFileDataAccessService {
    private static final Path FILE_PATH = Path.of("src","com","tlahui","bookings.ser");

    public static void writeToFile(CarBooking[] carBookingsArray) throws IOException {
        if (Files.notExists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
        Files.write(FILE_PATH, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        try (ObjectOutputStream out = new ObjectOutputStream(
                Files.newOutputStream(FILE_PATH, StandardOpenOption.WRITE))) {
            for (int i = 0; i < carBookingsArray.length; i++) {
                if (carBookingsArray[i] != null) {
                    out.writeObject(carBookingsArray[i]);
                }
            }
            out.flush();
        }
    }
}
