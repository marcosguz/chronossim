package com.uess.ChronosSim.service.impl;

import com.uess.ChronosSim.model.Metrics;
import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.model.SimulationOutcome;
import com.uess.ChronosSim.model.TimelineEvent;
import com.uess.ChronosSim.scheduler.*;
import com.uess.ChronosSim.service.SimulationService;
import com.uess.ChronosSim.service.dto.SimulationRequestDto;
import com.uess.ChronosSim.service.impl.component.MetricsCalculator;
import com.uess.ChronosSim.service.mapper.ProcessSimulationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimulationServiceImpl implements SimulationService {
    private final MetricsCalculator metricsCalculator;
    private final ProcessSimulationMapper processSimulationMapper;

    public SimulationServiceImpl(MetricsCalculator metricsCalculator, ProcessSimulationMapper processSimulationMapper) {
        this.metricsCalculator = metricsCalculator;
        this.processSimulationMapper = processSimulationMapper;
    }

    @Override
    public SimulationOutcome runSimulation(SimulationRequestDto request) {
        List<ProcessSimulation> processes = request.getProcessSimulationDto().stream().map(processSimulationMapper::processSimulationToProcessSimulationDto).collect(Collectors.toList());
        SchedulerStrategy scheduler = getStrategy(request);
        List<TimelineEvent> timeline = scheduler.simulate(processes);
        Metrics metrics = metricsCalculator.calculateMetrics(processes, timeline);

        return new SimulationOutcome(request.getAlgorithm(), timeline, metrics);
    }


    private SchedulerStrategy getStrategy(SimulationRequestDto request) {
        return switch (request.getAlgorithm()) {
            case FCFS -> new FCFSScheduler();
            case SJF -> new SJFScheduler();
            case RR -> new RRScheduler(request.getQuantum() != null ? request.getQuantum() : 2);
            case PRIORITY -> new PriorityScheduler();
            case CHS -> new CHSScheduler();
            default -> throw new IllegalArgumentException("Algoritmo no Soportado: " + request.getAlgorithm());
        };
    }
}