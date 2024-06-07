package bg.temps.backend.services;


import bg.temps.backend.entities.Role;
import bg.temps.backend.entities.User;
import bg.temps.backend.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User saveUser(User user) throws ResourceNotFoundException;

    void clearSave(User user);

    void addRoleToUser(String email, Role role) throws ResourceNotFoundException;

    Optional<User> getOptionalUser();

    int numberOfUsers();

    User getUserById(Long userId) throws ResourceNotFoundException;

    Page<User> getUsers(Pageable pageable);

    boolean usernameExists();

    void deleteUser(Long userId) throws ResourceNotFoundException;

    void saveUserDataAndFlush(User user);

    String getUsernameByAuthentication();

    List<User> getAllUsers();

    User createUser(User user) throws ResourceNotFoundException;

    User getUser(String username) throws ResourceNotFoundException;

    List<User> getUsers();

    User updateUser(Long id, User user) throws ResourceNotFoundException;

    void changePassword(Long id, String newPassword);

    User findUserByEmail(String email) throws ResourceNotFoundException;

    void updateUserRole(String email, Role role);

    User getUserByEmail(String email) throws ResourceNotFoundException;

    List<User> findUsersByLocation(String location);

    List<User> findUsersByRating(Float rating);

    List<User> findUsersBySkill(String skill);

    List<User> findUsersByRole(Role role);

    List<User> findUsersByFirstName(String firstName);

    List<User> findUsersByLastName(String lastName);

    List<User> findUsersByUserName(String userName);

}

