package com.uess.ChronosSim.controller;

import com.uess.ChronosSim.service.SimulationService;
import com.uess.ChronosSim.service.dto.SimulationRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/simulations")
public class SimulationController {
    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping
    public ResponseEntity<?> runSimulation(@RequestBody SimulationRequestDto requestDto) {
        return new ResponseEntity<>(simulationService.runSimulation(requestDto), HttpStatus.OK);
    }
}
