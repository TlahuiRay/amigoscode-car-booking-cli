package com.tlahui.booking;

import com.tlahui.car.Car;

import java.util.List;
import java.util.UUID;

public interface ICarBookingDAO {
    List<CarBooking> getCarBookings();
    void createBooking(CarBooking booking);
    boolean deleteBooking(CarBooking carBooking);
    List<CarBooking>  getBookingByUser(UUID selectedUserID);
    List<Car> getAllBookedCars();

}
