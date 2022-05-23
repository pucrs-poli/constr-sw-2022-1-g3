package com.trabalho.reservas.feing;

import com.trabalho.reservas.dto.RecursoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "recursoFeign", url = "https://628a3c025da6ddfd5d612a6c.mockapi.io")
public interface RecursoFeign {

    @GetMapping(value = "/recursos")
    RecursoDTO getRecurso(String id); //@PathVariable String id

}
