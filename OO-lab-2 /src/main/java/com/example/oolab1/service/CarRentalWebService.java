package com.example.oolab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oolab1.repository.CarRepository;
import com.example.oolab1.Car;
import java.util.List;
import java.util.Optional;

@Service
public class CarRentalWebService {

    private final CarRepository carRepository;

    @Autowired
    public CarRentalWebService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsByPlateNumber(String plateNumber) {
        return carRepository.findByPlateNumber(plateNumber);
    }

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
