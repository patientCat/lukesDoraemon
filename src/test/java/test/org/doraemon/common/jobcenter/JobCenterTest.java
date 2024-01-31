package test.org.doraemon.common.jobcenter;

import org.junit.Test;
import org.luke.doraemon.common.jobcenter.model.JobRequest;

public class JobCenterTest {

    @Test
    public void testJobCenter(){
        ExampleJobCenter exampleJobCenter = new ExampleJobCenter();
        JobRequest jobRequest = new JobRequest("001", "testJob", "context");
        exampleJobCenter.run(jobRequest);
    }

    @Test
    public void testJobCenterAfterFail(){
        ExampleJobCenter exampleJobCenter = new ExampleJobCenter();
        JobRequest jobRequest = new JobRequest("001", "testJob", "context");
        exampleJobCenter.run(jobRequest);
    }
}
