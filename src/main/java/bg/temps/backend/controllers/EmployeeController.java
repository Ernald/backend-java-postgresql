package bg.temps.backend.controllers;

import bg.temps.backend.entities.Employee;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping ("/employee")
//using orm - object relational mapping with jpa and hibernate
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(@Lazy EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/find/name")
    public ResponseEntity<List<Employee>> findEmployeeByName(String name){
        return ResponseEntity.ok(employeeService.getEmployeesByFirstName(name));

    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody @Valid Employee employee){
        try {
            return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) throws ResourceNotFoundException {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/skill/{skill}")
//    public ResponseEntity<List<Employee>> findEmployeesBySkill(@PathVariable String skill) {
//        return ResponseEntity.ok(employeeService.findEmployeesBySkill(skill));
//    }

//    @GetMapping("/location/{location}")
//    public ResponseEntity<List<Employee>> findEmployeesByLocation(@PathVariable String location) {
//        return ResponseEntity.ok(employeeService.findEmployeesByLocation(location));
//    }


//    @GetMapping("/find/name/vulnerable")
//    public ResponseEntity<?> findEmployeeByNameVulnerable(String name) {
//        try(Connection con = DriverManager
//                .getConnection("jdbc:postgresql://localhost:5432/Temps", "postgres", "admin")){
//            try(Statement stmt = con.createStatement()){
//                String findSql = "SELECT * FROM employees WHERE first_name='" + name + "';";
//                ResultSet result = stmt.executeQuery(findSql);
//                List<Employee> employees = new ArrayList<>();
//                while(result.next()){
//                    employees.add(new Employee(result.getString("firstName"),
//                            result.getString("lastName"),
//                            result.getInt("age")));
//                }
//                return ResponseEntity.ok(employees);
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return  ResponseEntity.ok("No result!");
//    }


    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<Employee>> getEmployeesByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(employeeService.getEmployeesByFirstName(firstName));
    }

    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<Employee>> getEmployeesBySkill(@PathVariable String skill) {
        return ResponseEntity.ok(employeeService.getEmployeesBySkill(skill));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Employee>> getEmployeesByLocation(@PathVariable String location) {
        return ResponseEntity.ok(employeeService.getEmployeesByLocation(location));
    }

    @GetMapping("/minAge/{age}")
    public ResponseEntity<List<Employee>> getEmployeesByMinAge(@PathVariable int age) {
        return ResponseEntity.ok(employeeService.getEmployeesByMinAge(age));
    }

    @GetMapping("/maxAge/{age}")
    public ResponseEntity<List<Employee>> getEmployeesByMaxAge(@PathVariable int age) {
        return ResponseEntity.ok(employeeService.getEmployeesByMaxAge(age));
    }

    @GetMapping("/experience/{experience}")
    public ResponseEntity<List<Employee>> getEmployeesByExperience(@PathVariable List<String> experience) {
        return ResponseEntity.ok(employeeService.getEmployeesByExperience(experience));
    }

    @GetMapping("/ageRange")
    public ResponseEntity<List<Employee>> getEmployeesByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        return ResponseEntity.ok(employeeService.getEmployeesByAgeRange(minAge, maxAge));
    }

    @GetMapping("/locationContaining/{location}")
    public ResponseEntity<List<Employee>> getEmployeesByLocationContaining(@PathVariable String location) {
        return ResponseEntity.ok(employeeService.getEmployeesByLocationContaining(location));
    }

    @GetMapping("/jobTitle/{jobTitle}")
    public ResponseEntity<List<Employee>> getEmployeesByJobTitle(@PathVariable String jobTitle) {
        return ResponseEntity.ok(employeeService.getEmployeesByJobTitle(jobTitle));
    }

    @GetMapping("/interested/{interest}")
    public ResponseEntity<List<Employee>> findEmployeesByInterest(@PathVariable List<String> interest) {
        return ResponseEntity.ok(employeeService.findEmployeesByInterest(interest));
    }

    @GetMapping("/notInterested/{interest}")
    public ResponseEntity<List<Employee>> getEmployeesByNotInterested(@PathVariable List<String> interest) {
        return ResponseEntity.ok(employeeService.getEmployeesByNotInterested(interest));
    }

    @GetMapping("/preference/{preference}")
    public List<Employee> getEmployeesByPreference(@PathVariable String preference) {
        return employeeService.findEmployeesByPreference(preference);
    }
}

