package com.jefiro.rastreamento.android.Service;

import com.jefiro.rastreamento.android.Model.DTO.LocationRequestDTO;
import com.jefiro.rastreamento.android.Model.DTO.LocationResponseDTO;
import com.jefiro.rastreamento.android.Model.LocationModel;
import com.jefiro.rastreamento.android.Repository.DeviceRepository;
import com.jefiro.rastreamento.android.Repository.LocationRepository;
import com.jefiro.rastreamento.android.infra.exception.RequestNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    DeviceRepository repository;

    @Autowired
    LocationRepository locationRepository;

    public void newLocation(LocationRequestDTO date) {
        if (date == null) {
            throw new RequestNullException();
        }


        var device = repository.findByDeviceIdAndTracker_TrackerId(date.deviceMacAddress(), date.trackerId()).orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado."));


        LocationModel location = new LocationModel(device, date);

        locationRepository.save(location);
    }

    public LocationResponseDTO findLocation(String id) {
        var location = locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Localização não encontrada."));
        return new LocationResponseDTO(location);
    }

    public List<LocationResponseDTO> findAll(String id) {
        return locationRepository.findByDevice_DeviceId(id).stream().map(LocationResponseDTO::new).toList();
    }
}
