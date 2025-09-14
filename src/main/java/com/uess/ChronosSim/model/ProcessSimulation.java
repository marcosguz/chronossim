package com.uess.ChronosSim.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessSimulation {
    private String id;
    private int arrivalTime;
    private int burstTime;
    private int priority;
}
