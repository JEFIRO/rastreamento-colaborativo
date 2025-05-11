package com.jefiro.rastreamento.android.Model.DTO;

import java.time.LocalDateTime;

public record LocationRequestDTO(
        String deviceMacAddress,
        String trackerId,
        double latitude,
        double longitude,
        LocalDateTime timestamp,
        Integer rssi) {
}
