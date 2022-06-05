package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.LessonDTO;
import com.trabalho.reservas.dto.ResourceDTO;
import com.trabalho.reservas.dto.ReservaDTO;
import com.trabalho.reservas.dto.UserDTO;
import com.trabalho.reservas.dto.request.ListReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.feing.LessonFeign;
import com.trabalho.reservas.feing.ResourcesFeign;
import com.trabalho.reservas.feing.UserFeign;
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
    private LessonFeign aulaFeign;

    @Autowired
    private ResourcesFeign recursoFeign;

    @Autowired
    private UserFeign usuarioFeign;

    public List<ReservaDTO> execute(ListReservationRequestDTO listarReservasRequestDTO) {
        List<Reservation> reservas = reservationRepositoryCustom.findAllReservasEnabledWithFilter(listarReservasRequestDTO);
        List<ReservaDTO> reservasDTO = mapReservaToReservaDTO(reservas);

        return reservasDTO;
    }

    private List<ReservaDTO> mapReservaToReservaDTO(List<Reservation> reservas) {
        List<ReservaDTO> reservasDTO = new ArrayList<>();


        for (Reservation reserva : reservas) {
            LessonDTO aulaDTO = aulaFeign.getClasses(reserva.getLessonId());
            ResourceDTO recursoDTO = recursoFeign.getResources(reserva.getResourceId());
            UserDTO usuarioDTO = usuarioFeign.getUsuario(reserva.getUserId());

            ReservaDTO dto = new ReservaDTO();
            BeanUtils.copyProperties(reserva, dto);
            dto.setAula(aulaDTO);
            dto.setRecurso(recursoDTO);
            dto.setUsuario(usuarioDTO);
            reservasDTO.add(dto);
        }
        return reservasDTO;
    }
}
