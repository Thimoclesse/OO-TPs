package com.example.oolab1;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private List<Car> cars;

    public CarService() {
        this.cars = List.of(
                new Car("11AA22", "Ferrari", 488, false),
                new Car("22BB33", "Lamborghini", 567, false),
                new Car("33CC44", "Porsche", 911, true)
        );
    }

    public List<Car> getCars() {
        return cars;
    }

    public Car getCarByPlateNumber(String plateNumber) {
        for (Car c : cars) {
            if (plateNumber.equals(c.getPlateNumber())) {
                return c;
            }
        }
        return null;
    }

    public void rentOrReturnCar(String plateNumber, boolean rent, RentRequest rentRequest) throws CarNotFoundException {
        Car car = getCarByPlateNumber(plateNumber);

        if (car == null) {
            throw new CarNotFoundException("Car not found with plate number: " + plateNumber);
        }

        if (rent) {
            if (car.isRent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is already rented");
            }
            car.setRent(true);
            car.setBegin(rentRequest.getBegin());
            car.setEnding(rentRequest.getEnd());
        } else {
            if (!car.isRent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is already available");
            }
            car.setRent(false);
            car.setBegin("xx/xx/xxxx");
            car.setEnding("xx/xx/xxxx");
        }
    }
}
