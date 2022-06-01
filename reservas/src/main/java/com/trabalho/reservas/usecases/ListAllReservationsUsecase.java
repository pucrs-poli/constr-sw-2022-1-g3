package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.AulaDTO;
import com.trabalho.reservas.dto.RecursoDTO;
import com.trabalho.reservas.dto.ReservaDTO;
import com.trabalho.reservas.dto.UsuarioDTO;
import com.trabalho.reservas.dto.request.ListReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.feing.AulaFeign;
import com.trabalho.reservas.feing.RecursoFeign;
import com.trabalho.reservas.feing.UsuarioFeign;
import com.trabalho.reservas.repositories.ReservaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListAllReservationsUsecase {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AulaFeign aulaFeign;

    @Autowired
    private RecursoFeign recursoFeign;

    @Autowired
    private UsuarioFeign usuarioFeign;

    public List<ReservaDTO> execute(ListReservationRequestDTO listarReservasRequestDTO) {
        List<Reservation> reservas = reservaRepository.findAll();
        List<ReservaDTO> reservasDTO = mapReservaToReservaDTO(reservas);

        return reservasDTO;
    }

    private List<ReservaDTO> mapReservaToReservaDTO(List<Reservation> reservas) {
        List<ReservaDTO> reservasDTO = new ArrayList<>();


        for (Reservation reserva : reservas) {
            AulaDTO aulaDTO = aulaFeign.getAulas(reserva.getIdAula());
            RecursoDTO recursoDTO = recursoFeign.getRecurso(reserva.getIdRecurso());
            UsuarioDTO usuarioDTO = usuarioFeign.getUsuario(reserva.getIdUsuario());

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
