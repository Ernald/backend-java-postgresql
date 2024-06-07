package bg.temps.backend.services;

import bg.temps.backend.entities.Employer;
import bg.temps.backend.exception.ResourceNotFoundException;

import java.util.List;

public interface EmployerService {

    List<Employer> getAllEmployers();

    Employer getEmployerById(Long id) throws ResourceNotFoundException;

    Employer createEmployer(Employer employer);

    Employer updateEmployer(Long id, Employer employer) throws ResourceNotFoundException;

    void deleteEmployer(Long id) throws ResourceNotFoundException;

    List<Employer> findEmployersByIndustry(String industry);

    List<Employer> findEmployersByLocation(String location);

    List<Employer> findEmployersByRating(double minRating, double maxRating);

    List<Employer> findEmployersByRatingRange(double minRating, double maxRating);

    List<Employer> findEmployersByBusinessName(String businessName);

    List<Employer> findEmployersByJobTitle(String jobTitle);

    List<Employer> findEmployersByIndustryContaining(String industry);

    List<Employer> findEmployersByLocationContaining(String location);

    List<Employer> findEmployersByRatingGreaterThanEqual(double rating);

    List<Employer> findEmployersByRatingLessThanEqual(double rating);
}