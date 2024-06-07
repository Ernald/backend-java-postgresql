package bg.temps.backend.services;

import bg.temps.backend.entities.JobApplication;
import bg.temps.backend.exception.ResourceNotFoundException;

import java.util.Date;
import java.util.List;

public interface JobApplicationService {
    JobApplication createJobApplication(JobApplication jobApplication);

    JobApplication getJobApplicationById(Long id) throws ResourceNotFoundException;

    List<JobApplication> getAllJobApplications();

    JobApplication updateJobApplication(Long id, JobApplication jobApplication) throws ResourceNotFoundException;

    void deleteJobApplication(Long id) throws ResourceNotFoundException;

    List<JobApplication> findJobApplicationsByEmployee(Long employeeId);

    List<JobApplication> findJobApplicationsByJobListing(Long jobListingId);

    List<JobApplication> findJobApplicationsByEmployeeId(Long employeeId);

    List<JobApplication> findJobApplicationsByJobListingId(Long jobListingId);

    List<JobApplication> findJobApplicationsByStatus(String status);

    List<JobApplication> findJobApplicationsByApplicationDateRange(Date startDate, Date endDate);

    List<JobApplication> findJobApplicationsByResumeKeyword(String keyword);
}