package com.uess.ChronosSim.scheduler;


import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.model.TimelineEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityScheduler implements SchedulerStrategy {

    @Override
    public List<TimelineEvent> simulate(List<ProcessSimulation> processes) {
        List<TimelineEvent> timeline = new ArrayList<>();
        List<ProcessSimulation> remaining = new ArrayList<>(processes);
        int currentTime = 0;

        while (!remaining.isEmpty()) {
            int finalCurrentTime = currentTime;
            List<ProcessSimulation> available = remaining.stream()
                    .filter(p -> p.getArrivalTime() <= finalCurrentTime)
                    .toList();

            ProcessSimulation next;
            if (!available.isEmpty()) {
                next = available.stream()
                        .min(Comparator.comparingInt(ProcessSimulation::getPriority)
                                .thenComparingInt(ProcessSimulation::getArrivalTime))
                        .get();
            } else {
                next = remaining.stream()
                        .min(Comparator.comparingInt(ProcessSimulation::getArrivalTime))
                        .get();
                currentTime = next.getArrivalTime();
            }

            timeline.add(new TimelineEvent(currentTime, currentTime + next.getBurstTime(), next.getId()));
            currentTime += next.getBurstTime();
            remaining.remove(next);
        }

        return timeline;
    }
}
