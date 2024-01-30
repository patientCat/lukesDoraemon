package org.luke.doraemon.common.jobcenter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerContext {

    private IWorker worker;
    private String name;
    private Integer retryTimes;
    private Long sleepTimeSeconds;
}
