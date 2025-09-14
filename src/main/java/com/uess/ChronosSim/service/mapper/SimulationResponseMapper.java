package com.uess.ChronosSim.service.mapper;

import com.uess.ChronosSim.model.SimulationOutcome;
import com.uess.ChronosSim.service.dto.SimulationResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface SimulationResponseMapper {
    SimulationResponseDto simulationToSimulationResultDto(SimulationOutcome simulation);
}
