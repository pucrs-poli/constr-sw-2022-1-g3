package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.ReservaDTO;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import org.springframework.stereotype.Service;

@Service
public class ListReservationUsecase {

    public ReservaDTO execute(String id) {
        throw new ErroNegocioException("METODO NAO IMPLEMENTADO");
    }

}
