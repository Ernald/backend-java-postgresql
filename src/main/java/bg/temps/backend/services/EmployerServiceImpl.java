package bg.temps.backend.services;

import bg.temps.backend.entities.Employer;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.repositories.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepo employerRepo;

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepo.findAll();
    }

    @Override
    public Employer getEmployerById(Long id)  throws ResourceNotFoundException {
        return employerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employer not found"));
    }

    @Override
    public Employer createEmployer(Employer employer) {
        return employerRepo.save(employer);
    }

    @Override
    public Employer updateEmployer(Long id, Employer employer) throws ResourceNotFoundException{
        Employer existingEmployer = employerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employer not found"));
        existingEmployer.setBusinessName(employer.getBusinessName());
//        existingEmployer.setIndustry(employer.getIndustry());
        existingEmployer.setLocation(employer.getLocation());
        existingEmployer.setRating(employer.getRating());
        existingEmployer.setJobListings(employer.getJobListings());
        return employerRepo.save(existingEmployer);
    }

    @Override
    public void deleteEmployer(Long id) throws ResourceNotFoundException{
        Employer existingEmployer = employerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employer not found"));
        employerRepo.delete(existingEmployer);
    }

    @Override
    public List<Employer> findEmployersByIndustry(String industry) {
        return employerRepo.findByIndustry(industry);
    }

    @Override
    public List<Employer> findEmployersByLocation(String location) {
        return employerRepo.findByLocation(location);
    }

    @Override
    public List<Employer> findEmployersByRating(double minRating, double maxRating) {
        return employerRepo.findByRatingBetween(minRating, maxRating);
    }

//    @Override
//    public List<Employer> findEmployersByIndustry(String industry) {
//        return employerRepo.findByIndustry(industry);
//    }

    @Override
    public List<Employer> findEmployersByRatingRange(double minRating, double maxRating) {
        return employerRepo.findByRatingBetween(minRating, maxRating);
    }

    @Override
    public List<Employer> findEmployersByBusinessName(String businessName) {
        return employerRepo.findByBusinessNameContaining(businessName);
    }

    @Override
    public List<Employer> findEmployersByJobTitle(String jobTitle) {
        return employerRepo.findByJobListings_title(jobTitle);
    }

    @Override
    public List<Employer> findEmployersByIndustryContaining(String industry) {
        return employerRepo.findByIndustryContaining(industry);
    }

    @Override
    public List<Employer> findEmployersByLocationContaining(String location) {
        return employerRepo.findByLocationContaining(location);
    }

    @Override
    public List<Employer> findEmployersByRatingGreaterThanEqual(double rating) {
        return employerRepo.findByRatingGreaterThanEqual(rating);
    }

    @Override
    public List<Employer> findEmployersByRatingLessThanEqual(double rating) {
        return employerRepo.findByRatingLessThanEqual(rating);
    }

}
