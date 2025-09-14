package com.uess.ChronosSim.scheduler;


import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.model.TimelineEvent;

import java.util.*;

public class RRScheduler implements SchedulerStrategy {
    private final int quantum;

    public RRScheduler(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public List<TimelineEvent> simulate(List<ProcessSimulation> processes) {
        List<TimelineEvent> timeline = new ArrayList<>();
        Queue<ProcessSimulation> queue = new LinkedList<>();
        Map<String, Integer> remainingTime = new HashMap<>();
        int currentTime = 0;

        processes.forEach(p -> remainingTime.put(p.getId(), p.getBurstTime()));
        List<ProcessSimulation> arrivalList = new ArrayList<>(processes);
        arrivalList.sort(Comparator.comparingInt(ProcessSimulation::getArrivalTime));

        while (!remainingTime.isEmpty()) {
            while (!arrivalList.isEmpty() && arrivalList.getFirst().getArrivalTime() <= currentTime) {
                queue.offer(arrivalList.removeFirst());
            }

            if (queue.isEmpty()) {
                currentTime = !arrivalList.isEmpty() ? arrivalList.getFirst().getArrivalTime() : currentTime;
                continue;
            }

            ProcessSimulation p = queue.poll();
            int execTime = Math.min(quantum, remainingTime.get(p.getId()));
            timeline.add(new TimelineEvent(currentTime, currentTime + execTime, p.getId()));
            currentTime += execTime;
            remainingTime.put(p.getId(), remainingTime.get(p.getId()) - execTime);

            if (remainingTime.get(p.getId()) > 0) {
                while (!arrivalList.isEmpty() && arrivalList.getFirst().getArrivalTime() <= currentTime) {
                    queue.offer(arrivalList.removeFirst());
                }
                queue.offer(p);
            } else {
                remainingTime.remove(p.getId());
            }
        }

        return timeline;
    }
}
