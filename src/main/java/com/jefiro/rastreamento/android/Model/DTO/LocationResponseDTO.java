package com.jefiro.rastreamento.android.Model.DTO;

import com.jefiro.rastreamento.android.Model.LocationModel;

import java.time.LocalDateTime;

public record LocationResponseDTO(
        String id,
        String deviceMacAddress,
        double longitude,
        double latitude,
        LocalDateTime timestamp,
        Integer rssi) {

    public LocationResponseDTO(LocationModel date) {
        this(date.get_id(), date.getDevice().getDeviceId(), date.getLongitude(), date.getLatitude(), date.getTimestamp(), date.getRssi());

    }
}
