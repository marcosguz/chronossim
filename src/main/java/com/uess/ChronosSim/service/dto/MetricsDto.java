package com.uess.ChronosSim.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetricsDto {
    private double avgWaitingTime;
    private double avgTurnaroundTime;
    private double avgResponseTime;
    private double cpuUtilization;
    private double throughput;
    private int contextSwitches;
}
