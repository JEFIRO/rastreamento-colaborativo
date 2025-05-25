package com.jefiro.rastreamento.android.Service;

import com.jefiro.rastreamento.android.Model.DTO.LocationRequestDTO;
import com.jefiro.rastreamento.android.Model.DTO.LocationResponseDTO;
import com.jefiro.rastreamento.android.Model.GeolocateApi.GoogleGeolocationService;
import com.jefiro.rastreamento.android.Model.GeolocateApi.WrapperLocate;
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

    @Autowired
    GoogleGeolocationService geolocationService;

    public void newLocation(WrapperLocate date) {
        if (date == null || date.locationRequestDTO() == null || date.wifiListDTO() == null) {
            throw new RequestNullException("Dados da localização estão incompletos.");
        }

        if (locationRepository.findByIdLocate(date.locationRequestDTO().idLocate()).isPresent()) {
            throw new IllegalStateException("Localização já registrada.");
        }

        var device = repository.findByDeviceIdAndTracker_TrackerId(
                date.locationRequestDTO().deviceMacAddress(),
                date.locationRequestDTO().trackerId()
        ).orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado."));

        var response = geolocationService.getLocation(date.wifiListDTO()).block();

        if (response == null) {
            throw new IllegalStateException("Não foi possível obter localização da API.");
        }

        LocationModel location = new LocationModel(device, response, date);
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
