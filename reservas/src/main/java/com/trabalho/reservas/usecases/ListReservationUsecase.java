package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.LessonDTO;
import com.trabalho.reservas.dto.ReservaDTO;
import com.trabalho.reservas.dto.ResourceDTO;
import com.trabalho.reservas.dto.UserDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import com.trabalho.reservas.exceptions.RecursoNaoEncontradoException;
import com.trabalho.reservas.feing.LessonFeign;
import com.trabalho.reservas.feing.ResourcesFeign;
import com.trabalho.reservas.feing.UserFeign;
import com.trabalho.reservas.repositories.ReservationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListReservationUsecase {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LessonFeign lessonFeign;

    @Autowired
    private ResourcesFeign resourcesFeign;

    @Autowired
    private UserFeign userFeign;

    public ReservaDTO execute(String id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Reservation not found");
        }

        Reservation reservation = reservationOptional.get();
        return mapReservaToReservaDTO(reservation);
    }

    private ReservaDTO mapReservaToReservaDTO(Reservation reservation) {

        LessonDTO aulaDTO = lessonFeign.getClasses(reservation.getLessonId());
        ResourceDTO recursoDTO = resourcesFeign.getResources(reservation.getResourceId());
        UserDTO usuarioDTO = userFeign.getUsuario(reservation.getUserId());

        ReservaDTO dto = new ReservaDTO();
        BeanUtils.copyProperties(reservation, dto);
        dto.setAula(aulaDTO);
        dto.setRecurso(recursoDTO);
        dto.setUsuario(usuarioDTO);
        return dto;
    }
}
