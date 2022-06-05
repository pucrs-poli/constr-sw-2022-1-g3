package com.trabalho.reservas.feing;

import com.trabalho.reservas.dto.LessonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "lessonsFeign", url = "${reservations.feign.lessonsApi}")
public interface LessonFeign {

    @GetMapping(value = "/lessons/{id}")
    LessonDTO getClasses(String id); //@PathVariable String id

}
