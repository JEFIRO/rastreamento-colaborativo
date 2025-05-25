package com.jefiro.rastreamento.android.Model.GeolocateApi;

public record wifiAccessPointsDTO(
         String macAddress,
         String signalStrength,
         String signalToNoiseRatio
) {
}
