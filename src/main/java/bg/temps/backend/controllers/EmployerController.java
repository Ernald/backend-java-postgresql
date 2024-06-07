package bg.temps.backend.controllers;

import bg.temps.backend.entities.Employer;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping ("/employer")
public class EmployerController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(@Lazy EmployerService employerService){
        this.employerService = employerService;
    }

    @GetMapping("/fetch")
    public List<Employer> getAllEmployers(){
        return (List<Employer>) ResponseEntity.ok(employerService.getAllEmployers());
    }

    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employerService.createEmployer(employer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(employerService.getEmployerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id, @RequestBody Employer employer) throws ResourceNotFoundException {
        return ResponseEntity.ok(employerService.updateEmployer(id, employer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) throws ResourceNotFoundException {
        employerService.deleteEmployer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/industry/{industry}")
    public ResponseEntity<List<Employer>> findEmployersByIndustry(@PathVariable String industry) {
        return ResponseEntity.ok(employerService.findEmployersByIndustry(industry));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Employer>> findEmployersByLocation(@PathVariable String location) {
        return ResponseEntity.ok(employerService.findEmployersByLocation(location));
    }

//    @GetMapping("/rating")
//    public ResponseEntity<List<Employer>> findEmployersByRating(@RequestParam double minRating, @RequestParam double maxRating) {
//        return ResponseEntity.ok(employerService.findEmployersByRating(minRating, maxRating));
//    }

    @GetMapping("/rating")
    public ResponseEntity<List<Employer>> findEmployersByRatingRange(@RequestParam double minRating, @RequestParam double maxRating) {
        return ResponseEntity.ok(employerService.findEmployersByRatingRange(minRating, maxRating));
    }

    @GetMapping("/businessName/{businessName}")
    public ResponseEntity<List<Employer>> findEmployersByBusinessName(@PathVariable String businessName) {
        return ResponseEntity.ok(employerService.findEmployersByBusinessName(businessName));
    }

    @GetMapping("/jobTitle/{jobTitle}")
    public ResponseEntity<List<Employer>> findEmployersByJobTitle(@PathVariable String jobTitle) {
        return ResponseEntity.ok(employerService.findEmployersByJobTitle(jobTitle));
    }

    @GetMapping("/industryContaining/{industry}")
    public ResponseEntity<List<Employer>> findEmployersByIndustryContaining(@PathVariable String industry) {
        return ResponseEntity.ok(employerService.findEmployersByIndustryContaining(industry));
    }

    @GetMapping("/locationContaining/{location}")
    public ResponseEntity<List<Employer>> findEmployersByLocationContaining(@PathVariable String location) {
        return ResponseEntity.ok(employerService.findEmployersByLocationContaining(location));
    }

    @GetMapping("/ratingGreaterThanEqual/{rating}")
    public ResponseEntity<List<Employer>> findEmployersByRatingGreaterThanEqual(@PathVariable double rating) {
        return ResponseEntity.ok(employerService.findEmployersByRatingGreaterThanEqual(rating));
    }

    @GetMapping("/ratingLessThanEqual/{rating}")
    public ResponseEntity<List<Employer>> findEmployersByRatingLessThanEqual(@PathVariable double rating) {
        return ResponseEntity.ok(employerService.findEmployersByRatingLessThanEqual(rating));
    }

}