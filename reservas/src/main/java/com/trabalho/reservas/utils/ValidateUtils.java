package com.trabalho.reservas.utils;

import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Component
public class ValidateUtils {
    public void validateRequestBody(CreateReservationRequestDTO reservationRequestDTO) {

        if (isNull(reservationRequestDTO.getStartDate())) {
            throw new ErroNegocioException("startDate should not be null!");
        }
        if (isNull(reservationRequestDTO.getEndDate())) {
            throw new ErroNegocioException("endDate should not be null!");
        }
        if (isNull(reservationRequestDTO.getResourceId())) {
            throw new ErroNegocioException("resourceId should not be null!");
        }
        if (isNull(reservationRequestDTO.getUserId())) {
            throw new ErroNegocioException("userId should not be null!");
        }
        if (isNull(reservationRequestDTO.getLessonId())) {
            throw new ErroNegocioException("lessonId should not be null!");
        }
    }

    public void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isBefore(LocalDateTime.now())) {
            throw new ErroNegocioException("startDate must be greater than now");
        }

        if (endDate.isBefore(startDate)) {
            throw new ErroNegocioException("endDate must be greater than startDate");
        }
    }
}
