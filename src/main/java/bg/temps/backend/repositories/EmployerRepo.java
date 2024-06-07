package bg.temps.backend.repositories;

import bg.temps.backend.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployerRepo extends JpaRepository<Employer, Long> {

    List<Employer> findByIndustry(String industry);

    List<Employer> findByLocation(String location);

    List<Employer> findByRatingBetween(double minRating, double maxRating);

    List<Employer> findByBusinessNameContaining(String businessName);

    List<Employer> findByJobListings_title(String jobTitle);

    List<Employer> findByIndustryContaining(String industry);

    List<Employer> findByLocationContaining(String location);

    @Query("SELECT e FROM employer e WHERE e.rating >= :rating")
    List<Employer> findByRatingGreaterThanEqual(double rating);

    @Query("SELECT e FROM employer e WHERE e.rating <= :rating")
    List<Employer> findByRatingLessThanEqual(double rating);

}
