package org.luke.doraemon.common.jobcenter.repo;

import java.util.Map;

public interface IJobStatusRepo {

    Map<String, Boolean> getWorkerStatus(String jobId);

    void setStatus(String jobId, String workerName, boolean b);
}
