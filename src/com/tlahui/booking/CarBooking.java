package com.tlahui.booking;

import com.tlahui.car.Car;
import com.tlahui.user.User;
import java.time.LocalDateTime;


public class CarBooking {
    private int bookingId;
    private User user;
    private Car car;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    LocalDateTime bookedAt;


}
