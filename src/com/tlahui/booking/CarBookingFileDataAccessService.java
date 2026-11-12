package com.tlahui.booking;

import com.tlahui.car.Car;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

public class CarBookingFileDataAccessService implements ICarBookingDAO {
    private static final Path FILE_PATH = Path.of("src","com","tlahui","bookings.ser");

    @Override
    public CarBooking[] getCarBookings() {
        try(FileInputStream fileInputStream = new FileInputStream(FILE_PATH.toFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            int length = objectInputStream.readInt();
            CarBooking[] bookings = new CarBooking[length];
            int index = 0;
            for (CarBooking booking : bookings) {
                bookings[index++] =(CarBooking) objectInputStream.readObject();
            }
            return bookings;
        } catch (FileNotFoundException e) {
            System.out.println("Bookings file was not found");
            return new CarBooking[0];
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    @Override
    public void createBooking(CarBooking booking) {
        CarBooking[] bookings = getCarBookings();

        try(FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH.toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeInt((bookings.length + 1));
            for (CarBooking carBooking : bookings) {
                objectOutputStream.writeObject(carBooking);
            }
            objectOutputStream.writeObject(booking);
        } catch (IOException e) {
            System.out.println("Error reading bookings file");
        }

    }

    @Override
    public boolean deleteBooking(CarBooking carBooking) {
        CarBooking[] bookings = getCarBookings();

        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH.toFile());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeInt((bookings.length - 1));

            for (CarBooking booking : bookings) {
                if (booking != null && booking.equals(carBooking)) {
                    continue;
                }
                objectOutputStream.writeObject(booking);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error deleting booking ");
            return false;
        }
    }

    @Override
    public CarBooking[] getBookingByUser(UUID selectedUserID) {
        CarBooking[] allBookings = getCarBookings();
        CarBooking[] bookings; //Array should be initialized empty

        int recordCount = 0; //we will use this variable to count the total occurrences of the users we're searching.
        for(int i = 0; i < allBookings.length; i++){
            if(allBookings[i].getUser().getId().equals(selectedUserID)){
                recordCount++;
            }
        }
        bookings = new CarBooking[recordCount]; //this will help us to define the size of the array we're going to return
        int index = 0;
        for(int i = 0; i < allBookings.length; i++) {
            if(allBookings[i].getUser().getId().equals(selectedUserID)){
                bookings[index] = allBookings[i];
                index++;
            }
        }
        return bookings;
    }

    @Override
    public Car[] getAllBookedCars() {
        CarBooking[] allBookings = getCarBookings();
        Car[] bookedCars = new Car[allBookings.length];
        for(int i = 0; i < allBookings.length; i++){
            bookedCars[i] = allBookings[i].getCar();
        }
        return bookedCars;
    }
}
