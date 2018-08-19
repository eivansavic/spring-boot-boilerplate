package boilerplate.app.service.usecase.authentication;

import boilerplate.app.model.User;
import boilerplate.app.model.VerificationToken;
import boilerplate.app.repository.VerificationTokenRepository;
import boilerplate.app.service.usecase.authentication.exception.VerificationTokenInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CheckVerificationTokenValidity {

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public CheckVerificationTokenValidity(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    VerificationToken check(User user, String token) throws VerificationTokenInvalidException {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findOneByToken(token);
        if (verificationTokenOptional.isPresent()) {
            if (!verificationTokenOptional.get().getUser().equals(user) || LocalDateTime.now().isAfter(verificationTokenOptional.get().getExpiryDate())) {
                throw new VerificationTokenInvalidException();
            }
        } else {
            throw new VerificationTokenInvalidException();
        }
        return verificationTokenOptional.get();
    }
}
