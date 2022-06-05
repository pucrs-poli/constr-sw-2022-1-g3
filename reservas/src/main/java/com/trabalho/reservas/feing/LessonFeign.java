package com.trabalho.reservas.feing;

import com.trabalho.reservas.dto.LessonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "lessonsFeign", url = "https://628a3c025da6ddfd5d612a6c.mockapi.io")
public interface LessonFeign {

    @GetMapping(value = "/lessons/{id}")
    LessonDTO getClasses(String id); //@PathVariable String id

}
