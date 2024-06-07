package bg.temps.backend.services;

import bg.temps.backend.entities.Employee;
import bg.temps.backend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

        Employee createEmployee(Employee employee);

        Employee getEmployeeById(Long id) throws ResourceNotFoundException;

        List<Employee> getAllEmployees();

        Employee updateEmployee(Long id, Employee employee) throws ResourceNotFoundException;

        void deleteEmployee(Long id) throws ResourceNotFoundException;

        List<Employee> findEmployeesBySkill(String skill);

        List<Employee> findEmployeesByLocation(String location);

        List<Employee> findEmployeesByInterest(List<String> interest);

        List<Employee> findEmployeesByPreference(String preference);

        List<Employee> getEmployeesByFirstName(String firstName);

        List<Employee> getEmployeesBySkill(String skill);

        List<Employee> getEmployeesByLocation(String location);

        List<Employee> getEmployeesByMinAge(int age);

        List<Employee> getEmployeesByMaxAge(int age);

        List<Employee> getEmployeesByExperience(List<String> experience);

        List<Employee> getEmployeesByAgeRange(int minAge, int maxAge);

        List<Employee> getEmployeesByLocationContaining(String location);

        List<Employee> getEmployeesByJobTitle(String jobTitle);

        List<Employee> getEmployeesByNotInterested(List<String> interest);

}


