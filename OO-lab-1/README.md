# Lab 1 - Car Rental Application

## Overview
This project is a simple Car Rental Application. It allows you to view, rent, and unrent cars through HTTP requests.

## Getting Started
To run the application, follow these steps:

### 1. Launch the Application
Run the **`OoLab1Application`** located in the following directory:
src/java/com/example/oolab1

### 2. Access the Web Interface
Once the application is running, open your web browser and go to:
http://localhost:8080

You will be greeted by a welcome message.

## API Endpoints

### 1. Get All Cars
To view all the available cars, make a **GET** request to the following endpoint:
http://localhost:8080/cars

### 2. Check if a Car is Rented
Each car has a unique license plate number. The following license plates are available:

- `11AA22`
- `22BB33`
- `33CC44`

To check if a car is rented, use the following **GET** request:
http://localhost:8080/cars/{licensePlate}?rent=true

Replace `{licensePlate}` with the desired car's license plate (e.g., `11AA22`).

### 3. Rent a Car
To rent a car, use the following **POST** request:
http://localhost:8080/cars/{licensePlate}?rent=true

Again, replace `{licensePlate}` with the actual plate number (e.g., `11AA22`).

### 4. Unrent a Car
To unrent a car, use the following **POST** request:
http://localhost:8080/cars/{licensePlate}?rent=false

Replace `{licensePlate}` with the car's plate (e.g., `11AA22`).

## Using Postman
You can also use **Postman** or any other API client to send these requests. Simply enter the endpoints into the request bar and use the **GET** or **POST** methods accordingly.

## Conclusion
This simple lab demonstrates basic RESTful web service functionalities for managing car rentals. Use the provided endpoints to interact with the service and manage the rental state of each car.