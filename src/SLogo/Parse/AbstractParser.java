package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.Repl;

import java.util.Deque;
import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a basic implementation of a parser for the SLogo language
 *
 * @author Created by th174 on 3/4/2017.
 */
public abstract class AbstractParser implements Parser {
    private Translator myTranslator;

    /**
     * Creates an instance of a parser with the default locale
     */
    public AbstractParser() {
        this(DEFAULT_LOCALE);
    }

    /**
     * Creates an instance of a parser with specified locale
     *
     * @param locale Locale
     */
    public AbstractParser(String locale) {
        setLocale(locale);
    }

    @Override
    public Deque<String> tokenize(String s) {
        s = s.replaceAll(REGEX.getString("Comment"), "");
        Matcher m = Pattern.compile(REGEX.getString("Token"), Pattern.DOTALL).matcher(s);
        Deque<String> tokens = new LinkedList<>();
        while (m.find()) {
            tokens.add(m.group(1));
        }
        return tokens;
    }

    @Override
    public abstract Expression readTokens(Environment env, Deque<String> tokens);

    protected Translator getTranslator() {
        return myTranslator;
    }

    @Override
    public boolean parse(Repl repl, Environment env, String userInput) {
        Deque<String> tokens = tokenize(userInput);
        while (!tokens.isEmpty()) {
            readTokens(env, tokens).eval(repl, env);
        }
        return true;
    }

    @Override
    public String setLocale(String locale) throws UnsupportedLanguageException {
        try {
            myTranslator = new Translator(ResourceBundle.getBundle(RESOURCES_LOCATION + locale));
        } catch (MissingResourceException e) {
            throw new UnsupportedLanguageException(locale);
        }
        return locale;
    }
}
