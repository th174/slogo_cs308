package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;

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
     * @param env,    Current runtime environmental variables
     * @return Expression built from command
     */
    Expression parse(Environment env, String command);

    /**
     * @param locale Desired Locale of commands
     */
    void setLocale(String locale) throws UnsupportedLanguageException;

    class SyntaxException extends RuntimeException {
        protected SyntaxException(String s) {
            super("Error parsing syntax: " + s);
        }
    }

    class UnsupportedLanguageException extends RuntimeException {
        protected UnsupportedLanguageException(String s) {
            super("Error Unsupported Language" + s);
        }
    }
}
