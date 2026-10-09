package com.tlahui;

import com.tlahui.booking.CarBookingService;
import com.tlahui.car.Car;
import com.tlahui.car.CarService;
import com.tlahui.user.User;
import com.tlahui.user.UserService;

import java.lang.reflect.Array;
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
                            "1.Book Car\n"+
                            "2. View All User Booked Cars\n"+
                            "3. View All Bookings\n"+
                            "4. View All Available Cars\n"+
                            "5. View all Available Electric Cars\n"+
                            "6. View all users\n"+
                            "7. Exit\n\n"+
                            "Select the number of an option: "
                            );

            switch (scanner.nextLine()){
                case "1" : bookCar();
                    break;
                case "2" : viewAllUserBookedCars();
                    break;
                case "3" : viewAllBookings();
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

    private static void bookCar(){
        System.out.println("Book a Car");
    }
    private static void viewAllUserBookedCars(){
        System.out.println("View All User Booked Cars");
    }

    private static void viewAllBookings(){
        System.out.println("View All Bookings");
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