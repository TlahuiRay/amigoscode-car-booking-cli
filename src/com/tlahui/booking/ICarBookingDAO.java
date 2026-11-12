package com.tlahui.booking;

import com.tlahui.car.Car;

import java.util.UUID;

public interface ICarBookingDAO {
    CarBooking[] getCarBookings();
    void createBooking(CarBooking booking);
    boolean deleteBooking(CarBooking carBooking);
    CarBooking[] getBookingByUser(UUID selectedUserID);
    Car[] getAllBookedCars();

}
