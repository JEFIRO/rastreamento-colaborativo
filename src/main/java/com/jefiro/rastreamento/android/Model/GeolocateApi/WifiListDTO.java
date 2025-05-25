package com.jefiro.rastreamento.android.Model.GeolocateApi;

import java.util.List;

public record WifiListDTO(
        String considerIp,
        List<wifiAccessPointsDTO> wifiAccessPoints
) {
}
