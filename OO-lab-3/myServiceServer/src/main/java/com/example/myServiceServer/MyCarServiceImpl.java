package com.example.myServiceServer;

import com.example.lib.*;
import com.example.model.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class MyCarServiceImpl extends carServiceGrpc.carServiceImplBase {
    private List<CarJPA> cars = new ArrayList<>(); // Liste des voitures
    Logger logger = LoggerFactory.getLogger(MyCarServiceImpl.class);

    public MyCarServiceImpl() {
        logger.info("Ajout des voitures");
        cars.add(new CarJPA("ABC123", "Toyota", 20000, false, 1));
        cars.add(new CarJPA("XYZ456", "Honda", 22000, true, 2));
    }

    @Override
    public void getCar(GetCarRequest request, StreamObserver<GetCarResponse> responseObserver) {
        logger.info("Trouver la voiture : " + request.getPlateNumber());

        for (CarJPA carJPA : cars) {
            if (carJPA.getPlateNumber().equals(request.getPlateNumber())) {
                // Convertir CarJPA en message Protobuf Car
                Car carProto = CarConverter.carJPAToProtobuf(carJPA);

                GetCarResponse response = GetCarResponse.newBuilder().setCar(carProto).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }
        }

        // Aucune voiture trouvée, envoyer une réponse vide
        StatusRuntimeException exception = Status.NOT_FOUND
                .withDescription("Car with plate number " + request.getPlateNumber() + " not found.")
                .asRuntimeException();

        responseObserver.onError(exception);
    }

    @Override
    public void getAllCars(GetAllCarsRequest request, StreamObserver<GetAllCarsResponse> responseObserver) {
        logger.info("Trouver toutes les voitures");

        GetAllCarsResponse.Builder responseBuilder = GetAllCarsResponse.newBuilder();

        for (CarJPA carJPA : cars) {
            // Convertir chaque CarJPA en message Protobuf Car
            Car carProto = CarConverter.carJPAToProtobuf(carJPA);
            responseBuilder.addCars(carProto);
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void rentCar(putRentCar request, StreamObserver<rentGood> responseObserver) {
        logger.info("Réserve la voiture : " + request.getPlateNumber() + ". Si cela est possible");

        for (CarJPA carJPA : cars) {
            if (carJPA.getPlateNumber().equals(request.getPlateNumber())) {
                if (carJPA.isRented() == request.getNewStateRent()) {
                    StatusRuntimeException exception = Status.FAILED_PRECONDITION
                            .withDescription("La voiture avec le numéro de plaque " + request.getPlateNumber() + " est déjà louée.")
                            .asRuntimeException();
                    responseObserver.onError(exception);
                } else {
                    // La voiture n'est pas encore louée
                    carJPA.setRented(true); // Marquer la voiture comme louée

                    responseObserver.onNext(rentGood.newBuilder().setActualRent(carJPA.isRented()).build());
                    responseObserver.onCompleted();
                }
                return;
            }
        }

        // Aucune voiture trouvée, envoyer une réponse vide
        StatusRuntimeException exception = Status.NOT_FOUND
                .withDescription("Car with plate number " + request.getPlateNumber() + " not found.")
                .asRuntimeException();

        responseObserver.onError(exception);
        return;
    }
}
