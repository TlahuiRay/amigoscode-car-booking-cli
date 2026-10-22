package com.tlahui.booking;

import com.tlahui.car.Car;
import com.tlahui.user.User;

import java.util.UUID;

public class CarBookingService {

    CarBookingDAO carBookingDAO = new CarBookingDAO();

    public CarBooking[] getAllBookings(){
        return carBookingDAO.getCarBookings();
    }

    public void bookACar(CarBooking booking){
        carBookingDAO.createBooking(booking);
    }

    public CarBooking getCarBookingByID(UUID id){
        for (CarBooking carBooking : carBookingDAO.getCarBookings()){
            if(carBooking.getBookingId().equals(id))
                return carBooking;
        }
        return null;
    }

    public void deleteBooking(CarBooking carBooking){
        carBookingDAO.deleteBooking(carBooking);
    }

    public CarBooking[] getCarBookingByUser(UUID selectedUserID) {
        return carBookingDAO.getBookingByUser(selectedUserID);
    }

    public Car[] getAllBookedCars() {
        return carBookingDAO.getAllBookedCars();
    }
}
