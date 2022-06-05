package com.trabalho.reservas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("reservations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {

    @Id
    public String id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean enabled;
    private String lessonId;
    private String resourceId;
    private String userId;

}
