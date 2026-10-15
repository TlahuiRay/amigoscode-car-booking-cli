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

    public CarBooking getCarBookingByID(int id){
        for (CarBooking carBooking : carBookingDAO.getCarBookings()){
            if(carBooking.getBookingId() == id)
                return carBooking;
        }
        return null;
    }

    public void deleteBooking(CarBooking carBooking){
        carBookingDAO.deleteBooking(carBooking);
    }

    public CarBooking[] getCarBookingByUser(int selectedUserID) {
        return carBookingDAO.getBookingByUser(selectedUserID);
    }

    public Car[] getAllBookedCars() {
        return carBookingDAO.getAllBookedCars();
    }
}
