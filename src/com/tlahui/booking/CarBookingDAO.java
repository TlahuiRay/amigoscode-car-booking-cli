package com.tlahui.booking;

import com.tlahui.car.Car;
import com.tlahui.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class CarBookingDAO {
    private static CarBooking[] carBookingsArray;

    static {
        carBookingsArray = new CarBooking[0];
    }

    public CarBooking[] getCarBookings() {
        return carBookingsArray;
    }

    public void createBooking(CarBooking booking){
        //Extend the array by +1 each time a new record is inserted
        CarBooking[] carBookingsArrayExtended = new CarBooking[carBookingsArray.length+1];
        for(int i = 0; i <carBookingsArray.length; i++){
            carBookingsArrayExtended[i] = carBookingsArray[i];
        }
        //Rewrite the original array with the new extended array
        carBookingsArray = Arrays.copyOf(carBookingsArrayExtended, carBookingsArrayExtended.length);

        //Insert the new record in the last position
        carBookingsArray[carBookingsArray.length-1] = booking;
    }

    public void deleteBooking(CarBooking carBooking){
        //Delete the booking by making the existing record equal to null
        for(int i = 0; i < carBookingsArray.length; i++){
            if(carBookingsArray[i].equals(carBooking)){
                carBookingsArray[i] = null;
                break;
            }
        }
        //Reduce the array by -1 each time a booking is deleted and copy it into the temporal reduced array
        int index = 0;
        CarBooking[] carBookingsArrayReduced = new CarBooking[carBookingsArray.length-1];
        for(int i = 0; i <carBookingsArray.length; i++){
            if(carBookingsArray[i]!=null){
                carBookingsArrayReduced[index] = carBookingsArray[i];
                index++;
            }
        }

        //Rewrite the original array with the new temporal reduced array
        carBookingsArray = Arrays.copyOf(carBookingsArrayReduced, carBookingsArrayReduced.length);
    }

    public CarBooking[] getBookingByUser(UUID selectedUserID){
        CarBooking[] bookings; //Array should be initialized empty
        int recordCount = 0; //we will use this variable to count the total occurrences of the users we're searching.
        for(int i = 0; i < carBookingsArray.length; i++){
            if(carBookingsArray[i].getUser().getId().equals(selectedUserID)){
                recordCount++;
            }
        }
        bookings = new CarBooking[recordCount]; //this will help us to define the size of the array we're going to return
        int index = 0;
        for(int i = 0; i < carBookingsArray.length; i++) {
            if(carBookingsArray[i].getUser().getId().equals(selectedUserID)){
                bookings[index] = carBookingsArray[i];
                index++;
            }
        }

        return bookings;
    }

    public Car[] getAllBookedCars() {
        Car[] bookedCars = new Car[carBookingsArray.length];
        for(int i = 0; i < carBookingsArray.length; i++){
            bookedCars[i] = carBookingsArray[i].getCar();
        }
        return bookedCars;

    }
}
