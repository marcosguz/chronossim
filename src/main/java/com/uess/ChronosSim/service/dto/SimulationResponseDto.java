package com.uess.ChronosSim.service.dto;

import com.uess.ChronosSim.model.enums.AlgorithmType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimulationResponseDto {
    private AlgorithmType algorithm;
    private List<TimelineEventDto> timeline;
    private double avgWaitingTime;
    private double avgTurnaroundTime;
    private double avgResponseTime;
    private double cpuUtilization;
    private double throughput;
    private int contextSwitches;
}
