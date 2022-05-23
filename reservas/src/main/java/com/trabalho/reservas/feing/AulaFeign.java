package com.trabalho.reservas.feing;

import com.trabalho.reservas.dto.AulaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "aulasFeign", url = "https://628a3c025da6ddfd5d612a6c.mockapi.io")
public interface AulaFeign {

    @GetMapping(value = "/aulas")
//    @GetMapping
    AulaDTO getAulas(String id); //@PathVariable String id

}
