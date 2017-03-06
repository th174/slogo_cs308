package SLogo.Parse;

import java.util.ResourceBundle;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Parser {
    String RESOURCES_LOCATION = "resources.languages/";
    String DEFAULT_LOCALE = "English";
    ResourceBundle REGEX = ResourceBundle.getBundle(RESOURCES_LOCATION + "Syntax");

    /**
     * @param command User written command
     * @return Expression built from command
     */
    Expression parse(String command);

    /**
     * @param locale Desired Locale of commands
     */
    void setLocale(String locale);

    class SyntaxException extends RuntimeException {
        public SyntaxException(String s) {
            super("Error parsing syntax: " + s);
        }
    }
}
