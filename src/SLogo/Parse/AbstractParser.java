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
 * Created by th174 on 3/4/2017.
 */
public abstract class AbstractParser implements Parser {
    private Translator myTranslator;

    public AbstractParser() {
        this(DEFAULT_LOCALE);
    }

    public AbstractParser(String locale) {
        setLocale(locale);
    }

    protected LinkedList<String> tokenSplit(String s) {
        Matcher m = Pattern.compile(REGEX.getString("Token"), Pattern.DOTALL).matcher(s);
        LinkedList<String> tokens = new LinkedList<>();
        while (m.find()) {
            tokens.add(m.group(1));
        }
        return tokens;
    }

    public abstract Expression readTokens(Environment env, Deque tokens);

    protected Translator getTranslator() {
        return myTranslator;
    }

    @Override
    public boolean parse(Repl repl, Environment env, String input) {
        LinkedList<String> tokens = tokenSplit(input.replaceAll(REGEX.getString("Comment"), ""));
        while (!tokens.isEmpty()) {
            readTokens(env, tokens).eval(repl,env);
        }
        return true;
    }

    @Override
    public void setLocale(String locale) throws UnsupportedLanguageException {
        try {
            myTranslator = new Translator(ResourceBundle.getBundle(RESOURCES_LOCATION + locale));
        } catch (MissingResourceException e) {
            throw new UnsupportedLanguageException(locale);
        }
    }
}
