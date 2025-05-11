package com.jefiro.rastreamento.android.Model.DTO;

import java.time.LocalDateTime;

public record DeviceRequestDTO(
        String deviceId,
        String deviceModel,
        String trackerID
) {
}
