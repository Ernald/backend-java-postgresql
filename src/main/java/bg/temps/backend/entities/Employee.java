package bg.temps.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity(name = "employee")
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User{

    @Column()
    private int age;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> jobApplications = new ArrayList<>();

    @ElementCollection
    private List<String> experience;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobListing> jobListings = new ArrayList<>();
    //for the history of hiring

    //similar to preferences in user for employer and employee
    @ElementCollection()
    private List<String> notInterested;

    @ElementCollection
    private List<String> interests;


    public Employee(){

    }

    public Employee(String firstName, String lastName, String userName, String email, String password, Long phoneNumber, List<String> location, Float rating, List<String> feedback, List<String> preferences, List<String> experience, List<String> skills, Set<Role> roles, int age, List<JobApplication> jobApplications, List<String> experience1, List<JobListing> jobListings, List<String> notInterested) {
        super(firstName, lastName, userName, email, password, phoneNumber, location, rating, feedback, preferences, experience, skills, roles);
        this.age = age;
        this.jobApplications = jobApplications;
        this.experience = experience;
        this.jobListings = jobListings;
        this.notInterested = notInterested;
    }

    public void setRole(Role employee) {
    }

}
