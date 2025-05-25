package com.jefiro.rastreamento.android.Model;

import com.jefiro.rastreamento.android.Model.GeolocateApi.WrapperLocate;
import com.jefiro.rastreamento.android.Model.GeolocateApi.WrapperResponse;
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

    @Column(nullable = true)
    private String idLocate;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceModel device;

    private double latitude;
    private double longitude;
    private int accuracy;
    private int rssi;
    private LocalDateTime timestamp;

    public LocationModel(DeviceModel device, WrapperResponse date,WrapperLocate locate) {
        this.device = device;
        this.latitude = date.location().lat();
        this.longitude = date.location().lng();
        this.timestamp = LocalDateTime.now();
        this.rssi = locate.locationRequestDTO().rssi();
        this.idLocate = locate.locationRequestDTO().idLocate();
    }
}
