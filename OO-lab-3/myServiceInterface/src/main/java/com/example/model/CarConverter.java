package com.example.model;
import com.example.lib.Car;

public class CarConverter {

    // Convertir une entité CarJPA en un message Protobuf Car
    public static Car carJPAToProtobuf(CarJPA carJPA) {
        return Car.newBuilder()
                .setPlateNumber(carJPA.getPlateNumber())
                .setBrand(carJPA.getBrand())
                .setPrice(carJPA.getPrice())
                .setRented(carJPA.isRented())
                .setId(carJPA.getId())
                .build();
    }

    // Convertir un message Protobuf Car en une entité CarJPA
    public static CarJPA protobufToCarJPA(Car car) {
        return new CarJPA(
                car.getPlateNumber(),
                car.getBrand(),
                car.getPrice(),
                car.getRented(),
                car.getId()
        );
    }
}

