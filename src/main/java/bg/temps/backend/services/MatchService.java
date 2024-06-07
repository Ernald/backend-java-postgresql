package bg.temps.backend.services;

import bg.temps.backend.entities.JobListing;
import bg.temps.backend.entities.User;

import java.util.List;

public interface MatchService {
    List<JobListing> matchJobListings(User user);
    List<User> matchApplicants(JobListing jobListing);
}