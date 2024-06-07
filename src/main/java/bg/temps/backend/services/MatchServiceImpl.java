package bg.temps.backend.services;

import bg.temps.backend.entities.JobListing;
import bg.temps.backend.entities.User;
import bg.temps.backend.repositories.JobListingRepo;
import bg.temps.backend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JobListingRepo jobListingRepo;

    public List<JobListing> matchJobListings(User user) {
        List<JobListing> jobListings = jobListingRepo.findAll();

        return jobListings.stream()
                .filter(jobListing -> matchByLocation(jobListing, user))
                .filter(jobListing -> matchBySkills(jobListing, user))
                .filter(jobListing -> matchByPreferences(jobListing, user))
                .filter(jobListing -> matchByAvailability(jobListing, user))
                .filter(jobListing -> matchByRating(jobListing, user))
                .sorted((j1, j2) -> Float.compare(j2.getEmployer().getRating(), j1.getEmployer().getRating()))
                .collect(Collectors.toList());
    }

    public List<User> matchApplicants(JobListing jobListing) {
        List<User> users = userRepo.findAll();

        return users.stream()
                .filter(user -> matchByLocation(jobListing, user))
                .filter(user -> matchBySkills(jobListing, user))
                .filter(user -> matchByPreferences(jobListing, user))
                .filter(user -> matchByAvailability(jobListing, user))
                .filter(user -> matchByRating(jobListing, user))
                .sorted((u1, u2) -> Float.compare(u2.getRating(), u1.getRating())) // Sort by rating
                .collect(Collectors.toList());
    }

    private boolean matchByLocation(JobListing jobListing, User user) {
        return user.getLocation().contains(jobListing.getLocation());
    }

    private boolean matchBySkills(JobListing jobListing, User user) {
        return user.getSkills().containsAll(jobListing.getRequiredSkills());
    }

    private boolean matchByPreferences(JobListing jobListing, User user) {
        return jobListing.getRequiredSkills().stream().anyMatch(user.getSkills()::contains);
    }

    private boolean matchByAvailability(JobListing jobListing, User user) {
        return jobListing.getIsAvailable();
    }


    private boolean matchByRating(JobListing jobListing, User user) {
        return user.getRating() >= jobListing.getEmployer().getRating();
    }

}
