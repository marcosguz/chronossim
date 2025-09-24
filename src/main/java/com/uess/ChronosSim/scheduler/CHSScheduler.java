package com.uess.ChronosSim.scheduler;

import com.uess.ChronosSim.model.ProcessSimulation;
import com.uess.ChronosSim.model.TimelineEvent;

import java.util.*;
import java.util.stream.Collectors;

public class CHSScheduler implements SchedulerStrategy {

    private List<ProcessSimulation> readyQueue = new ArrayList<>();
    private ProcessSimulation currentProcess = null;

    @Override
    public List<TimelineEvent> simulate(List<ProcessSimulation> processes) {

        List<TimelineEvent> timeline = new ArrayList<>();
        int currentTime = 0;
        int contextSwitches = 0;

        List<ProcessSimulation> allProcesses = new ArrayList<>(processes);

        // Mapas para cálculo de métricas
        Map<String, Integer> startTimes = new HashMap<>();
        Map<String, Integer> finishTimes = new HashMap<>();
        Map<String, Integer> originalBurstTimes = processes.stream()
                .collect(Collectors.toMap(ProcessSimulation::getId, ProcessSimulation::getBurstTime));

        while (!allProcesses.isEmpty() || !readyQueue.isEmpty() || currentProcess != null) {

            int finalCurrentTime = currentTime;

            // Llegada de procesos
            List<ProcessSimulation> arriving = allProcesses.stream()
                    .filter(p -> p.getArrivalTime() == finalCurrentTime)
                    .toList();
            readyQueue.addAll(arriving);
            allProcesses.removeAll(arriving);

            // Selección del mejor proceso
            if (!readyQueue.isEmpty()) {
                ProcessSimulation best = readyQueue.stream()
                        .min(Comparator.comparingInt(ProcessSimulation::getBurstTime)
                                .thenComparing(Comparator.comparing(ProcessSimulation::getPriority).reversed()))
                        .get();

                if (currentProcess == null || !currentProcess.equals(best)) {
                    if (currentProcess != null) {
                        readyQueue.add(currentProcess);
                        contextSwitches++;
                        timeline.add(new TimelineEvent(currentTime - 1, currentTime, currentProcess.getId()));
                    }
                    currentProcess = best;
                    readyQueue.remove(best);

                    // Registrar primer arranque
                    startTimes.putIfAbsent(currentProcess.getId(), currentTime);
                }
            }

            // Ejecutar 1 unidad de tiempo
            if (currentProcess != null) {
                currentProcess.setBurstTime(currentProcess.getBurstTime() - 1);

                // Si terminó
                if (currentProcess.getBurstTime() == 0) {
                    timeline.add(new TimelineEvent(currentTime, currentTime + 1, currentProcess.getId()));
                    finishTimes.put(currentProcess.getId(), currentTime + 1);
                    currentProcess = null;
                }
            }

            currentTime++;
        }

        // Cálculo de métricas
        double avgWaitingTime = processes.stream()
                .mapToDouble(p -> finishTimes.get(p.getId()) - p.getArrivalTime() - originalBurstTimes.get(p.getId()))
                .average().orElse(0);

        double avgTurnaroundTime = processes.stream()
                .mapToDouble(p -> finishTimes.get(p.getId()) - p.getArrivalTime())
                .average().orElse(0);

        double avgResponseTime = processes.stream()
                .mapToDouble(p -> startTimes.get(p.getId()) - p.getArrivalTime())
                .average().orElse(0);

        double totalBurst = originalBurstTimes.values().stream().mapToInt(Integer::intValue).sum();
        double cpuUtilization = ((double) totalBurst / currentTime) * 100;
        double throughput = ((double) processes.size() / currentTime);

        System.out.println("Métricas CHS:");
        System.out.println("Avg Waiting Time: " + avgWaitingTime);
        System.out.println("Avg Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Avg Response Time: " + avgResponseTime);
        System.out.println("CPU Utilization: " + cpuUtilization);
        System.out.println("Throughput: " + throughput);
        System.out.println("Context Switches: " + contextSwitches);

        return timeline;
    }
}