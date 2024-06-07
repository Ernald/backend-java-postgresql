package bg.temps.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@Entity(name = "employer")
@DiscriminatorValue("EMPLOYER")
public class Employer extends User {

    @Column()
    private String businessName;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobListing> jobListings = new ArrayList<>();

    // industry

    //user -> role employee and employer extends user

    @Column()
    private String industry;

    public Employer(String businessName, List<JobListing> jobListings) {
        this.businessName = businessName;
        this.jobListings = jobListings;
    }

    public Employer(String firstName, String lastName, String userName, String email, String password, Long phoneNumber, List<String> location, Float rating, List<String> feedback, List<String> preferences, List<String> experience, List<String> skills, Set<Role> roles, String businessName, List<JobListing> jobListings) {
        super(firstName, lastName, userName, email, password, phoneNumber, location, rating, feedback, preferences, experience, skills, roles);
        this.businessName = businessName;
        this.jobListings = jobListings;
    }

    public Employer(){

    }

    public void setRole(Role employer) {
    }
}
