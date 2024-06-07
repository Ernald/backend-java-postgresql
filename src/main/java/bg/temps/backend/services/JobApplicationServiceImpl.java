package bg.temps.backend.services;

import bg.temps.backend.entities.JobApplication;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.repositories.JobApplicationRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {
    @Autowired
    private final JobApplicationRepo jobApplicationRepo;

    public JobApplicationServiceImpl(JobApplicationRepo jobApplicationRepo) {
        this.jobApplicationRepo = jobApplicationRepo;
    }

    @Override
    public JobApplication createJobApplication(JobApplication jobApplication) {
        return jobApplicationRepo.save(jobApplication);
    }

    @Override
    public JobApplication getJobApplicationById(Long id) throws ResourceNotFoundException {
        return jobApplicationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("JobApplication not found"));
    }

    @Override
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepo.findAll();
    }


    @Override
    public List<JobApplication> findJobApplicationsByEmployee(Long employeeId) {
        return jobApplicationRepo.findByEmployeeId(employeeId);
    }

    @Override
    public List<JobApplication> findJobApplicationsByJobListing(Long jobListingId) {
        return jobApplicationRepo.findByJobListingId(jobListingId);
    }
    @Override
    public JobApplication updateJobApplication(Long id, JobApplication jobApplication) throws ResourceNotFoundException {
        JobApplication existingJobApplication = getJobApplicationById(id);
        existingJobApplication.setStatus(jobApplication.getStatus());
        existingJobApplication.setApplicationDate(jobApplication.getApplicationDate());
        existingJobApplication.setResume(jobApplication.getResume());
        existingJobApplication.setJobListing(jobApplication.getJobListing());
        existingJobApplication.setEmployee(jobApplication.getEmployee());
        return jobApplicationRepo.save(existingJobApplication);
    }

    @Override
    public void deleteJobApplication(Long id) throws ResourceNotFoundException {
        jobApplicationRepo.deleteById(id);
    }

    @Override
    public List<JobApplication> findJobApplicationsByEmployeeId(Long employeeId) {
        return jobApplicationRepo.findByEmployeeId(employeeId);
    }

    @Override
    public List<JobApplication> findJobApplicationsByJobListingId(Long jobListingId) {
        return jobApplicationRepo.findByJobListingId(jobListingId);
    }

    @Override
    public List<JobApplication> findJobApplicationsByStatus(String status) {
        return jobApplicationRepo.findByStatus(status);
    }

    @Override
    public List<JobApplication> findJobApplicationsByApplicationDateRange(Date startDate, Date endDate) {
        return jobApplicationRepo.findByApplicationDateBetween(startDate, endDate);
    }

    @Override
    public List<JobApplication> findJobApplicationsByResumeKeyword(String keyword) {
        return jobApplicationRepo.findByResumeContaining(keyword);
    }
}
