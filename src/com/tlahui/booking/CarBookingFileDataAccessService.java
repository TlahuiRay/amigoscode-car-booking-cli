package com.tlahui.booking;

import com.tlahui.car.Car;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingFileDataAccessService implements ICarBookingDAO {
    private static final Path FILE_PATH = Path.of("src","com","tlahui","bookings.ser");

    @Override
    public List<CarBooking>  getCarBookings() {
        List<CarBooking> bookings = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(FILE_PATH.toFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            for (CarBooking booking : bookings) {
                bookings.add((CarBooking) objectInputStream.readObject());
            }
            return bookings;
        } catch (FileNotFoundException e) {
            System.out.println("Bookings file was not found");
            return bookings;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    @Override
    public void createBooking(CarBooking booking) {
        List<CarBooking> bookings = getCarBookings();

        try(FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH.toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeInt((bookings.size() + 1));
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
        List<CarBooking> bookings = getCarBookings();

        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH.toFile());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeInt((bookings.size() - 1));

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
    public List<CarBooking>  getBookingByUser(UUID selectedUserID) {
        List<CarBooking> allBookings = getCarBookings();
        List<CarBooking> bookings = new ArrayList<>();


        for(int i = 0; i < allBookings.size(); i++) {
            if(allBookings.get(i).getUser().getId().equals(selectedUserID)){
                bookings.add(allBookings.get(i));
            }
        }
        return bookings;
    }

    @Override
    public List<Car> getAllBookedCars() {
        List<CarBooking>allBookings = getCarBookings();
        List<Car> bookedCars = new ArrayList<>();

        for(int i = 0; i < allBookings.size(); i++){
            bookedCars.add(allBookings.get(i).getCar());
        }
        return bookedCars;
    }
}
