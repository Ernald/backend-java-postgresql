package bg.temps.backend.repositories;

import bg.temps.backend.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByEmployeeId(Long employeeId);

    List<JobApplication> findByJobListingId(Long jobListingId);

    List<JobApplication> findByStatus(String status);

    List<JobApplication> findByApplicationDateBetween(Date startDate, Date endDate);

    List<JobApplication> findByResumeContaining(String keyword);
}


