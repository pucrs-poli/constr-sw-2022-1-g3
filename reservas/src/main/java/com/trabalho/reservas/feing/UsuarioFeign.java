package com.trabalho.reservas.feing;

import com.trabalho.reservas.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "usuarioFeign", url = "https://628a3c025da6ddfd5d612a6c.mockapi.io")
public interface UsuarioFeign {

    @GetMapping(value = "/usuarios")
    UsuarioDTO getUsuario(String id); //@PathVariable String id

}
