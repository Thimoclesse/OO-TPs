package com.example.oolab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
public class HelloService {

    private final Logger logger = LoggerFactory.getLogger(HelloService.class);
    private final CarService carService;

    public HelloService(CarService carService) {
        this.carService = carService;
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode().value(), ex.getReason());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }


    // Default endpoint
    @GetMapping({"/"})
    public String hello() {
        return "Hello, welcome";
    }

    // List all cars
    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listOfCars() {
        return carService.getCars();
    }

    // Get a specific car by plate number
    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car aCar(@PathVariable("plateNumber") String plateNumber) {
        Car car = carService.getCarByPlateNumber(plateNumber);
        if (car == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found with plate number: " + plateNumber);
        }
        return car;
    }

    // Rent or return a car
    @PutMapping(value = "/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> rentOrGetBack(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent", required = true) boolean rent,
            @RequestBody RentRequest rentRequest) throws CarNotFoundException {

        try {
            carService.rentOrReturnCar(plateNumber, rent, rentRequest);
            Car car = carService.getCarByPlateNumber(plateNumber);

            if (rent) {
                logger.info("Renting car with plate number: " + plateNumber);
                logger.info(car.getBeggin_date());
                logger.info(car.getEnd_date());
                return ResponseEntity.ok("Renting car with plate number: " + plateNumber + "\n" +
                        "Begin date: " + car.getBeggin_date() + "\n" +
                        "End date: " + car.getEnd_date());

            } else {
                logger.info("Freeing car with plate number: " + plateNumber);
                logger.info(car.getBeggin_date());
                logger.info(car.getEnd_date());
                return ResponseEntity.ok("Freeing car with plate number: " + plateNumber + "\n" +
                        "Begin date: " + car.getBeggin_date() + "\n" +
                        "End date: " + car.getEnd_date());
            }
        } catch (ResponseStatusException e) {
            logger.error("Error: " + e.getReason());
            throw e;
        }

    }
}
