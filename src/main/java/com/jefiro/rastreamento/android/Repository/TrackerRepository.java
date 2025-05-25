package com.jefiro.rastreamento.android.Repository;

import com.jefiro.rastreamento.android.Model.TrackerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackerRepository extends JpaRepository<TrackerModel,String> {
    Optional<TrackerModel> findByTrackerId(String id);

}
