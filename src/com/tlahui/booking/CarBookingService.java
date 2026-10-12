package com.tlahui.booking;

import com.tlahui.car.Car;
import com.tlahui.user.User;

public class CarBookingService {

    CarBookingDAO carBookingDAO = new CarBookingDAO();

    public CarBooking[] getAllBookings(){
        return carBookingDAO.getCarBookings();
    }

    public void bookACar(Car car, User user){
        carBookingDAO.createBooking(car, user);
    }
}
