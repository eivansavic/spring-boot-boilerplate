package boilerplate.app.helper;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class ResourceBundleUtil {
    final static private String RESOURCE_BUNDLE_EXCEPTION = "i18n.exception";

    final static private ResourceBundle.Control resourceControl = new UTF8ResourceBundleControl();

    public static String getExceptionValue(Locale locale, String resourceBundleKey) {
        return getResource(locale, RESOURCE_BUNDLE_EXCEPTION, resourceBundleKey);
    }

    private static String getResource(Locale locale, String resourceBundleName, String resourceBundleKey) {
        return ResourceBundle.getBundle(resourceBundleName, locale, resourceControl).getString(resourceBundleKey);
    }
}
