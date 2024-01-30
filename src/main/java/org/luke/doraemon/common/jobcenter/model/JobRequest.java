package org.luke.doraemon.common.jobcenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    private String jobId;
    private String jobName;
    private String contextHolder;
}
