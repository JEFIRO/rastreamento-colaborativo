package com.jefiro.rastreamento.android.Model.DTO;

public record UserCreated(
        String name,
        String email,
        String password,
        DeviceRequestDTO device
) {
}
