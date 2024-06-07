package bg.temps.backend.services;

import bg.temps.backend.entities.JobListing;
import bg.temps.backend.exception.ResourceNotFoundException;

import java.util.List;

public interface JobListingService {
    JobListing createJobListing(JobListing jobListing);

    JobListing getJobListingById(Long id) throws ResourceNotFoundException;

    List<JobListing> getAllJobListings();

    JobListing updateJobListing(Long id, JobListing jobListing) throws ResourceNotFoundException;

    void deleteJobListing(Long id) throws ResourceNotFoundException;

    List<JobListing> findJobListingsBySkill(String skill);

    List<JobListing> findJobListingsByEmployer(Long employerId);

    List<JobListing> findJobListingsByLocation(String location);

    List<JobListing> findJobListingsBySalaryRange(double minSalary, double maxSalary);

    List<JobListing> sortJobListingsByDate();

    List<JobListing> sortJobListingsBySalary();

    List<JobListing> findJobListingsByEmployerId(Long employerId);

    List<JobListing> findJobListingsByRequiredSkill(String skill);

    List<JobListing> findJobListingsByTitle(String title);

    List<JobListing> findJobListingsByStatus(Boolean status);
}