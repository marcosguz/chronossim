package com.uess.ChronosSim.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Metrics {
    private double avgWaitingTime;
    private double avgTurnaroundTime;
    private double avgResponseTime;
    private double cpuUtilization;
    private double throughput;
    private int contextSwitches;
}
