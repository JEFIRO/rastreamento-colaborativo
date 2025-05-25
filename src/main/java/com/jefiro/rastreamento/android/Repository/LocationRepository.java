package com.jefiro.rastreamento.android.Repository;

import com.jefiro.rastreamento.android.Model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationModel,String> {
    List<LocationModel> findByDevice_DeviceId(String deviceId);
    Optional<LocationModel> findByIdLocate(String id);

}
