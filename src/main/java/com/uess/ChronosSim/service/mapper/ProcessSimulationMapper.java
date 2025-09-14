package com.uess.ChronosSim.service.mapper;

import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.service.dto.ProcessSimulationDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProcessSimulationMapper {
    ProcessSimulation processSimulationToProcessSimulationDto(ProcessSimulationDto processSimulationDto);
    ProcessSimulationDto processSimulationDtoToProcessSimulation(ProcessSimulation processSimulation);
}
