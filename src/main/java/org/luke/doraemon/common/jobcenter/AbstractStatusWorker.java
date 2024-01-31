package org.luke.doraemon.common.jobcenter;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.luke.doraemon.common.jobcenter.model.JobRequest;
import org.luke.doraemon.common.jobcenter.repo.IJobStatusRepo;

@AllArgsConstructor
public abstract class AbstractStatusWorker implements IWorker{
    private IJobStatusRepo jobStatusRepo;
    @Override
    public void apply(JobRequest jobRequest) {
        if(canAlwaysWork()){
            doWork(jobRequest);
            letWorkerTired(jobRequest.getJobId());
            return ;
        }

        boolean tired = hasWorked(jobRequest.getJobId());
        if(!tired){
            doWork(jobRequest);
            letWorkerTired(jobRequest.getJobId());
        }
    }
    public boolean canAlwaysWork(){
        return false;
    }

    public boolean hasWorked(String jobId){
        Map<String, Boolean> workerStatus = jobStatusRepo.getWorkerStatus(jobId);
        return workerStatus.containsKey(getName());
    }

    public void letWorkerTired(String jobId){
        jobStatusRepo.setStatus(jobId, getName(), true);
    }

    public abstract void doWork(JobRequest jobRequest);
}
