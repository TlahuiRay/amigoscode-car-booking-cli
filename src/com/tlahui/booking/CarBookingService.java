package com.tlahui.booking;

import com.tlahui.car.Car;

import java.util.UUID;

public class CarBookingService {

    private ICarBookingService carBookingService;

    public CarBookingService(ICarBookingService carBookingService) {
        this.carBookingService = carBookingService;
    }

    public CarBooking[] getAllBookings(){
        return carBookingService.getCarBookings();
    }

    public void bookACar(CarBooking booking){
        carBookingService.createBooking(booking);
    }

    public CarBooking getCarBookingByID(UUID id){
        for (CarBooking carBooking : carBookingService.getCarBookings()){
            if(carBooking.getBookingId().equals(id))
                return carBooking;
        }
        return null;
    }

    public void deleteBooking(CarBooking carBooking){
        carBookingService.deleteBooking(carBooking);
    }

    public CarBooking[] getCarBookingByUser(UUID selectedUserID) {
        return carBookingService.getBookingByUser(selectedUserID);
    }

    public Car[] getAllBookedCars() {
        return carBookingService.getAllBookedCars();
    }
}
