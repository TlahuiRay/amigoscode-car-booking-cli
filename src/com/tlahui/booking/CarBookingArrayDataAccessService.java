///*
//* DEPRECATED
//* */
//
//package com.tlahui.booking;
//
//import com.tlahui.car.Car;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardOpenOption;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//public class CarBookingArrayDataAccessService implements ICarBookingDAO {
//    private static CarBooking[] carBookingsArray;
//    private static final Path FILE_PATH = Path.of("src","com","tlahui","bookings.ser");
//    private CarBookingFileDataAccessService bookingFileDAS = new CarBookingFileDataAccessService();
//
//    static {
//        //We initialize the bookings array from the file
//        if(Files.exists(FILE_PATH)){
//            //First we count the total number of objects serialized in the file
//            int count = 0;
//            try (ObjectInputStream in = new ObjectInputStream(
//                    Files.newInputStream(FILE_PATH, StandardOpenOption.READ))) {
//
//                while (true) {
//                    in.readObject();
//                    count++;
//                }
//            } catch (EOFException eof) {
//                //End of File : exit condition , not sure if this is a good practice
//            } catch (IOException | ClassNotFoundException e) {
//                System.out.println("Failed to read bookings file : " + e.getMessage());
//            }
//            //We initialize our array according to the total number of objects found in the file
//            carBookingsArray = new CarBooking[count];
//
//            //We read the file again and fill the array with the content
//            if (count > 0) {
//                try (ObjectInputStream in = new ObjectInputStream(
//                        Files.newInputStream(FILE_PATH, StandardOpenOption.READ))) {
//                    for (int i = 0; i < count; i++) {
//                        carBookingsArray[i] = (CarBooking) in.readObject();
//                    }
//                } catch (IOException | ClassNotFoundException e) {
//                    System.out.println("Failed to read bookings file : " + e.getMessage());
//                }
//            }
//        } else { //If file doesnt exist we initialize an empty array
//            carBookingsArray = new CarBooking[0];
//        }
//    }
//
//    public CarBooking[] getCarBookings() {
//        return carBookingsArray;
//    }
//
//    public void createBooking(CarBooking booking){
//
//        //Extend the array by +1 each time a new record is inserted
//        CarBooking[] carBookingsArrayExtended = new CarBooking[carBookingsArray.length+1];
//        for(int i = 0; i <carBookingsArray.length; i++){
//            carBookingsArrayExtended[i] = carBookingsArray[i];
//        }
//        //Rewrite the original array with the new extended array
//        carBookingsArray = Arrays.copyOf(carBookingsArrayExtended, carBookingsArrayExtended.length);
//
//        //Insert the new record in the last position
//        carBookingsArray[carBookingsArray.length-1] = booking;
//
//        //Save current state in the file after inserting a new record in the array
//        try {
//            writeToFile(carBookingsArray);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public boolean deleteBooking(CarBooking carBooking){
//        //Delete the booking by making the existing record equal to null
//        for(int i = 0; i < carBookingsArray.length; i++){
//            if(carBookingsArray[i].equals(carBooking)){
//                carBookingsArray[i] = null;
//                break;
//            }
//        }
//        //Reduce the array by -1 each time a booking is deleted and copy it into the temporal reduced array
//        int index = 0;
//        CarBooking[] carBookingsArrayReduced = new CarBooking[carBookingsArray.length-1];
//        for(int i = 0; i <carBookingsArray.length; i++){
//            if(carBookingsArray[i]!=null){
//                carBookingsArrayReduced[index] = carBookingsArray[i];
//                index++;
//            }
//        }
//
//        //Rewrite the original array with the new temporal reduced array
//        carBookingsArray = Arrays.copyOf(carBookingsArrayReduced, carBookingsArrayReduced.length);
//
//        //Save current state in the file after deleting a record in the array
//        try {
//            writeToFile(carBookingsArray);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }
//
//    public CarBooking[] getBookingByUser(UUID selectedUserID){
//        CarBooking[] bookings; //Array should be initialized empty
//        int recordCount = 0; //we will use this variable to count the total occurrences of the users we're searching.
//        for(int i = 0; i < carBookingsArray.length; i++){
//            if(carBookingsArray[i].getUser().getId().equals(selectedUserID)){
//                recordCount++;
//            }
//        }
//        bookings = new CarBooking[recordCount]; //this will help us to define the size of the array we're going to return
//        int index = 0;
//        for(int i = 0; i < carBookingsArray.length; i++) {
//            if(carBookingsArray[i].getUser().getId().equals(selectedUserID)){
//                bookings[index] = carBookingsArray[i];
//                index++;
//            }
//        }
//
//        return bookings;
//    }
//
//    public List<Car> getAllBookedCars() {
//        List<Car> bookedCars = new ArrayList<>();
//        for(int i = 0; i < carBookingsArray.length; i++){
//            bookedCars.add(carBookingsArray[i].getCar());
//        }
//        return bookedCars;
//    }
//
//    public static void writeToFile(CarBooking[] carBookingsArray) throws IOException {
//        if (Files.notExists(FILE_PATH)) {
//            Files.createFile(FILE_PATH);
//        }
//        Files.write(FILE_PATH, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
//        try (ObjectOutputStream out = new ObjectOutputStream(
//                Files.newOutputStream(FILE_PATH, StandardOpenOption.WRITE))) {
//            for (int i = 0; i < carBookingsArray.length; i++) {
//                if (carBookingsArray[i] != null) {
//                    out.writeObject(carBookingsArray[i]);
//                }
//            }
//            out.flush();
//        }
//    }
//}
