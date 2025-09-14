package com.uess.ChronosSim.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessSimulationDto {
    private String id;
    private int arrivalTime;
    private int burstTime;
    private Integer priority;
}
