package com.tlahui.car;

import com.tlahui.booking.CarBookingDAO;

public class CarService {

    CarDAO carDAO = new CarDAO();

    public Car[] getAvailableCars(){
        return carDAO.getAllCars();
    }
    public Car[] getAvailableElectricCars(){
        int electricCarsCount = 0;
        Car[] allCars = carDAO.getAllCars();

        for(Car car : allCars){
            if(car.isElectric)
                electricCarsCount++;
        }

        Car[] electricCars = new Car[electricCarsCount];
        int j = 0;
        for(int i = 0; i < allCars.length; i++){
            if(allCars[i].isElectric){
                electricCars[j] = allCars[i];
                j++;
            }

        }
        return electricCars;
    }

}
