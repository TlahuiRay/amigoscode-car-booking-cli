package com.tlahui.booking;

public class CarBookingDAO {
    private final static CarBooking[] carBookings;
    private final static int MAX_LIMIT = 99;

    static {
        carBookings = new CarBooking[MAX_LIMIT];
    }

    public CarBooking[] getCarBookings() {
        return carBookings;
    }

}
