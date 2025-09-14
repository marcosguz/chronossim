package com.uess.ChronosSim.model;

import com.uess.ChronosSim.model.enums.AlgorithmType;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimulationOutcome {
    private AlgorithmType algorithm;
    private List<TimelineEvent> timeline;
    private Metrics metrics;
}
