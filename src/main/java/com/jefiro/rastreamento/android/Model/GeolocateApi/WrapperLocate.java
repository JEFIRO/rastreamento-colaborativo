package com.jefiro.rastreamento.android.Model.GeolocateApi;

import com.jefiro.rastreamento.android.Model.DTO.LocationRequestDTO;

public record WrapperLocate(
        WifiListDTO wifiListDTO,
        LocationRequestDTO locationRequestDTO) {
}
