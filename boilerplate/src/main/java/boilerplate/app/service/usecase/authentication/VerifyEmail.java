package boilerplate.app.service.usecase.authentication;

import boilerplate.app.model.User;
import boilerplate.app.model.VerificationToken;
import boilerplate.app.repository.UserRepository;
import boilerplate.app.repository.VerificationTokenRepository;
import boilerplate.app.service.usecase.authentication.exception.VerificationTokenInvalidException;
import boilerplate.app.service.usecase.authentication.value.VerifyEmailInfo;
import boilerplate.app.service.usecase.user.GetUserByEmail;
import boilerplate.app.service.usecase.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerifyEmail {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final GetUserByEmail getUserByEmail;
    private final CheckVerificationTokenValidity checkVerificationTokenValidity;

    @Autowired
    public VerifyEmail(VerificationTokenRepository verificationTokenRepository, UserRepository userRepository, GetUserByEmail getUserByEmail, CheckVerificationTokenValidity checkVerificationTokenValidity) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
        this.getUserByEmail = getUserByEmail;
        this.checkVerificationTokenValidity = checkVerificationTokenValidity;
    }

    public void verify(VerifyEmailInfo verifyEmailInfo) throws UserNotFoundException, VerificationTokenInvalidException {
        User user = getUserByEmail.getUserByEmail(verifyEmailInfo.getEmail());
        VerificationToken verificationToken = checkVerificationTokenValidity.check(user, verifyEmailInfo.getVerificationToken());
        verifyUserEmail(user);
        invalidateVerificationToken(verificationToken);
    }

    private void verifyUserEmail(User user) {
        user.setEmailVerified(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    private void invalidateVerificationToken(VerificationToken verificationToken) {
        verificationToken.setUpdatedAt(LocalDateTime.now());
        verificationToken.setExpiryDate(LocalDateTime.now());
        verificationTokenRepository.save(verificationToken);
    }
}
