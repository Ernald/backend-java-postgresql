package bg.temps.backend.controllers;

import bg.temps.backend.entities.JobApplication;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/jobapplications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @Autowired
    public JobApplicationController(@Lazy JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobApplication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationService.createJobApplication(jobApplication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getJobApplicationById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(jobApplicationService.getJobApplicationById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllJobApplications() {
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateJobApplication(@PathVariable Long id, @RequestBody JobApplication jobApplication) throws ResourceNotFoundException {
        return ResponseEntity.ok(jobApplicationService.updateJobApplication(id, jobApplication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) throws ResourceNotFoundException {
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/employee/{employeeId}")
//    public ResponseEntity<List<JobApplication>> findJobApplicationsByEmployee(@PathVariable Long employeeId) {
//        return ResponseEntity.ok(jobApplicationService.findJobApplicationsByEmployee(employeeId));
//    }

    @GetMapping("/joblisting/{jobListingId}")
    public ResponseEntity<List<JobApplication>> findJobApplicationsByJobListing(@PathVariable Long jobListingId) {
        return ResponseEntity.ok(jobApplicationService.findJobApplicationsByJobListing(jobListingId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<JobApplication>> findJobApplicationsByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(jobApplicationService.findJobApplicationsByEmployeeId(employeeId));
    }

    @GetMapping("/jobListing/{jobListingId}")
    public ResponseEntity<List<JobApplication>> findJobApplicationsByJobListingId(@PathVariable Long jobListingId) {
        return ResponseEntity.ok(jobApplicationService.findJobApplicationsByJobListingId(jobListingId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobApplication>> findJobApplicationsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(jobApplicationService.findJobApplicationsByStatus(status));
    }

    @GetMapping("/dateRange")
    public ResponseEntity<List<JobApplication>> findJobApplicationsByApplicationDateRange(
            @RequestParam Date startDate, @RequestParam Date endDate) {
        return ResponseEntity.ok(jobApplicationService.findJobApplicationsByApplicationDateRange(startDate, endDate));
    }

    @GetMapping("/resumeKeyword/{keyword}")
    public ResponseEntity<List<JobApplication>> findJobApplicationsByResumeKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(jobApplicationService.findJobApplicationsByResumeKeyword(keyword));
    }
}

