package org.luke.doraemon.common.jobcenter;

import org.luke.doraemon.common.jobcenter.model.JobRequest;

public class DefaultFallback {
    public static Boolean onSuccess(JobRequest jobRequest){
        return true;
    }

    public static Boolean onFail(JobRequest jobRequest, Exception e){
        return true;
    }
}
