package com.trabalho.reservas.repositories.impl;

import com.trabalho.reservas.dto.ResourceDTO;
import com.trabalho.reservas.dto.request.ListReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.feing.ResourcesFeign;
import com.trabalho.reservas.repositories.ReservationRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Repository
public class ReservaRepositoryImpl implements ReservationRepositoryCustom {

    private final String RESOURCE_ID = "resourceId";
    private final String START_DATE = "startDate";
    private final String END_DATE = "endDate";
    private final String ENABLED = "enabled";
    private final String USER_ID = "userId";

    @Autowired
    private ResourcesFeign resourcesFeign;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Reservation> findReservaByResourceIdAndStartDate(String idRecurso, LocalDateTime dataInicio) {
        Criteria criteria = Criteria.where(RESOURCE_ID).is(idRecurso)
                .and(ENABLED).is(Boolean.TRUE)
                .and(START_DATE).lte(dataInicio)
                .and(END_DATE).gt(dataInicio);

        final Query query = Query.query(criteria);

        return mongoTemplate.find(query, Reservation.class);
    }

    @Override
    public List<Reservation> findAllReservasEnabledWithFilter(ListReservationRequestDTO listarReservasRequestDTO) {
        Criteria criteria = Criteria.where(ENABLED).is(Boolean.TRUE);
        if (nonNull(listarReservasRequestDTO.getUserId())) {
            criteria.and(USER_ID).is(listarReservasRequestDTO.getUserId());
        }

        if (nonNull(listarReservasRequestDTO.getResourcesType())) {
            //TODO busca na api de resources o id do tipo de recurso
            List<ResourceDTO> resourceDTOS = resourcesFeign.getResourcesByType(listarReservasRequestDTO.getResourcesType());
            //TODO adiciona no criteria os ids do recurso
            List<String> resourcesIds = resourceDTOS.stream().map(ResourceDTO::getId).collect(Collectors.toList());
            criteria.and(RESOURCE_ID).in(resourcesIds);
        }

        if (nonNull(listarReservasRequestDTO.getStartDate())) {
            criteria.and(START_DATE).gte(listarReservasRequestDTO.getStartDate());
        }

        if (nonNull(listarReservasRequestDTO.getEndDate())) {
            criteria.and(END_DATE).lte(listarReservasRequestDTO.getEndDate());
        }

        final Query query = Query.query(criteria);

        return mongoTemplate.find(query, Reservation.class);
    }
}
