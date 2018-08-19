package boilerplate.app.service.usecase.authentication;

import boilerplate.app.component.EmailSender;
import boilerplate.app.configuration.CustomProperties;
import boilerplate.app.service.usecase.authentication.value.SendVerificationMailInfo;
import boilerplate.app.component.EmailSender;
import boilerplate.app.configuration.CustomProperties;
import boilerplate.app.service.usecase.authentication.value.SendVerificationMailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class SendVerificationMail {

    private final CustomProperties customProperties;
    private final EmailSender mailSender;

    @Autowired
    public SendVerificationMail(CustomProperties customProperties, EmailSender mailSender) {
        this.customProperties = customProperties;
        this.mailSender = mailSender;
    }

    public void send(SendVerificationMailInfo sendVerificationMailInfo) {
        Context context = new Context();
        context.setVariable("title", "Verification");
        context.setVariable("description", sendVerificationMailInfo.getEmail());
        context.setVariable("verification_link",
                customProperties.getServerUrl() +
                        "/authentication/email/verification?email=" + sendVerificationMailInfo.getEmail() +
                        "&token=" + sendVerificationMailInfo.getToken());
        mailSender.send(sendVerificationMailInfo.getEmail(),
                "Verification",
                "email_verification.html",
                context);
    }
}
