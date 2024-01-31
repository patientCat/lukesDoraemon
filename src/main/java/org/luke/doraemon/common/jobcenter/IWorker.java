package org.luke.doraemon.common.jobcenter;

import org.luke.doraemon.common.jobcenter.model.JobRequest;

public interface IWorker {
    void apply(JobRequest jobRequest);

    default String getName(){
        return "defaultWorker";
    }
}
