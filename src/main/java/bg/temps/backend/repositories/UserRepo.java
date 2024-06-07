package bg.temps.backend.repositories;
//

import bg.temps.backend.entities.Role;
import bg.temps.backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    @Query("SELECT u "
            + "FROM User u")
    Page<User> filterUsers(Pageable pageable);

    Optional<User> findByUsername(String username);

    boolean existsById(Long id);

    boolean existsByUsername(String username);

//    User createUser(User user);
//
//    User updateUser(Long id, User user);
//

    User findByEmail(String email);

    List<User> findByLocationContaining(String location);

    List<User> findByRatingGreaterThanEqual(Float rating);

    List<User> findBySkillsContaining(String skill);

    List<User> findByRoles(Role role);

    List<User> findByFirstNameContaining(String firstName);

    List<User> findByLastNameContaining(String lastName);

    List<User> findByUsernameContaining(String userName);
}
