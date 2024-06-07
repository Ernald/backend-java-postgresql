package bg.temps.backend.entities;


import jakarta.persistence.*;

import java.util.Date;
//import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_listing_id", nullable = false)
    private JobListing jobListing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column()
    private String status;

    @Column
    private Date applicationDate;

    @Column
    private String resume;


    public JobApplication() {
    }

    public JobApplication(JobListing jobListing, Employee employee, String status, Date applicationDate, String resume) {
        this.jobListing = jobListing;
        this.employee = employee;
        this.status = status;
        this.applicationDate = applicationDate;
        this.resume = resume;
    }

    public JobApplication(JobListing jobListing, Employee employee, String status) {
        this.jobListing = jobListing;
        this.employee = employee;
        this.status = status;
    }

    public JobListing getJobListing() {
        return jobListing;
    }

    public void setJobListing(JobListing jobListing) {
        this.jobListing = jobListing;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
