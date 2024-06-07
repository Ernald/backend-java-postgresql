package bg.temps.backend.repositories;

import bg.temps.backend.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findAllByFirstName(String name);

    List<Employee> findBySkillsContaining(String skill);

    List<Employee> findByLocation(String location);

    List<Employee> findByPreferencesContaining(String preference);

    List<Employee> findByInterestsContaining(List<String> interest);

    List<Employee> findByAgeGreaterThanEqual(int age);

    List<Employee> findByAgeLessThanEqual(int age);

    List<Employee> findByExperienceContaining(List<String> experience);

    @Query("SELECT e FROM employee e WHERE :minAge <= e.age AND e.age <= :maxAge")
    List<Employee> findByAgeBetween(int minAge, int maxAge);

    @Query("SELECT e FROM employee e WHERE :location IN ?1")
    List<Employee> findByLocationContaining(String location);

    List<Employee> findByJobListings_title(String jobTitle);

    List<Employee> findByNotInterestedContaining(List<String> interest);

}
