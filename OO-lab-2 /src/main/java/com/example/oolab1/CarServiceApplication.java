package com.example.oolab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.example.oolab1.service.CarRentalWebService;
import com.example.oolab1.repository.CarRepository;


@SpringBootApplication
public class CarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

/* @Bean
    public CommandLineRunner demo(CarRentalWebService carRentalService) {
        return (args) -> {
            Car car1 = new Car("11AA22", "Ferrari", 1000, false);
            carRentalService.addCar(car1);

            Car car2 = new Car("22BB44", "Porsche", 2000, false);
            carRentalService.addCar(car2);
        };
    }*/
    @Bean
    public CommandLineRunner demo(CarRepository repository) {
        return (args) -> {
            repository.save(new Car("11AA22", "Ferrari", 1000, false));
            repository.save(new Car("22BB44", "Porsche", 2000, false));
        };
    }
}



