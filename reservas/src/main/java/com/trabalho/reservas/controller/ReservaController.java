package com.trabalho.reservas.controller;

import com.trabalho.reservas.dto.ReservaDTO;
import com.trabalho.reservas.dto.request.ReservaRequestDTO;
import com.trabalho.reservas.usecases.CriarReservaUsecase;
import com.trabalho.reservas.usecases.ListarReservasUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private CriarReservaUsecase criarReservaUsecase;

    @Autowired
    private ListarReservasUsecase listarReservasUsecase;

    @PostMapping
    public void criarReserva(
            @RequestBody ReservaRequestDTO reservaRequestDTO
            ){
        criarReservaUsecase.execute(reservaRequestDTO);
    }

    @GetMapping
    public List<ReservaDTO> listarReservas(){
        return listarReservasUsecase.execute();
    }

}
