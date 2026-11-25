package com.tlahui.booking;

import com.tlahui.car.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingArrayDataAccessService implements ICarBookingDAO {
    private static List<CarBooking> bookings = new ArrayList<>();

    @Override
    public List<CarBooking>  getCarBookings() {
        return bookings;
    }

    @Override
    public void createBooking(CarBooking booking) {
        bookings.add(booking);
    }

    @Override
    public boolean deleteBooking(CarBooking carBooking) {
        if(bookings.remove(carBooking))
            return true;
        return false;
    }

    @Override
    public List<CarBooking>  getBookingByUser(UUID selectedUserID) {
        List<CarBooking> bookingsByUser = new ArrayList<>();
        for( CarBooking booking : bookings) {
            if(booking.getUser().getId().equals(selectedUserID))
                bookingsByUser.add(booking);
        }
        return bookingsByUser;
    }

    @Override
    public List<Car> getAllBookedCars() {
        List<Car> bookedCars = new ArrayList<>();
        for (CarBooking booking : bookings) {
            bookedCars.add(booking.getCar());
        }
        return bookedCars;
    }
}