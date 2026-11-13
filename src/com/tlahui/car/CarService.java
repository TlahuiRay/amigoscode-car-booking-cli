package com.tlahui.car;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarService {

    private ICarDAO carDAO;

    public CarService(ICarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAvailableCars(List<Car> bookedCars){

        boolean booked;
        List<Car> availableCars = new ArrayList<>();

        for (Car car : carDAO.getCars()) {
            booked = false;
            for (Car bookedCar : bookedCars) {
                if (!bookedCar.equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                availableCars.add(car);
            }
        }

        return availableCars;
    }
    public List<Car> getAvailableElectricCars(List<Car> bookedCars){
        List<Car> availableCars = getAvailableCars(bookedCars);
        List<Car> eCars = new ArrayList<>();

        for(Car c : availableCars){
            if(c.isElectric)
                eCars.add(c);
        }
        return eCars;
    }

    public Car getCarByID(UUID id){
        for(Car car : carDAO.getCars()){
            if(car.getId().equals(id))
                return car;
        }
        return null;
    }
}
