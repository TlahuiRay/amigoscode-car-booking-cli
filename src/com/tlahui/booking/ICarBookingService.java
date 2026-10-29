package com.tlahui.booking;

import com.tlahui.car.Car;

import java.util.UUID;

public interface ICarBookingService {
    CarBooking[] getCarBookings();
    void createBooking(CarBooking booking);
    void deleteBooking(CarBooking carBooking);
    CarBooking[] getBookingByUser(UUID selectedUserID);
    Car[] getAllBookedCars();

}
