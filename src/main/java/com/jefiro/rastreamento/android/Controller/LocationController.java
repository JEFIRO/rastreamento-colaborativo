package com.jefiro.rastreamento.android.Controller;

import com.jefiro.rastreamento.android.Model.DTO.LocationRequestDTO;
import com.jefiro.rastreamento.android.Model.DTO.LocationResponseDTO;
import com.jefiro.rastreamento.android.Model.GeolocateApi.WrapperLocate;
import com.jefiro.rastreamento.android.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracker")
public class LocationController {

    @Autowired
    LocationService service;

    @PostMapping("/post")
    public ResponseEntity<?> newLocation(@RequestBody WrapperLocate date){
        service.newLocation(date);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/all/{id}")
    public ResponseEntity<List<LocationResponseDTO>> findLocation(@PathVariable String id){
        return ResponseEntity.ok(service.findAll(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<LocationResponseDTO> findLocationByID(@PathVariable String id){
        return ResponseEntity.ok(service.findLocation(id));
    }

}
