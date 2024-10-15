package com.example.myServiceClient;

import com.example.model.CarJPA;
import com.example.myServiceClient.service.CarService;
import com.example.myServiceClient.service.MyService;
import com.example.myServiceClient.web.WebService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebService.class) // Indiquer quel contrôleur REST est testé
class WebServiceTest {

    @Autowired
    private MockMvc mockMvc;  // Simulateur des requêtes HTTP

    @MockBean
    private MyService myService;

    @MockBean
    private CarService carService;

    @Test
    void testSayHello() throws Exception {
        when(myService.sayHello()).thenReturn("Hello World");

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    void testAllCars() throws Exception {
        CarJPA car1 = new CarJPA("ABC123", "Toyota", 20000, false, 1);
        CarJPA car2 = new CarJPA("XYZ789", "Honda", 15000, true, 2);

        // Simuler la réponse du service `CarService`
        when(carService.allCars()).thenReturn(List.of(car1, car2));

        // Vérification de la réponse JSON avec `MockMvc`
        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))  // Vérifier la longueur du tableau JSON
                .andExpect(jsonPath("$[0].plateNumber").value("ABC123"))  // Vérifier la première voiture
                .andExpect(jsonPath("$[1].plateNumber").value("XYZ789"));  // Vérifier la deuxième voiture
    }

    @Test
    void testGetCarById() throws Exception {
        CarJPA car = new CarJPA("ABC123", "Toyota", 20000, false, 1);

        // Simuler la réponse du service `CarService`
        when(carService.getCarById("ABC123")).thenReturn(car);

        // Test de la requête GET pour récupérer une voiture par son ID
        mockMvc.perform(get("/cars/ABC123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plateNumber").value("ABC123"))
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.price").value(20000))
                .andExpect(jsonPath("$.rented").value(false))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testPostCarRent() throws Exception {
        // Simuler la réponse du service `CarService`
        when(carService.postRentCar("ABC123", true)).thenReturn("{\"Actual_Rent\": true}");

        // Test de la requête PUT pour mettre à jour l'état de location d'une voiture
        mockMvc.perform(put("/cars/ABC123")
                        .param("rent", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Actual_Rent").value(true));
    }
}


