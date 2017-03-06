package SLogo.Parse;

import java.util.Deque;
import java.util.LinkedList;
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

    private LinkedList<String> tokenSplit(String s) {
        Matcher m = Pattern.compile(String.format("(%s|%s|%s|%s)", REGEX.getString("GroupStart"), REGEX.getString("GroupEnd"), REGEX.getString("ListStart"), REGEX.getString("ListEnd"))).matcher(s);
        s = m.replaceAll(" $1 ");
        m = Pattern.compile(REGEX.getString("Token"), Pattern.DOTALL).matcher(s);
        LinkedList<String> tokens = new LinkedList<>();
        while (m.find()) {
            tokens.add(m.group(1));
        }
        return tokens;
    }

    protected abstract Expression readTokens(Deque tokens);

    protected Translator getTranslator() {
        return myTranslator;
    }

    @Override
    public Expression parse(String input) {
        LinkedList<String> tokens = tokenSplit(input.replaceAll(REGEX.getString("Comment"), ""));
        Expression temp = readTokens(tokens);
        System.out.println(temp.toString().substring(1, temp.toString().length() - 1));
        return temp;
    }

    @Override
    public void setLocale(String locale) {
        myTranslator = new Translator(ResourceBundle.getBundle(RESOURCES_LOCATION + locale));
    }

}
