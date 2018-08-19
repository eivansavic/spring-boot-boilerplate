package boilerplate.app.util;

import java.time.LocalDateTime;
import java.util.UUID;

public class VerificationTokenUtil {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static LocalDateTime calculateExpiryDate(int expirationTimeInDays) {
        return LocalDateTime.now().plusDays(expirationTimeInDays);
    }
}
