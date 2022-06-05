package com.trabalho.reservas.feing;

import com.trabalho.reservas.dto.ResourceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "recursoFeign", url = "${reservations.feign.resourcesApi}")
public interface ResourcesFeign {

    @GetMapping(value = "/resources/{id}")
    ResourceDTO getResources(String id); //@PathVariable String id

    @GetMapping(value = "/resourcetypes/{resourcesTypeName}")
    List<ResourceDTO> getResourcesByType(String resourcesTypeName);
}
