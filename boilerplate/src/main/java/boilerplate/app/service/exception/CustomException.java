package boilerplate.app.service.exception;

import boilerplate.app.helper.ResourceBundleUtil;
import lombok.Getter;
import boilerplate.app.helper.ResourceBundleUtil;

import java.util.Locale;

@Getter
public class CustomException extends Exception {

    private String localizedErrorMessage;

    private String errorDescription;

    protected CustomException(String resourceBundleExceptionKey, String languageCode) {
        this.localizedErrorMessage = translateErrorMessage(resourceBundleExceptionKey, new Locale(languageCode));
        this.errorDescription = translateErrorMessage(resourceBundleExceptionKey, Locale.ENGLISH);
    }

    protected CustomException(String resourceBundleExceptionKey) {
        this.localizedErrorMessage = translateErrorMessage(resourceBundleExceptionKey, Locale.ENGLISH);
        this.errorDescription = this.localizedErrorMessage;
    }

    private String translateErrorMessage(String resourceBundleExceptionKey, Locale locale) {
        return ResourceBundleUtil.getExceptionValue(locale, resourceBundleExceptionKey);
    }
}
