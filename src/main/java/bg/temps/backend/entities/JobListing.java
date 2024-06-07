package bg.temps.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity(name = "job_listing")
@Table(name = "job_listing")
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Boolean isAvailable;

//    @Column
//    private String type; // struct

    //foreign key - userid - employer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    //if the status is closed shows which employee have worked here

    @ElementCollection()
    private List<String> requiredSkills;

    @Column()
    private String location;

    @Column()
    private Boolean status;
    @Column()
    private String experienceLevel;

    @ElementCollection()
    private List<Timestamp> availability;//start date - end date/hour

    @Column
    private Float salary;


    //    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public JobListing() {}

    public JobListing(Long job_id) {
        this.id = job_id;
    }

    public JobListing(String title, Boolean status, Employer employer, Employee employee, List<String> requiredSkills, String location, List<Timestamp> availability, Float salary) {
        this.title = title;
        this.isAvailable = status;
        this.employer = employer;
        this.employee = employee;
        this.requiredSkills = requiredSkills;
        this.location = location;
        this.availability = availability;
        this.salary = salary;
    }

//    public JobListing(String title, Boolean status, String type, Employer employer, Employee employee, List<String> skill, String location, List<Timestamp> availability, Float salary) {
//        this.title = title;
//        this.status = status;
//        this.type = type;
//        this.employer = employer;
//        this.employee = employee;
//        this.requiredSkills = skill;
//        this.location = location;
//        this.availability = availability;
//        this.salary = salary;
//    }
}
