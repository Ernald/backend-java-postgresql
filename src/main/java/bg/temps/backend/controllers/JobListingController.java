package bg.temps.backend.controllers;

import bg.temps.backend.entities.JobListing;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.services.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/job-listings")
public class JobListingController {

    private final JobListingService jobListingService;

    @Autowired
    public JobListingController(@Lazy JobListingService jobListingService) {
        this.jobListingService = jobListingService;
    }

    @PostMapping
    public ResponseEntity<JobListing> createJobListing(@RequestBody JobListing jobListing) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobListingService.createJobListing(jobListing));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobListing> getJobListingById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(jobListingService.getJobListingById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobListing>> getAllJobListings() {
        return ResponseEntity.ok(jobListingService.getAllJobListings());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobListing> updateJobListing(@PathVariable Long id, @RequestBody JobListing jobListing) throws ResourceNotFoundException {
        return ResponseEntity.ok(jobListingService.updateJobListing(id, jobListing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobListing(@PathVariable Long id) throws ResourceNotFoundException {
        jobListingService.deleteJobListing(id);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints
    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<JobListing>> findJobListingsBySkill(@PathVariable String skill) {
        return ResponseEntity.ok(jobListingService.findJobListingsBySkill(skill));
    }

//    @GetMapping("/employer/{employerId}")
//    public ResponseEntity<List<JobListing>> findJobListingsByEmployer(@PathVariable Long employerId) {
//        return ResponseEntity.ok(jobListingService.findJobListingsByEmployer(employerId));
//    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<JobListing>> findJobListingsByLocation(@PathVariable String location) {
        return ResponseEntity.ok(jobListingService.findJobListingsByLocation(location));
    }

    @GetMapping("/salary")
    public ResponseEntity<List<JobListing>> findJobListingsBySalaryRange(@RequestParam double minSalary, @RequestParam double maxSalary) {
        return ResponseEntity.ok(jobListingService.findJobListingsBySalaryRange(minSalary, maxSalary));
    }


    @GetMapping("/sort/date")
    public ResponseEntity<List<JobListing>> sortJobListingsByDate() {
        return ResponseEntity.ok(jobListingService.sortJobListingsByDate());
    }

    @GetMapping("/sort/salary")
    public ResponseEntity<List<JobListing>> sortJobListingsBySalary() {
        return ResponseEntity.ok(jobListingService.sortJobListingsBySalary());
    }

    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobListing>> findJobListingsByEmployerId(@PathVariable Long employerId) {
        return ResponseEntity.ok(jobListingService.findJobListingsByEmployerId(employerId));
    }

    @GetMapping("/required-skill/{skill}")
    public ResponseEntity<List<JobListing>> findJobListingsByRequiredSkill(@PathVariable String skill) {
        return ResponseEntity.ok(jobListingService.findJobListingsByRequiredSkill(skill));
    }


    @GetMapping("/title/{title}")
    public ResponseEntity<List<JobListing>> findJobListingsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(jobListingService.findJobListingsByTitle(title));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobListing>> findJobListingsByStatus(@PathVariable Boolean status) {
        return ResponseEntity.ok(jobListingService.findJobListingsByStatus(status));
    }
}


