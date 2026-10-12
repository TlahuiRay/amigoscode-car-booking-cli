package com.tlahui.booking;

import com.tlahui.car.Car;
import com.tlahui.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CarBookingDAO {
    private static CarBooking[] carBookingsArray;
    private static int idAssigned = 1;

    static {
        carBookingsArray = new CarBooking[0];
    }

    public CarBooking[] getCarBookings() {
        return carBookingsArray;
    }

    public void createBooking(Car car, User user){
        System.out.println("Lenght before: "+carBookingsArray.length);
        //Extend the array by +1 each time a new record is inserted
        CarBooking[] carBookingsArrayExtended = new CarBooking[carBookingsArray.length+1];
        for(int i = 0; i <carBookingsArray.length; i++){
            carBookingsArrayExtended[i] = carBookingsArray[i];
        }
        //Rewrite the original array with the new extended array
        carBookingsArray = Arrays.copyOf(carBookingsArrayExtended, carBookingsArrayExtended.length);

        //Insert the new record in the last position
        carBookingsArray[carBookingsArray.length-1]
                = new CarBooking(
                        idAssigned++,
                        user,
                        car,
                        LocalDateTime.now(),
                        BookingStatus.ACTIVE);
        System.out.println("Lenght after: "+carBookingsArray.length);
    }

}
