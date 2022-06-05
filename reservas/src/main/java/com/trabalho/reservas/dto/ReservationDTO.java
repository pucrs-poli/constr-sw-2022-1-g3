package com.trabalho.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservationDTO {

    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean enabled;
    private UserDTO user;
    private ResourceDTO resource;
    private LessonDTO lesson;
}
