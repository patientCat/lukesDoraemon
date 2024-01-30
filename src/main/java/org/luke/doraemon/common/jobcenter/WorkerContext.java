package org.luke.doraemon.common.jobcenter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerContext {
    private IWorker worker;
    private Integer retryTimes;
    private Long sleepTimeSeconds;

    public String getName(){
        if(worker != null) {
            return worker.getName();
        }
        return "";
    }
}
