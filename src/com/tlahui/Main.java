package com.tlahui;

import com.tlahui.booking.CarBooking;
import com.tlahui.booking.CarBookingService;
import com.tlahui.car.Car;
import com.tlahui.car.CarService;
import com.tlahui.user.User;
import com.tlahui.user.UserService;

import java.lang.reflect.Array;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean keepGoing = true;
        Scanner scanner = new Scanner(System.in);

        CarService carService = new CarService();
        CarBookingService carBookingService = new CarBookingService();
        UserService userService = new UserService();


        System.out.println("Car Booking CLI powered by Tlahui");
        while(keepGoing) {
            System.out.println(
                            "1. Book Car\n"+
                            "2. View All User Booked Cars\n"+
                            "3. View All Bookings\n"+
                            "4. Delete Booking\n"+
                            "5. View All Available Cars\n"+
                            "6. View all Available Electric Cars\n"+
                            "7. View all users\n"+
                            "8. Exit\n\n"+
                            "Select the number of an option: "
                            );

            switch (scanner.nextLine()){
                case "1" : bookCar(carBookingService, carService, userService,  scanner);
                    break;
                case "2" : viewAllUserBookedCars(carBookingService, userService, scanner);
                    break;
                case "3" : viewAllBookings(carBookingService);
                    break;
                case "4" : deleteBooking(carBookingService, scanner);
                    break;
                case "5" : viewAvailableCars(carService,  carBookingService,  false);
                    break;
                case "6" : viewAvailableCars(carService, carBookingService, true);
                    break;
                case "7" : viewAllUsers(userService);
                    break;
                case "8" :
                    System.out.println("Exit, ByeBye");
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Not a valid option, please try again\n");
            };
        }
    }

    private static void bookCar(CarBookingService carBookingService,CarService carService, UserService userService,
                                Scanner scanner ){
        System.out.println("Book a Car");
        Car[] cars = carService.getAvailableCars(getAllBookedCars(carBookingService));
        Car[] bookedCars = getAllBookedCars(carBookingService);
        int selectedCarID = -1;
        int selectedUserID = -1;
        boolean validInput = false;

        if(cars.length == 0) {
            System.out.println("Currently there's no Cars Available");
            return;
        }

        System.out.println("Select the ID of an available Car");
        while (!validInput) {
            try {
                for(Car car : cars)
                    System.out.println(car.toString());
                selectedCarID = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.Please enter an numeric value.");
                scanner.nextLine();
            }

        }

        //Verify Booked Cars
        for (Car bookedCar : bookedCars) {
            if (bookedCar.getId() == selectedCarID) {
                System.out.println("You have selected a car that's already booked. Try a different car");
                return;
            }
        }


        Car carToBook = carService.getCarByID(selectedCarID);
        if(Optional.ofNullable(carToBook).isEmpty()) {
            System.out.println("Selected car is not valid");
            return;
        }

        validInput = false;
        System.out.println("Select the ID of an existing User");
        while (!validInput) {
            try {
                for(User user : userService.getAllUsers())
                    System.out.println(user.toString());
                selectedUserID = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an numeric value.");
                scanner.nextLine();
            }
        }
        User userToBook = userService.getUserByID(selectedUserID);
        if(Optional.ofNullable(userToBook).isEmpty()) {
            System.out.println("Selected user is not valid");
            return;
        }

        carBookingService.bookACar(carToBook, userToBook);
        System.out.println("User: " + userToBook.toString() +"\nHas successfully booked Car: " + carToBook.toString());
    }
    private static void viewAllUserBookedCars(CarBookingService carBookingService, UserService userService, Scanner scanner){
        boolean validInput = false;
        int selectedUserID = -1;

        System.out.println("Select the ID of an existing User");
        while (!validInput) {
            try {
                for(User user : userService.getAllUsers())
                    System.out.println(user.toString());
                selectedUserID = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an numeric value.");
                scanner.nextLine();
            }
        }

        CarBooking[] bookings = carBookingService.getCarBookingByUser(selectedUserID);
        if(bookings.length == 0){
            System.out.println("Selected user doesn't have any booking");
            return;
        }
        System.out.println("Bookings corresponding to user with ID: "+selectedUserID);
        for(CarBooking booking: bookings){
            System.out.println(booking.toString());
        }
    }

    private static void viewAllBookings(CarBookingService carBookingService){
        System.out.println("View All Bookings");
        for(CarBooking carBooking : carBookingService.getAllBookings()){
            System.out.println(carBooking.toString());
        }
    }

    private static void deleteBooking(CarBookingService carBookingService, Scanner scanner){
        CarBooking[] carBookings = carBookingService.getAllBookings();
        if(carBookings.length == 0){
            System.out.println("Currently there's no bookings");
            return;
        }
        System.out.println("Select the ID of an existing Booking from the list: ");
        for(CarBooking carBooking : carBookings){
            System.out.println(carBooking.toString());
        }
        int selectedBooking = scanner.nextInt();
        CarBooking bookingToDelete = carBookingService.getCarBookingByID(selectedBooking);

        if(Optional.ofNullable(bookingToDelete).isEmpty()) {
            System.out.println("Selected booking is not valid");
            return;
        }
        carBookingService.deleteBooking(bookingToDelete);
    }

    private static void viewAvailableCars(CarService carService, CarBookingService carBookingService, boolean onlyElectric){
        System.out.println("View All Available Cars ");
        if(onlyElectric){
            Car[] cars = carService.getAvailableElectricCars(carBookingService.getAllBookedCars());
            if(cars.length == 0)
                System.out.println("Electric Cards Not found");
            else{
                for(Car car : cars)
                    System.out.println(car.toString());
            }
        }
        else{
            Car[] cars = carService.getAvailableCars(carBookingService.getAllBookedCars());
            if(cars.length == 0)
                System.out.println("Currently there's no Cars Available");
            else{
                for(Car car : cars)
                    System.out.println(car.toString());
            }
        }
    }

    private static void viewAllUsers(UserService userService){
        for(User user : userService.getAllUsers()){
            System.out.println(user.toString());
        }
    }

    private static Car[] getAllBookedCars(CarBookingService carBookingService) {
        return carBookingService.getAllBookedCars();
    }
}