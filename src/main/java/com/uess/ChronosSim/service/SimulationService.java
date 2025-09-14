package com.uess.ChronosSim.service;

import com.uess.ChronosSim.model.SimulationOutcome;
import com.uess.ChronosSim.service.dto.SimulationRequestDto;

public interface SimulationService {
    SimulationOutcome runSimulation(SimulationRequestDto request);
}
