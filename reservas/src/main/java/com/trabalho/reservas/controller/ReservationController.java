package com.trabalho.reservas.controller;

import com.trabalho.reservas.dto.ErrorDTO;
import com.trabalho.reservas.dto.ReservationDTO;
import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.dto.request.ListReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.usecases.*;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservations")
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

    @Autowired
    private DeleteReservationsByResourceUsecase deleteReservationsByResourceUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(
            @ApiResponse(code = 400, message = "Bad Request")
    )
    public Reservation createReservation(
            @RequestBody CreateReservationRequestDTO reservationRequestDTO
    ) {
        return createReservationUsecase.execute(reservationRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    )
    public List<ReservationDTO> listAllReservations(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDate,
            @RequestParam(required = false) String resourcesType,
            @RequestParam(required = false) String userId
    ) {
        ListReservationRequestDTO listReservationRequestDTO = ListReservationRequestDTO.builder()
                .endDate(endDate)
                .startDate(startDate)
                .resourcesType(resourcesType)
                .userId(userId)
                .build();
        return listAllReservationsUsecase.execute(listReservationRequestDTO);
    }

    @DeleteMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public void deleteReservation(
            @PathVariable String reservationId
    ) {
        deleteReservationUsecase.execute(reservationId);
    }

    @GetMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ReservationDTO listReservation(
            @PathVariable String reservationId
    ) {
        return listReservationUsecase.execute(reservationId);
    }

    @PutMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public void updateReservation(
            @PathVariable String reservationId,
            @RequestBody CreateReservationRequestDTO reservaRequestDTO
    ) {
        updateReservationUsecase.execute(reservationId, reservaRequestDTO);
    }

    @PatchMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public void updatePartialReservation(
            @PathVariable String reservationId,
            @RequestBody CreateReservationRequestDTO reservaRequestDTO
    ) {
        updatePartialReservationUsecase.execute(reservationId, reservaRequestDTO);
    }

    @DeleteMapping("{resourceId}/resources")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservations(@PathVariable String resourceId) {
        deleteReservationsByResourceUsecase.execute(resourceId);
    }
}
