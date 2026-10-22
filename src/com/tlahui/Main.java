package com.tlahui;

import com.tlahui.booking.BookingStatus;
import com.tlahui.booking.CarBooking;
import com.tlahui.booking.CarBookingService;
import com.tlahui.car.Car;
import com.tlahui.car.CarService;
import com.tlahui.user.User;
import com.tlahui.user.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

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
        String selectedCarID = "", selectedUserID = "";
        String startDateStr = "", endDateStr = "";;
        LocalDate startDate = null, endDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
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
                selectedCarID = scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid value.");
            }

        }

        //Verify Booked Cars
        for (Car bookedCar : bookedCars) {
            if (bookedCar.getId().equals(UUID.fromString(selectedCarID))) {
                System.out.println("You have selected a car that's already booked. Try a different car");
                return;
            }
        }

        Car carToBook = carService.getCarByID(UUID.fromString(selectedCarID));
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
                selectedUserID = scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid value.");
            }
        }
        User userToBook = userService.getUserByID(UUID.fromString(selectedUserID));
        if(Optional.ofNullable(userToBook).isEmpty()) {
            System.out.println("Selected user is not valid");
            return;
        }

        System.out.println("Indicate booking start date (yyyy-MM-dd)");
        while(startDate == null){
            try{
                startDateStr = scanner.nextLine();
                startDate = LocalDate.parse(startDateStr, formatter);
                if(startDate.isBefore(LocalDate.now())){
                    System.out.println("Invalid Date, Start Date cannot be before today");
                    startDate = null;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date, try again with a valid value (yyyy-MM-dd)");
            }
        }

        System.out.println("Indicate booking end date (yyyy-MM-dd)");
        while(endDate == null){
            try{
                endDateStr = scanner.nextLine();
                endDate = LocalDate.parse(endDateStr, formatter);
                if(endDate.isBefore(startDate)){
                    System.out.println("Invalid Date, End Date cannot be Start Date");
                    endDate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date, try again with a valid value (yyyy-MM-dd)");
            }
        }

        carBookingService.bookACar(
                new CarBooking(
                        UUID.randomUUID(),
                        userToBook,
                        carToBook,
                        startDate,
                        endDate,
                        carToBook.getRentalPricePerDay()*startDate.until(endDate, ChronoUnit.DAYS),
                        BookingStatus.ACTIVE));
        System.out.println("User: " + userToBook.getName() +"\n"+
                "Has successfully booked Car: " + carToBook.getBrand() +"\n"+
                "with Registration Number: "+ carToBook.getRegNumber()+ "\n");
    }
    private static void viewAllUserBookedCars(CarBookingService carBookingService, UserService userService, Scanner scanner){
        boolean validInput = false;
        String selectedUserID = "";

        System.out.println("Select the ID of an existing User");
        while (!validInput) {
            try {
                for(User user : userService.getAllUsers())
                    System.out.println(user.toString());
                selectedUserID = scanner.nextLine();;
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid value.");
            }
        }

        CarBooking[] bookings = carBookingService.getCarBookingByUser(UUID.fromString(selectedUserID));
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
        CarBooking[] carBookings = carBookingService.getAllBookings();
        if(carBookings.length == 0){
            System.out.println("Currently there's no bookings");
            return;
        }
        for(CarBooking carBooking : carBookings){
            System.out.println(carBooking.toString());
        }
    }

    private static void deleteBooking(CarBookingService carBookingService, Scanner scanner){
        CarBooking[] carBookings = carBookingService.getAllBookings();
        String selectedBooking = "";
        boolean validInput = false;
        if(carBookings.length == 0){
            System.out.println("Currently there's no bookings");
            return;
        }

        System.out.println("Select the ID of an existing Booking from the list: ");
        while (!validInput) {
            try {
                for(CarBooking carBooking : carBookings){
                    System.out.println(carBooking.toString());
                }
                selectedBooking = scanner.nextLine();;
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an numeric value.");
            }
        }
        CarBooking bookingToDelete = carBookingService.getCarBookingByID(UUID.fromString(selectedBooking));

        if(Optional.ofNullable(bookingToDelete).isEmpty()) {
            System.out.println("Selected booking is not valid");
            return;
        }
        carBookingService.deleteBooking(bookingToDelete);
        System.out.println("Booking Was Successfully Deleted");
    }

    private static void viewAvailableCars(CarService carService, CarBookingService carBookingService, boolean onlyElectric){
        System.out.println("View All Available Cars ");
        if(onlyElectric){
            Car[] cars = carService.getAvailableElectricCars(carBookingService.getAllBookedCars());
            if(cars.length == 0)
                System.out.println("Electric Cars Not found");
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