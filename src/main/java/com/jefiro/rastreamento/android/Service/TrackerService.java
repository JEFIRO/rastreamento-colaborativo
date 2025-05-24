package com.jefiro.rastreamento.android.Service;

import com.jefiro.rastreamento.android.Model.DTO.RegisterTrackerDTO;
import com.jefiro.rastreamento.android.Model.TrackerModel;
import com.jefiro.rastreamento.android.Repository.DeviceRepository;
import com.jefiro.rastreamento.android.Repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackerService {

    @Autowired
    TrackerRepository repository;

    @Autowired
    DeviceRepository deviceRepository;

    public void newTracker(RegisterTrackerDTO data) {

        if (data == null) {
            throw new IllegalArgumentException("Dados do tracker não podem ser nulos.");
        }

        var trackerOptional = repository.findByTrackerId(data.idExterno());
        if (trackerOptional.isEmpty()) {
            throw new RuntimeException("Tracker não encontrado com o ID externo: " + data.idExterno());
        }

        var tracker = trackerOptional.get();
        tracker.setRedeName(data.redeName());
        tracker.setRedePassword(data.redePassword());

        var deviceOptional = deviceRepository.findByDeviceId(data.deviceId());
        if (deviceOptional.isEmpty()) {
            throw new RuntimeException("Dispositivo não encontrado com o ID: " + data.deviceId());
        }

        var device = deviceOptional.get();
        device.setTracker(tracker);

        deviceRepository.save(device);
        repository.save(tracker);
    }



}
