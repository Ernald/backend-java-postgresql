package bg.temps.backend.services;

import bg.temps.backend.entities.Employee;
import bg.temps.backend.entities.Employer;
import bg.temps.backend.entities.Role;
import bg.temps.backend.entities.User;
import bg.temps.backend.exception.ResourceNotFoundException;
import bg.temps.backend.exception.UserNotFoundException;
import bg.temps.backend.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public User login(String username, String password) throws ResourceNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResourceNotFoundException("Invalid credentials");
        }
        return user;
    }

    public User createEmployer(Employer employer) throws ResourceNotFoundException {
        if (employer.getRoles() == null || employer.getRoles().isEmpty()) {
            employer.setRoles(Set.of(new Role(Role.RoleName.ROLE_EMPLOYER)));
        }
        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
        return userRepo.save(employer);
    }

    public User createEmployee(Employee employee) throws ResourceNotFoundException {
        if (employee.getRoles() == null || employee.getRoles().isEmpty()) {
            employee.setRoles(Set.of(new Role(Role.RoleName.ROLE_EMPLOYEE)));
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return userRepo.save(employee);
    }

    public User updateUser(Long id, User user) throws ResourceNotFoundException {
        User existingUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());
        return userRepo.save(existingUser);
    }

    public void deleteUser(Long id) throws ResourceNotFoundException {
        userRepo.deleteById(id);
    }

    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<User> findUsersByFirstName(String firstName) {
        return userRepo.findByFirstNameContaining(firstName);
    }

    public List<User> findUsersByLastName(String lastName) {
        return userRepo.findByLastNameContaining(lastName);
    }

    public List<User> findUsersByUserName(String userName) {
        return userRepo.findByUsernameContaining(userName);
    }

    public List<User> findUsersByLocation(String location) {
        return userRepo.findByLocationContaining(location);
    }

    public List<User> findUsersByRating(Float rating) {
        return userRepo.findByRatingGreaterThanEqual(rating);
    }

    public List<User> findUsersBySkill(String skill) {
        return userRepo.findBySkillsContaining(skill);
    }

    public List<User> findUsersByRole(Role role) {
        return userRepo.findByRoles(role);
    }
}