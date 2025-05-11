package com.jefiro.rastreamento.android.Model.DTO;

import com.jefiro.rastreamento.android.Model.TrackerModel;

public record TrackerSumaryDTO(
        String _id,
        String trackerId,
        String type) {
    public TrackerSumaryDTO(TrackerModel date) {
        this(date.get_id(), date.getTrackerId(), date.getType());
    }
}
