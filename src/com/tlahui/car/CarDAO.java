package com.tlahui.car;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDAO {
    private static final Car[] carsSeedDataArray = {
            new Car(UUID.fromString("5a6ba841-2b8b-473c-b862-5e7264bee505"),"9H19CY90AN", 100000.00, Brand.CHEVROLET, false),
            new Car(UUID.fromString("66693ecb-fb5d-41da-b36b-0f3c025d219e"),"VMUSJSQBES", 50000.00, Brand.FORD, false),
            new Car(UUID.fromString("3a7cabad-f6e2-48af-b468-0391ec172822"),"PXW7E38KO2", 150000.00, Brand.BYD, true),
            new Car(UUID.fromString("8e6101f9-bebc-44fe-acbb-63d6085f8587"),"RV6KV1NM5L", 80000.00, Brand.VOLKSWAGEN, false),
            new Car(UUID.fromString("97319d39-1930-4404-9f45-583b58800278"),"PCX1E4MLAZ", 200000.00, Brand.TOYOTA, true),
    };

    public Car[] getAllCars() {
        return carsSeedDataArray;
    }
}
