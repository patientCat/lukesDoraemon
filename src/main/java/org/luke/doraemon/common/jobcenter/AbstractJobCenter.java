package org.luke.doraemon.common.jobcenter;


import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import io.vavr.Function2;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.luke.doraemon.common.jobcenter.model.JobRequest;

@Slf4j
public abstract class AbstractJobCenter {
    public abstract String getJobName();

    protected abstract Function<JobRequest, Boolean> getSuccessFallback();

    protected abstract Function2<JobRequest, Exception, Boolean> getFailFallback();

    protected abstract List<WorkerContext> getWorkerList();


    public void run(JobRequest jobRequest) {
        log.info("jobRequest={}", jobRequest);
        String jobName = jobRequest.getJobName();
        try {
            run0(jobRequest);
            if (this.getSuccessFallback() != null) {
                log.info("success_call_back");
                this.getSuccessFallback().apply(jobRequest);
            }
        } catch (Exception e) {
            log.error("job={}_alarm_emsg={}", jobName, e.getMessage(), e);
            if (this.getFailFallback() != null) {
                log.info("fail_call_back");
                this.getFailFallback().apply(jobRequest, e);
            }
        }
    }

    @SneakyThrows
    private void run0(JobRequest jobContext) {
        if (this.getWorkerList() != null) {
            List<WorkerContext> stepHandlerList = this.getWorkerList();
            for (int i = 0; i < stepHandlerList.size(); i++) {
                WorkerContext workerContext = stepHandlerList.get(i);
                log.info("begin_number={}_step_name={}", i, workerContext.getName());
                long sleepTimeSeconds = workerContext.getSleepTimeSeconds();
                int retryTimes = workerContext.getRetryTimes();
                Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                        .retryIfException()
                        .retryIfResult(BooleanUtils::isNotTrue)
                        .withWaitStrategy(WaitStrategies.fixedWait(sleepTimeSeconds, TimeUnit.SECONDS))
                        .withStopStrategy(StopStrategies.stopAfterAttempt(retryTimes))
                        .build();

                int finalI = i;
                AtomicReference<Exception> atomicException = new AtomicReference<>();
                try{
                    retryer.call(() -> {
                        try {
                            workerContext.getWorker().apply(jobContext);
                            log.info("begin_number={}_step_name={}_success", finalI, workerContext.getName());
                            return true;
                        } catch (Exception e) {
                            log.error("begin_number={}_step_name={}_fail", finalI, workerContext.getName(), e);
                            atomicException.set(e);
                            return false;
                        }
                    });
                }catch (Exception e){
                    log.error("Retrying failed to complete successfully after {} times", retryTimes, e);
                    if(atomicException.get() != null){
                        throw atomicException.get();
                    }
                }
            }
        }
    }
}
