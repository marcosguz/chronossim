package com.uess.ChronosSim.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimelineEvent {
    private int start;
    private int end;
    private String processId;
}
