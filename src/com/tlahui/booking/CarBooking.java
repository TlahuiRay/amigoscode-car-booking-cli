package com.tlahui.booking;

import com.tlahui.car.Car;
import com.tlahui.user.User;
import java.time.LocalDateTime;
import java.util.Objects;


public class CarBooking {
    private int bookingId;
    private User user;
    private Car car;
    private LocalDateTime timeStamp;
    private BookingStatus status;

    public CarBooking(int bookingId, User user, Car car, LocalDateTime timeStamp, BookingStatus status) {
        this.bookingId = bookingId;
        this.user = user;
        this.car = car;
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBooking that = (CarBooking) o;
        return bookingId == that.bookingId && Objects.equals(user, that.user) && Objects.equals(car, that.car) && Objects.equals(timeStamp, that.timeStamp) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, user, car, timeStamp, status);
    }

    @Override
    public String toString() {
        return "CarBooking{" +
                "bookingId=" + bookingId +
                ", user=" + user +
                ", car=" + car +
                ", timeStamp=" + timeStamp +
                ", status=" + status +
                '}';
    }
}
