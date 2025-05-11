package com.jefiro.rastreamento.android.Model;

import com.jefiro.rastreamento.android.Model.DTO.DeviceRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "device")
public class DeviceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String _id;
    @Column(unique = true)
    private String deviceId;
    private String deviceModel;
    private String owner;
    private boolean active;
    private LocalDateTime createAccountDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tracker_id")
    private TrackerModel tracker;


    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocationModel> location;


    public DeviceModel(DeviceRequestDTO date) {
        this.deviceId = date.deviceId();
        this.deviceModel = date.deviceModel();
        this.active = true;
        this.createAccountDate = LocalDateTime.now();
    }
}
