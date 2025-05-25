package com.jefiro.rastreamento.android.Model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "tracker")
public class TrackerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String _id;

    @Column(unique = true)
    private String trackerId;
    private String type;

    private String redeName;
    private String redePassword;
    private String deviceId;

    @OneToOne(mappedBy = "tracker")
    private DeviceModel deviceModel;

    public TrackerModel(String trackerId) {
        this.trackerId = trackerId;
    }
}
