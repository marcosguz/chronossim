package com.uess.ChronosSim.service.mapper.impl;

import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.service.dto.ProcessSimulationDto;
import com.uess.ChronosSim.service.mapper.ProcessSimulationMapper;
import org.springframework.stereotype.Component;

@Component
public class ProcessSimulationMapperImpl implements ProcessSimulationMapper {
    @Override
    public ProcessSimulation processSimulationToProcessSimulationDto(ProcessSimulationDto processSimulationDto) {
        return new ProcessSimulation(
                processSimulationDto.getId(),
                processSimulationDto.getArrivalTime(),
                processSimulationDto.getBurstTime(),
                processSimulationDto.getPriority()
        );
    }

    @Override
    public ProcessSimulationDto processSimulationDtoToProcessSimulation(ProcessSimulation processSimulation) {
        return new ProcessSimulationDto(
                processSimulation.getId(),
                processSimulation.getArrivalTime(),
                processSimulation.getBurstTime(),
                processSimulation.getPriority()
        );
    }
}
