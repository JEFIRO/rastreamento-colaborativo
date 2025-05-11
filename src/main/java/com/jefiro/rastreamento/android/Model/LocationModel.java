package com.jefiro.rastreamento.android.Model;

import com.jefiro.rastreamento.android.Model.DTO.LocationRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "location")
public class LocationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String _id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceModel device;

    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
    private int rssi;

    public LocationModel(DeviceModel date, LocationRequestDTO location) {
        this.device = date;
        this.latitude = location.latitude();
        this.longitude = location.longitude();
        this.timestamp = location.timestamp();
        this.rssi = location.rssi();
    }
}
