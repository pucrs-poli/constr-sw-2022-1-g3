package com.trabalho.reservas.feing;

import com.trabalho.reservas.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "usersFeign", url = "${reservations.feign.usersApi}")
public interface UserFeign {

    @GetMapping(value = "/users")
    UserDTO getUsuario(String id); //@PathVariable String id

}
