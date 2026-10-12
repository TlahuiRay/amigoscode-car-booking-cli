package com.tlahui;

import com.tlahui.booking.CarBooking;
import com.tlahui.booking.CarBookingService;
import com.tlahui.car.Car;
import com.tlahui.car.CarService;
import com.tlahui.user.User;
import com.tlahui.user.UserService;

import java.lang.reflect.Array;
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
                            "4. View All Available Cars\n"+
                            "5. View all Available Electric Cars\n"+
                            "6. View all users\n"+
                            "7. Exit\n\n"+
                            "Select the number of an option: "
                            );

            switch (scanner.nextLine()){
                case "1" : bookCar(carBookingService, carService, userService,  scanner);
                    break;
                case "2" : viewAllUserBookedCars();
                    break;
                case "3" : viewAllBookings(carBookingService);
                    break;
                case "4" : viewAvailableCars(carService, false);
                    break;
                case "5" : viewAvailableCars(carService, true);
                    break;
                case "6" : viewAllUsers(userService);
                    break;
                case "7" :
                    System.out.println("Exit, ByeBye");
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Not a valid option, please try again\n");
            };
        }
    }

    private static void bookCar(CarBookingService carBookingService, CarService carService, UserService userService,  Scanner scanner ){
        System.out.println("Book a Car");
        Car[] cars = carService.getAvailableCars();
        int selectedCar;
        int selectedUser;

        if(cars.length == 0) {
            System.out.println("Currently there's no Cars Available");
            return;
        }
        else{
            System.out.println("Select the ID of an available Car");
            for(Car car : cars)
                System.out.println(car.toString());
            selectedCar = scanner.nextInt();
        }
        Car carToBook = carService.getCarByID(selectedCar);
        if(Optional.ofNullable(carToBook).isEmpty()) {
            System.out.println("Selected car is not valid");
            return;
        }

        System.out.println("Select the ID of an existing User");
        for(User user : userService.getAllUsers())
            System.out.println(user.toString());
        selectedUser = scanner.nextInt();
        User userToBook = userService.getUserByID(selectedUser);
        if(Optional.ofNullable(userToBook).isEmpty()) {
            System.out.println("Selected user is not valid");
            return;
        }

        carBookingService.bookACar(carToBook, userToBook);
        System.out.println("User: " + userToBook.toString() +"\nHas successfully booked Car: " + carToBook.toString());

    }
    private static void viewAllUserBookedCars(){
        System.out.println("View All User Booked Cars");
    }

    private static void viewAllBookings(CarBookingService carBookingService){
        System.out.println("View All Bookings");
        for(CarBooking carBooking : carBookingService.getAllBookings()){
            System.out.println(carBooking.toString());
        }
    }

    private static void viewAvailableCars(CarService carService, boolean onlyElectric){
        System.out.println("View All Available Cars ");
        if(onlyElectric){
            Car[] cars = carService.getAvailableElectricCars();
            if(cars.length == 0)
                System.out.println("Electric Cards Not found");
            else{
                for(Car car : cars)
                    System.out.println(car.toString());
            }

        }
        else{
            Car[] cars = carService.getAvailableCars();
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

}