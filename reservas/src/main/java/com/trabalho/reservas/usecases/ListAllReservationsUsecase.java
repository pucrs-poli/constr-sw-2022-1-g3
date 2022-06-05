package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.LessonDTO;
import com.trabalho.reservas.dto.ResourceDTO;
import com.trabalho.reservas.dto.ReservationDTO;
import com.trabalho.reservas.dto.UserDTO;
import com.trabalho.reservas.dto.request.ListReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.feing.LessonFeign;
import com.trabalho.reservas.feing.ResourcesFeign;
import com.trabalho.reservas.feing.UserFeign;
import com.trabalho.reservas.mapper.ReservationToReservationDTOMapper;
import com.trabalho.reservas.repositories.ReservationRepository;
import com.trabalho.reservas.repositories.ReservationRepositoryCustom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListAllReservationsUsecase {

    @Autowired
    private ReservationRepository reservaRepository;

    @Autowired
    private ReservationRepositoryCustom reservationRepositoryCustom;

    @Autowired
    private ReservationToReservationDTOMapper mapper;

    public List<ReservationDTO> execute(ListReservationRequestDTO listarReservasRequestDTO) {
        List<Reservation> reservationsList = reservationRepositoryCustom.findAllReservasEnabledWithFilter(listarReservasRequestDTO);

        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservationsList) {
            reservationDTOS.add(mapper.mapper(reservation));
        }
        return reservationDTOS;
    }

}
