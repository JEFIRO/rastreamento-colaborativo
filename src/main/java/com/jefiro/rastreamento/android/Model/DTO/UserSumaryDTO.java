package com.jefiro.rastreamento.android.Model.DTO;

import com.jefiro.rastreamento.android.Model.DeviceModel;
import com.jefiro.rastreamento.android.Model.UserModel;

import java.util.List;

public record UserSumaryDTO(
        String _id,
        String name,
        String email,
        DeviceSumary model
) {
    public UserSumaryDTO(UserModel date) {
        this(date.get_id(), date.getName(), date.getEmail(),new DeviceSumary(date.getDevices().get(0)));
    }
}
