package com.trabalho.reservas.repositories.impl;

import com.trabalho.reservas.entities.Reserva;
import com.trabalho.reservas.repositories.ReservaRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservaRepositoryImpl implements ReservaRepositoryCustom {

    private final String ID_RECURSO = "idRecurso";
    private final String DATA_INICIO = "data_inicio";
    private final String DATA_FIM = "data_fim";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Reserva> findReservaByIdRecursoAndDataInicio(String idRecurso, LocalDateTime dataInicio) {
        Criteria criteria = Criteria.where(ID_RECURSO).is(idRecurso)
                .and(DATA_INICIO).lte(dataInicio)
                .and(DATA_FIM).gt(dataInicio);

        final Query query = Query.query(criteria);

        return mongoTemplate.find(query, Reserva.class);
    }
}
