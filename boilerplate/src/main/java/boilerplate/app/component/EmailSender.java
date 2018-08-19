package boilerplate.app.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void send(String to, String subject, String templateName, Context context) {
        String body = templateEngine.process(templateName, context);
        sendMail(to, subject, body, true);
    }

    @Async
    public void send(String to, String subject, String text) {
        sendMail(to, subject, text, false);
    }

    private void sendMail(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
            logger.info("Send email '{}' to: {}", subject, to);
        } catch (Exception e) {
            logger.error(String.format("Problem with sending email to: %s, error message: %s", to, e.getMessage()));
        }
    }
}
