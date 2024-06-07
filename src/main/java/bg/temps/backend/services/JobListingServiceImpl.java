package bg.temps.backend.services;

import bg.temps.backend.entities.JobListing;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.repositories.JobListingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class JobListingServiceImpl implements JobListingService {
    @Autowired
    private final JobListingRepo jobListingRepo;

    public JobListingServiceImpl(JobListingRepo jobListingRepo) {
        this.jobListingRepo = jobListingRepo;
    }

    @Override
    public JobListing createJobListing(JobListing jobListing) {
        return jobListingRepo.save(jobListing);
    }

    @Override
    public JobListing getJobListingById(Long id) throws ResourceNotFoundException {
        return jobListingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("JobListing not found"));
    }

    @Override
    public List<JobListing> getAllJobListings() {
        return jobListingRepo.findAll();
    }


    @Override
    public void deleteJobListing(Long id) {
        jobListingRepo.deleteById(id);
    }

    @Override
    public List<JobListing> findJobListingsBySkill(String skill) {
        return jobListingRepo.findByRequiredSkillsContaining(skill);
    }

    @Override
    public List<JobListing> findJobListingsByEmployer(Long employerId) {
        return jobListingRepo.findByEmployerId(employerId);
    }

    @Override
    public List<JobListing> findJobListingsByLocation(String location) {
        return jobListingRepo.findByLocation(location);
    }

    @Override
    public List<JobListing> findJobListingsBySalaryRange(double minSalary, double maxSalary) {
        return jobListingRepo.findBySalaryBetween(minSalary, maxSalary);
    }


    @Override
    public List<JobListing> sortJobListingsByDate() {
        return jobListingRepo.findAll(Sort.by(Sort.Direction.DESC, "postedDate"));
    }

    @Override
    public List<JobListing> sortJobListingsBySalary() {
        return jobListingRepo.findAll(Sort.by(Sort.Direction.DESC, "salary"));
    }

    @Override
    public JobListing updateJobListing(Long id, JobListing jobListing) throws ResourceNotFoundException {
        JobListing existingJobListing = getJobListingById(id);
        existingJobListing.setTitle(jobListing.getTitle());
        existingJobListing.setIsAvailable(jobListing.getIsAvailable());
        existingJobListing.setLocation(jobListing.getLocation());
        existingJobListing.setSalary(jobListing.getSalary());
        existingJobListing.setRequiredSkills(jobListing.getRequiredSkills());
        existingJobListing.setEmployer(jobListing.getEmployer());
        return jobListingRepo.save(existingJobListing);
    }

    @Override
    public List<JobListing> findJobListingsByEmployerId(Long employerId) {
        return jobListingRepo.findByEmployerId(employerId);
    }

    @Override
    public List<JobListing> findJobListingsByRequiredSkill(String skill) {
        return jobListingRepo.findByRequiredSkillsContaining(skill);
    }

    @Override
    public List<JobListing> findJobListingsByTitle(String title) {
        return jobListingRepo.findByTitleContaining(title);
    }


    @Override
    public List<JobListing> findJobListingsByStatus(Boolean status) {
        return jobListingRepo.findByStatus(status);
    }
}
