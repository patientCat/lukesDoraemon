package test.org.doraemon.common.jobcenter;

import com.google.common.collect.Lists;
import io.vavr.Function2;
import java.util.List;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.luke.doraemon.common.constant.JobCenterName;
import org.luke.doraemon.common.jobcenter.AbstractJobCenter;
import org.luke.doraemon.common.jobcenter.DefaultFallback;
import org.luke.doraemon.common.jobcenter.IWorker;
import org.luke.doraemon.common.jobcenter.WorkerContext;
import org.luke.doraemon.common.jobcenter.model.JobRequest;

@Slf4j
public class ExampleJobCenter extends AbstractJobCenter {

    @Override
    public String getJobName() {
        return JobCenterName.ExampleJobCenter.getValue();
    }

    @Override
    public Function<JobRequest, Boolean> getSuccessFallback() {
        return DefaultFallback::onSuccess;
    }

    @Override
    public Function2<JobRequest, Exception, Boolean> getFailFallback() {
        return DefaultFallback::onFail;
    }

    @Override
    public List<WorkerContext> getWorkerList() {
        List<WorkerContext> contextArrayList = Lists.newArrayList();
        contextArrayList.add(new WorkerContext(new IWorker() {
            @Override
            public void apply(JobRequest jobRequest) {
                log.info("step1 jobName={}", jobRequest.getJobName());
                return;
            }
        }, "step1", 1, 1L));


        contextArrayList.add(new WorkerContext(new IWorker() {
            @Override
            public void apply(JobRequest jobRequest) {
                log.info("step2 jobName={}", jobRequest.getJobName());
                return;
            }
        }, "step2", 1, 1L));
        return contextArrayList;
    }
}
