package boilerplate.app.repository;

import boilerplate.app.model.User;
import boilerplate.app.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findOneByUser(User user);

    Optional<VerificationToken> findOneByToken(String token);
}
