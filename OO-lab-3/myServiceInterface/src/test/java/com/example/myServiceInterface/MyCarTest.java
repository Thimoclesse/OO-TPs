package com.example.myServiceInterface;

import com.example.lib.*;
import com.example.model.CarJPA;
import com.example.myServiceClient.service.CarService;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyCarTest {

    @Mock
    private carServiceGrpc.carServiceBlockingStub carServiceStub;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllCars() {
        GetAllCarsResponse mockResponse = mock(GetAllCarsResponse.class);
        when(mockResponse.getCarsList()).thenReturn(List.of());  // Simuler une liste vide
        when(carServiceStub.getAllCars(any())).thenReturn(mockResponse);

        List<CarJPA> cars = carService.allCars();
        assertNotNull(cars);
        assertEquals(0, cars.size());  // Attendre une liste vide
    }

    @Test
    void testGetCarByIdSuccess() {
        GetCarResponse mockResponse = mock(GetCarResponse.class);
        Car mockCar = Car.newBuilder().setPlateNumber("ABC123").build();
        when(mockResponse.getCar()).thenReturn(mockCar);
        when(carServiceStub.getCar(any())).thenReturn(mockResponse);

        CarJPA car = carService.getCarById("ABC123");
        assertNotNull(car);
        assertEquals("ABC123", car.getPlateNumber());
    }

    @Test
    void testGetCarByIdNotFound() {
        when(carServiceStub.getCar(any())).thenThrow(new StatusRuntimeException(Status.NOT_FOUND));

        CarJPA car = carService.getCarById("XYZ789");
        assertNull(car);  // Attendre que null soit retourné pour une voiture non trouvée
    }

    @Test
    void testPostRentCarSuccess() {
        rentGood mockResponse = mock(rentGood.class);
        when(mockResponse.getActualRent()).thenReturn(true);  // Simuler que la voiture a été louée
        when(carServiceStub.rentCar(any())).thenReturn(mockResponse);

        String result = carService.postRentCar("ABC123", true);
        assertNotNull(result);
        assertTrue(result.contains("Actual_Rent"));
    }

    @Test
    void testPostRentCarFailed() {
        when(carServiceStub.rentCar(any())).thenThrow(new StatusRuntimeException(Status.FAILED_PRECONDITION));

        String result = carService.postRentCar("ABC123", false);
        assertNotNull(result);
        assertTrue(result.contains("Erreur"));
    }
}
