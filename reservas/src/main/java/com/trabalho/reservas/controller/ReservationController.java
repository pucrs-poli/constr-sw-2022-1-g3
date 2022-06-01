package com.trabalho.reservas.controller;

import com.trabalho.reservas.dto.ReservaDTO;
import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.dto.request.ListReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.usecases.*;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private CreateReservationUsecase createReservationUsecase;

    @Autowired
    private ListAllReservationsUsecase listAllReservationsUsecase;

    @Autowired
    private DeleteReservationUsecase deleteReservationUsecase;

    @Autowired
    private ListReservationUsecase listReservationUsecase;

    @Autowired
    private UpdateReservationUsecase updateReservationUsecase;

    @Autowired
    private UpdatePartialReservationUsecase updatePartialReservationUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(
            @RequestBody CreateReservationRequestDTO reservationRequestDTO
    ) {
        return createReservationUsecase.execute(reservationRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public List<ReservaDTO> listAllReservations(
            ListReservationRequestDTO listarReservasRequestDTO
    ) {
        return listAllReservationsUsecase.execute(listarReservasRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public void deleteReservation(
            @PathVariable String id
    ) {
        deleteReservationUsecase.execute(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservaDTO listReservation(
            @PathVariable String id
    ) {
        return listReservationUsecase.execute(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReservation(
            @PathVariable String id,
            @RequestBody CreateReservationRequestDTO reservaRequestDTO
    ){
        updateReservationUsecase.execute(id, reservaRequestDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePartialReservation(
            @PathVariable String id,
            @RequestBody CreateReservationRequestDTO reservaRequestDTO
    ){
        updatePartialReservationUsecase.execute(id, reservaRequestDTO);
    }


}
