package test.org.doraemon.spock.common


import org.luke.doraemon.common.jobcenter.model.JobRequest
import org.luke.doraemon.common.jobcenter.repo.IJobStatusRepo
import spock.lang.Specification
import test.org.doraemon.common.jobcenter.ExampleJobCenter
import test.org.doraemon.common.jobcenter.StatusJobCenter

class JobCenterSpec extends Specification{
    def "test job center"(){
        given:
        ExampleJobCenter exampleJobCenter = new ExampleJobCenter()
        JobRequest jobRequest = new JobRequest("001", "testJob", "context");
        when:
        exampleJobCenter.run(jobRequest);
        then:
        1 == 1
    }

    def "test job center with status"(){
        given:
        def repo = Mock(IJobStatusRepo.class)
        repo.getWorkerStatus("001") >> ["step1":true]
        StatusJobCenter exampleJobCenter = new StatusJobCenter(repo)
        JobRequest jobRequest = new JobRequest("001", "testJob", "context");
        when:
        exampleJobCenter.run(jobRequest);
        then:
        1 == 1
    }
}
