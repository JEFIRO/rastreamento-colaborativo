package com.jefiro.rastreamento.android.Repository;

import com.jefiro.rastreamento.android.Model.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceModel,String> {
    Optional<DeviceModel> findByDeviceIdAndTracker_TrackerId(String deviceId, String trackerId);

    Optional<DeviceModel> findByDeviceId(String deviceId);

}
