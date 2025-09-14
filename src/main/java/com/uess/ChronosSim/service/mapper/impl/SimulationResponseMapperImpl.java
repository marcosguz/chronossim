package com.uess.ChronosSim.service.mapper.impl;

import com.uess.ChronosSim.model.SimulationOutcome;
import com.uess.ChronosSim.service.dto.SimulationResponseDto;
import com.uess.ChronosSim.service.dto.TimelineEventDto;
import com.uess.ChronosSim.service.mapper.SimulationResponseMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SimulationResponseMapperImpl implements SimulationResponseMapper {
    @Override
    public SimulationResponseDto simulationToSimulationResultDto(SimulationOutcome simulation) {
        return new SimulationResponseDto(
                simulation.getAlgorithm(),
                simulation.getTimeline().stream().map(t -> new TimelineEventDto(t.getStart(), t.getEnd(), t.getProcessId())).collect(Collectors.toList()),
                simulation.getMetrics().getAvgWaitingTime(),
                simulation.getMetrics().getAvgTurnaroundTime(),
                simulation.getMetrics().getAvgResponseTime(),
                simulation.getMetrics().getCpuUtilization(),
                simulation.getMetrics().getThroughput(),
                simulation.getMetrics().getContextSwitches()
        );
    }
}
