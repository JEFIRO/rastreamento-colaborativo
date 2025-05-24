package com.jefiro.rastreamento.android.Controller;

import com.jefiro.rastreamento.android.Model.DTO.RegisterTrackerDTO;
import com.jefiro.rastreamento.android.Model.TrackerModel;
import com.jefiro.rastreamento.android.Service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracker/cad/")
public class TrackerController {

    @Autowired
    TrackerService service;


    @PostMapping("/new")
    public ResponseEntity<String> newTracker(@RequestBody RegisterTrackerDTO data){
        service.newTracker(data);
        return ResponseEntity.ok("Tracker atualizado e associado ao dispositivo com sucesso.");
    }


}
