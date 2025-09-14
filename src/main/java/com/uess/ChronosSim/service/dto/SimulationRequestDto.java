package com.uess.ChronosSim.service.dto;

import com.uess.ChronosSim.model.enums.AlgorithmType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimulationRequestDto {
    private AlgorithmType algorithm;
    private Integer quantum;
    private List<ProcessSimulationDto> processSimulationDto;
}
