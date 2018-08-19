package boilerplate.app.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "custom", ignoreUnknownFields = false)
public class CustomProperties {

    private String serverUrl;

    private Integer verificationTokenExpirationTimeInDays;
}
