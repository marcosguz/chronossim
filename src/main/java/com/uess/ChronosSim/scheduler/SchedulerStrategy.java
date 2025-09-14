package com.uess.ChronosSim.scheduler;

import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.model.TimelineEvent;

import java.util.List;

public interface SchedulerStrategy {
    List<TimelineEvent> simulate(List<ProcessSimulation> processes);
}
