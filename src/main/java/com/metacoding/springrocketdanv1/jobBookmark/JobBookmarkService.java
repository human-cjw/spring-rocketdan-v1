package com.metacoding.springrocketdanv1.jobBookmark;

import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.job.JobRepository;
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobBookmarkService {
    private final JobBookmarkRepository jobBookmarkRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Transactional
    public void 북마크토글(JobBookmarkRequest.SaveDTO reqDTO, Integer sessionUserId) {
        JobBookmark bookmark = jobBookmarkRepository.findByUserIdAndJobId(sessionUserId, reqDTO.getJobId());

        if (bookmark == null) {
            User user = userRepository.findById(sessionUserId);
            jobBookmarkRepository.save(reqDTO.toEntity(user));
        } else {
            jobBookmarkRepository.delete(bookmark);
        }
    }

    public List<JobBookmarkResponse.JobListWithBookmarkDTO> getAllJobsWithBookmarkInfo(Integer sessionUserId) {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(job -> {
                    boolean isBookmarked = jobBookmarkRepository.findByUserIdAndJobId(sessionUserId, job.getId()) != null;
                    return new JobBookmarkResponse.JobListWithBookmarkDTO(job, isBookmarked);
                })
                .toList();
    }

    public Long count(Integer sessionUserId) {
        return jobBookmarkRepository.countByUserId(sessionUserId);
    }

    public List<JobBookmarkResponse.BookmarkListDTO> getBookmarkList(Integer userId) {
        List<JobBookmark> bookmarkList = jobBookmarkRepository.findAllByUserId(userId);

        return bookmarkList.stream()
                .map(bookmark -> new JobBookmarkResponse.BookmarkListDTO(bookmark))
                .toList();
    }

    @Transactional
    public void 북마크삭제(Integer bookmarkId) {
        JobBookmark bookmark = jobBookmarkRepository.findById(bookmarkId);
        if (bookmark != null) {
            jobBookmarkRepository.delete(bookmark);
        }
    }

}
