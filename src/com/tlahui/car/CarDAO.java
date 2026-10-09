package com.tlahui.car;

import java.math.BigDecimal;

public class CarDAO {
    private static final Car[] carsSeedDataArray = {
            new Car(1,"9H19CY90AN", new BigDecimal("100000.00"), Brand.CHEVROLET, false),
            new Car(2,"VMUSJSQBES", new BigDecimal("50000.00"), Brand.FORD, false),
            new Car(3,"PXW7E38KO2", new BigDecimal("150000.00"), Brand.BYD, true),
            new Car(4,"RV6KV1NM5L", new BigDecimal("80000.00"), Brand.VOLKSWAGEN, false),
            new Car(5,"PCX1E4MLAZ", new BigDecimal("200000.00"), Brand.TOYOTA, true),
    };

    public Car[] getAllCars() {
        return carsSeedDataArray;
    }
}
