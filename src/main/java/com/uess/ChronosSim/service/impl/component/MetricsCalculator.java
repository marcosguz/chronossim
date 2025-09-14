package com.uess.ChronosSim.service.impl.component;

import com.uess.ChronosSim.model.Metrics;
import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.model.TimelineEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MetricsCalculator {
    public Metrics calculateMetrics(List<ProcessSimulation> processes, List<TimelineEvent> timeline) {
        int n = processes.size();
        double totalWaiting = 0;
        double totalTurnaround = 0;
        double totalResponse = 0;
        int contextSwitches = timeline.size() - 1;

        for (ProcessSimulation p : processes) {
            int arrival = p.getArrivalTime();
            List<TimelineEvent> events = timeline.stream()
                    .filter(t -> t.getProcessId().equals(p.getId()))
                    .toList();

            int start = events.getFirst().getStart();
            int end = events.getLast().getEnd();

            totalWaiting += (end - arrival - p.getBurstTime());
            totalTurnaround += end - arrival;
            totalResponse += start - arrival;
        }

        double cpuTime = timeline.stream().mapToInt(t -> t.getEnd() - t.getStart()).sum();
        double totalTime = timeline.getLast().getEnd();
        double cpuUtil = (cpuTime / totalTime) * 100;
        double throughput = n / totalTime;

        return new Metrics(
                totalWaiting / n,
                totalTurnaround / n,
                totalResponse / n,
                cpuUtil,
                throughput,
                contextSwitches
        );
    }
}
