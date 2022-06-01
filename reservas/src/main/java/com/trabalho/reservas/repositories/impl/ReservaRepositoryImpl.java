package com.trabalho.reservas.repositories.impl;

import com.trabalho.reservas.dto.request.ListReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.repositories.ReservaRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
public class ReservaRepositoryImpl implements ReservaRepositoryCustom {

    private final String ID_RECURSO = "idRecurso";
    private final String DATA_INICIO = "data_inicio";
    private final String DATA_FIM = "data_fim";
    private final String ENABLED = "enabled";
    private final String ID_USUARIO = "idUsuario";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Reservation> findReservaByIdRecursoAndDataInicio(String idRecurso, LocalDateTime dataInicio) {
        Criteria criteria = Criteria.where(ID_RECURSO).is(idRecurso)
                .and(ENABLED).is(Boolean.TRUE)
                .and(DATA_INICIO).lte(dataInicio)
                .and(DATA_FIM).gt(dataInicio);

        final Query query = Query.query(criteria);

        return mongoTemplate.find(query, Reservation.class);
    }

    @Override
    public List<Reservation> findAllReservasEnabledWithFilter(ListReservationRequestDTO listarReservasRequestDTO) {
        Criteria criteria = Criteria.where(ENABLED).is(Boolean.TRUE);
        if(nonNull(listarReservasRequestDTO.getIdUsuario())) {
            criteria.and(ID_USUARIO).is(listarReservasRequestDTO.getIdUsuario());
        }

        if(nonNull(listarReservasRequestDTO.getIdRecurso())) {
            criteria.and(ID_RECURSO).is(listarReservasRequestDTO.getIdRecurso());
        }

        if(nonNull(listarReservasRequestDTO.getInitialDate())){
            criteria.and(DATA_INICIO).gte(listarReservasRequestDTO.getInitialDate());
        }

        if(nonNull(listarReservasRequestDTO.getEndDate())){
            criteria.and(DATA_FIM).lte(listarReservasRequestDTO.getEndDate());
        }

        final Query query = Query.query(criteria);

        return mongoTemplate.find(query, Reservation.class);
    }
}
