package com.tlahui.car;

import java.math.BigDecimal;

public class CarDAO {
    private static final Car[] carsArray = {
            new Car("1", new BigDecimal("100000.00"), Brand.CHEVROLET, false),
            new Car("2", new BigDecimal("50000.00"), Brand.FORD, false),
            new Car("3", new BigDecimal("150000.00"), Brand.BYD, true),
            new Car("4", new BigDecimal("80000.00"), Brand.VOLKSWAGEN, false),
            new Car("5", new BigDecimal("200000.00"), Brand.TOYOTA, true),
    };

    public Car[] getAllCars() {
        return carsArray;
    }
}
