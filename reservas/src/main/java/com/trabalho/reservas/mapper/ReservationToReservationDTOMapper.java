package com.trabalho.reservas.mapper;

import com.trabalho.reservas.dto.LessonDTO;
import com.trabalho.reservas.dto.ReservationDTO;
import com.trabalho.reservas.dto.ResourceDTO;
import com.trabalho.reservas.dto.UserDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.feing.LessonFeign;
import com.trabalho.reservas.feing.ResourcesFeign;
import com.trabalho.reservas.feing.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationToReservationDTOMapper {

    @Autowired
    private LessonFeign lessonFeign;

    @Autowired
    private ResourcesFeign resourcesFeign;

    @Autowired
    private UserFeign userFeign;

    public ReservationDTO mapper(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        BeanUtils.copyProperties(reservation, dto);

        try {
            LessonDTO lessonDTO = lessonFeign.getClasses(reservation.getLessonId());
            dto.setLesson(lessonDTO);
        } catch (Exception e) {
            log.debug("error to find lesson {} on reservation {}", reservation.getLessonId(), reservation.getId(), e);
        }

        try {
            ResourceDTO resourceDTO = resourcesFeign.getResources(reservation.getResourceId());
            dto.setResource(resourceDTO);
        } catch (Exception e) {
            log.debug("error to find resource {} on reservation {}", reservation.getResourceId(), reservation.getId(), e);
        }

        try {
            UserDTO userDTO = userFeign.getUsuario(reservation.getUserId());
            dto.setUser(userDTO);
        } catch (Exception e) {
            log.debug("error to find user {} on reservation {}", reservation.getUserId(), reservation.getId(), e);
        }

        return dto;
    }

}
