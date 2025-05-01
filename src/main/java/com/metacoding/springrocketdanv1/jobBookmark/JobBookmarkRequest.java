package com.metacoding.springrocketdanv1.jobBookmark;

import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.user.User;
import lombok.Data;

public class JobBookmarkRequest {

    @Data
    public static class SaveDTO {
        private Integer jobId;

        public JobBookmark toEntity(User user) {
            return new JobBookmark(user, Job.builder().id(jobId).build());
        }
    }
}
