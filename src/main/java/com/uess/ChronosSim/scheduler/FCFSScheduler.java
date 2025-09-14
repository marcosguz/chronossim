package com.uess.ChronosSim.scheduler;

import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.model.TimelineEvent;

import java.util.Comparator;
import java.util.List;


public class FCFSScheduler implements SchedulerStrategy {
    @Override
    public List<TimelineEvent> simulate(List<ProcessSimulation> processes) {
        List<TimelineEvent> timeline = new java.util.ArrayList<>();
        int currentTime = 0;

        processes.sort(Comparator.comparingInt(ProcessSimulation::getArrivalTime));

        for (ProcessSimulation process : processes) {
            if (currentTime < process.getArrivalTime()) currentTime = process.getArrivalTime();
            timeline.add(new TimelineEvent(currentTime, currentTime + process.getBurstTime(), process.getId()));
            currentTime += process.getBurstTime();
        }

        return timeline;
    }
}
