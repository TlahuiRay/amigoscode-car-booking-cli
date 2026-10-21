package com.tlahui.car;

public class CarService {

    CarDAO carDAO = new CarDAO();

    public Car[] getAvailableCars(Car[] bookedCars){

        int availableCarsCount = 0;
        boolean booked;
        //First you need to get the total count of available cars, compare all cars vs booked cars
        for (Car car : carDAO.getAllCars()) {
            booked = false;
            for (Car bookedCar : bookedCars) {
                if (!bookedCar.equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                ++availableCarsCount;
            }
        }

        //Create a new array with the size of the total of the available cars
        Car[] availableCars = new Car[availableCarsCount];
        int index = 0;

        //Create an array containing all the available cars
        for (Car car : carDAO.getAllCars()) {
            booked = false;
            for (Car bookedCar : bookedCars) {
                if (!bookedCar.equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                availableCars[index++] = car;
            }
        }

        return availableCars;
    }
    public Car[] getAvailableElectricCars(Car[] bookedCars){
        //As stated by Ian, no need to duplicate code, we can reuse our first method getAvailableCars
        Car[] availableCars = getAvailableCars(bookedCars);
        int electricCarsCount = 0;

        for(Car car : availableCars){
            if(car.isElectric)
                electricCarsCount++;
        }

        Car[] electricCars = new Car[electricCarsCount];
        int j = 0;
        for(int i = 0; i < availableCars.length; i++){
            if(availableCars[i].isElectric){
                electricCars[j] = availableCars[i];
                j++;
            }

        }
        return electricCars;
    }

    public Car getCarByID(int id){
        for(Car car : carDAO.getAllCars()){
            if(car.getId() == id)
                return car;
        }
        return null;

    }

}
