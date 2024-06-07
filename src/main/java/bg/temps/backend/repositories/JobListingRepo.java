package bg.temps.backend.repositories;

import bg.temps.backend.entities.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobListingRepo extends JpaRepository <JobListing, Long> {

    List<JobListing> findByEmployerId(Long employerId);

    List<JobListing> findByRequiredSkillsContaining(String skill);

    List<JobListing> findByLocation(String location);

    List<JobListing> findBySalaryBetween(double minSalary, double maxSalary);

    List<JobListing> findByExperienceLevel(String experienceLevel);

    List<JobListing> findByTitleContaining(String title);

    List<JobListing> findByStatus(Boolean status);
}
