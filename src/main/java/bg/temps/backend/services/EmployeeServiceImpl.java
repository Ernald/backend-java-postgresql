package bg.temps.backend.services;

import bg.temps.backend.entities.Employee;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) throws ResourceNotFoundException {
        return employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) throws ResourceNotFoundException {
        Employee existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setExperience(employee.getExperience());
        existingEmployee.setJobApplications(employee.getJobApplications());
        existingEmployee.setJobListings(employee.getJobListings());
        existingEmployee.setNotInterested(employee.getNotInterested());
        return employeeRepo.save(existingEmployee);

    }

    @Override
    public void deleteEmployee(Long id) throws ResourceNotFoundException {
        Employee existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepo.delete(existingEmployee);
    }

    @Override
    public List<Employee> findEmployeesBySkill(String skill) {
        return employeeRepo.findBySkillsContaining(skill);
    }

    @Override
    public List<Employee> findEmployeesByLocation(String location) {
        return employeeRepo.findByLocation(location);
    }

    @Override
    public List<Employee> findEmployeesByInterest(List<String> interest) {
        return employeeRepo.findByInterestsContaining(interest);
    }

    @Override
    public List<Employee> getEmployeesByFirstName(String firstName) {
        return employeeRepo.findAllByFirstName(firstName);
    }

    @Override
    public List<Employee> getEmployeesBySkill(String skill) {
        return employeeRepo.findBySkillsContaining(skill);
    }

    @Override
    public List<Employee> getEmployeesByLocation(String location) {
        return employeeRepo.findByLocation(location);
    }

    @Override
    public List<Employee> getEmployeesByMinAge(int age) {
        return employeeRepo.findByAgeGreaterThanEqual(age);
    }

    @Override
    public List<Employee> getEmployeesByMaxAge(int age) {
        return employeeRepo.findByAgeLessThanEqual(age);
    }

    @Override
    public List<Employee> getEmployeesByExperience(List<String> experience) {
        return employeeRepo.findByExperienceContaining(experience);
    }

    @Override
    public List<Employee> getEmployeesByAgeRange(int minAge, int maxAge) {
        return employeeRepo.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public List<Employee> getEmployeesByLocationContaining(String location) {
        return employeeRepo.findByLocationContaining(location);
    }

    @Override
    public List<Employee> getEmployeesByJobTitle(String jobTitle) {
        return employeeRepo.findByJobListings_title(jobTitle);
    }

    @Override
    public List<Employee> getEmployeesByNotInterested(List<String> interest) {
        return employeeRepo.findByNotInterestedContaining(interest);
    }

    @Override
    public List<Employee> findEmployeesByPreference(String preference) {
        return employeeRepo.findByPreferencesContaining(preference);
    }


}