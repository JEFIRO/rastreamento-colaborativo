package com.jefiro.rastreamento.android.Model.DTO;

import com.jefiro.rastreamento.android.Model.DeviceModel;
import com.jefiro.rastreamento.android.Model.TrackerModel;

import java.time.LocalDateTime;
import java.util.List;

public record DeviceSumary(
        String _id,
        String deviceId,
        String deviceModel,
        String owner,
        boolean active,
        LocalDateTime createAccountDate,
        TrackerSumaryDTO trackerModel
) {
    public DeviceSumary(DeviceModel date) {
        this(date.get_id(), date.getDeviceId(), date.getDeviceModel(), date.getOwner(), date.isActive(),
                date.getCreateAccountDate(), new TrackerSumaryDTO(date.getTracker()));
    }
}
