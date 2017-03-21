package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.Repl;

import java.util.Deque;
import java.util.ResourceBundle;

/**
 * This interface provides an API for parsers that allows users to convert strings into Expressions
 *
 * @author Created by th174 on 2/16/2017.
 */
public interface Parser {
    String RESOURCES_LOCATION = "resources.languages/";
    String DEFAULT_LOCALE = "English";
    ResourceBundle REGEX = ResourceBundle.getBundle(RESOURCES_LOCATION + "Syntax");

    /**
     * @param repl      Current REPL session
     * @param userInput User written command
     * @param env,      Current runtime environmental variables
     * @return true if userInput was parsed correctly, otherwise false
     */
    boolean parse(Repl repl, Environment env, String userInput);

    /**
     * Splits a user input string into a deque of syntactical tokens
     *
     * @param s User input string
     * @return A Deque of tokens
     */
    Deque<String> tokenize(String s);

    /**
     * Reads a deque of tokens, converting it to an Expression
     *
     * @param env    Current local environment
     * @param tokens Deque of tokens to process
     * @return Expression created from tokens
     */
    Expression readTokens(Environment env, Deque<String> tokens);

    /**
     * @param locale Desired Locale of commands
     * @return New locale set
     */
    String setLocale(String locale) throws UnsupportedLanguageException;

    /**
     * Thrown when the parser detects basic syntax errors in the user input string
     */
    class SyntaxException extends RuntimeException {
        protected SyntaxException(String s) {
            super("Error parsing syntax: " + s);
        }
    }

    /**
     * Thrown when a locale is not supported
     */
    class UnsupportedLanguageException extends RuntimeException {
        protected UnsupportedLanguageException(String s) {
            super("Error Unsupported Language" + s);
        }
    }
}
