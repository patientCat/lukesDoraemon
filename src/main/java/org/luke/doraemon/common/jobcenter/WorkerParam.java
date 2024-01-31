package org.luke.doraemon.common.jobcenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerParam {
    public static WorkerParam ofDefault(){
        WorkerParam workerParam = new WorkerParam();
        workerParam.retryTimes = 1;
        workerParam.sleepTimeSeconds = 1L;
        return workerParam;
    }

    private Integer retryTimes;
    private Long sleepTimeSeconds;
}
