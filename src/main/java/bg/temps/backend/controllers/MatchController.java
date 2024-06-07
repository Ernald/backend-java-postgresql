package bg.temps.backend.controllers;

import bg.temps.backend.entities.JobListing;
import bg.temps.backend.entities.User;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.services.JobListingService;
import bg.temps.backend.services.MatchServiceImpl;
import bg.temps.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchServiceImpl matchService;

    private final UserService userService;

    private final JobListingService jobListingService;

    @Autowired
    public MatchController(@Lazy MatchServiceImpl matchService, @Lazy UserService userService, @Lazy JobListingService jobListingService) {
        this.matchService = matchService;
        this.userService = userService;
        this.jobListingService = jobListingService;
    }

    @GetMapping("/job-listings")
    public ResponseEntity<List<JobListing>> getMatchedJobListings(Authentication authentication) throws ResourceNotFoundException {
        User user = userService.getUser(authentication.getName());
        List<JobListing> matchedJobListings = matchService.matchJobListings(user);
        return ResponseEntity.ok(matchedJobListings);
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<User>> getMatchedApplicants(@RequestParam Long jobListingId) throws ResourceNotFoundException {
        JobListing jobListing = jobListingService.getJobListingById(jobListingId);
        List<User> matchedApplicants = matchService.matchApplicants(jobListing);
        return ResponseEntity.ok(matchedApplicants);
    }

}