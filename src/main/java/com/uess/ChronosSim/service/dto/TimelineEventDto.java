package com.uess.ChronosSim.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimelineEventDto {
    private int startTime;
    private int endTime;
    private String processId;
}
