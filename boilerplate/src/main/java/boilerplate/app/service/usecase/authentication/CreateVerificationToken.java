package boilerplate.app.service.usecase.authentication;

import boilerplate.app.configuration.CustomProperties;
import boilerplate.app.repository.VerificationTokenRepository;
import boilerplate.app.configuration.CustomProperties;
import boilerplate.app.model.User;
import boilerplate.app.model.VerificationToken;
import boilerplate.app.repository.VerificationTokenRepository;
import boilerplate.app.util.VerificationTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateVerificationToken {

    private final VerificationTokenRepository verificationTokenRepository;
    private final CustomProperties customProperties;

    @Autowired
    public CreateVerificationToken(VerificationTokenRepository verificationTokenRepository, CustomProperties customProperties) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.customProperties = customProperties;
    }

    public String create(User user) {
        final String token = VerificationTokenUtil.generateToken();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(VerificationTokenUtil.calculateExpiryDate(customProperties.getVerificationTokenExpirationTimeInDays()));
        verificationToken.setUser(user);
        verificationToken.setCreatedAt(LocalDateTime.now());
        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
