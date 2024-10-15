package com.example.oolab1.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.example.oolab1.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
    // Méthode pour trouver une voiture par son numéro de plaque
    List<Car> findByPlateNumber(String plateNumber);
}


