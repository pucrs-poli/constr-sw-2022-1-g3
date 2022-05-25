package com.trabalho.reservas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErroDTO {
    private String error;
    private Integer status;
}
