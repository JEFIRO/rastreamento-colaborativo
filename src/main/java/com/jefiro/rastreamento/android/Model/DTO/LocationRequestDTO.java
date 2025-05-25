package com.jefiro.rastreamento.android.Model.DTO;

public record LocationRequestDTO(
        String idLocate,
        String deviceMacAddress,
        String trackerId,
        Integer rssi) {
}
