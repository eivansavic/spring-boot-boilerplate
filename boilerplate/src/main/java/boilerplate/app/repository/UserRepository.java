package boilerplate.app.repository;

import boilerplate.app.model.User;
import boilerplate.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);
}

