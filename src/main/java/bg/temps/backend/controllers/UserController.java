package bg.temps.backend.controllers;

import bg.temps.backend.entities.Employee;
import bg.temps.backend.entities.Employer;
import bg.temps.backend.entities.Role;
import bg.temps.backend.entities.User;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register/employer")
    public ResponseEntity<User> registerEmployer(@RequestBody Employer employer) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createEmployer(employer));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register/employee")
    public ResponseEntity<User> registerEmployee(@RequestBody Employee employee) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createEmployee(employee));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            User user = userService.login(username, password);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<User>> findUsersByFirstName(@PathVariable String firstName) {
        try {
            return ResponseEntity.ok(userService.findUsersByFirstName(firstName));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<User>> findUsersByLastName(@PathVariable String lastName) {
        try {
            return ResponseEntity.ok(userService.findUsersByLastName(lastName));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userName/{userName}")
    public ResponseEntity<List<User>> findUsersByUserName(@PathVariable String userName) {
        try {
            return ResponseEntity.ok(userService.findUsersByUserName(userName));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<User>> findUsersByLocation(@PathVariable String location) {
        try {
            return ResponseEntity.ok(userService.findUsersByLocation(location));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<User>> findUsersByRating(@PathVariable Float rating) {
        try {
            return ResponseEntity.ok(userService.findUsersByRating(rating));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<User>> findUsersBySkill(@PathVariable String skill) {
        try {
            return ResponseEntity.ok(userService.findUsersBySkill(skill));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> findUsersByRole(@PathVariable Role role) {
        try {
            return ResponseEntity.ok(userService.findUsersByRole(role));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static Pageable createPagination(Integer currentPage, Integer perPage, int size) {
        Pageable pageable;
        if ((currentPage != null && perPage != null) && (currentPage > 0 && perPage > 0)) {
            pageable = PageRequest.of(currentPage - 1, perPage);
        } else if (currentPage == null && perPage == null) {
            pageable = PageRequest.of(0, size);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The value of currentPage and/or perPage parameters cannot be under or equal to 0.");
        }
        return pageable;
    }
}