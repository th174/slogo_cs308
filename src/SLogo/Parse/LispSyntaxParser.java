package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by th174 on 2/21/2017.
 */
public final class LispSyntaxParser implements Parser {
    public static final String RESOURCES_LOCATION = "resources.languages/";
    public static final String DEFAULT_LOCALE = "English";
    public static final String REGEX = "Syntax";
    private ResourceBundle myRegex;
    private ResourceBundle myLocale;
    private Environment currentEnv = new EnvironmentImpl();
    private Map<String, String> translator;

    public LispSyntaxParser() throws IOException {
        this(DEFAULT_LOCALE);
        AtomicList.setEnvironment(currentEnv);
    }

    public LispSyntaxParser(String locale) throws SyntaxException, UnexpectedEOLException, IOException {
        myRegex = ResourceBundle.getBundle(RESOURCES_LOCATION + REGEX);
        setLocale(locale);
    }

    private LinkedList<String> tokenSplit(String s) {
        Matcher m = Pattern.compile(String.format("(%s|%s|%s|%s)", myRegex.getString("GroupStart"), myRegex.getString("GroupEnd"), myRegex.getString("ListStart"), myRegex.getString("ListEnd"))).matcher(s);
        return new LinkedList<>(Arrays.asList(m.replaceAll(" $1 ").trim()
                .split(myRegex.getString("Whitespace") + "|" + myRegex.getString("Newline"))));
    }

    private RecursiveExpression readTokens(Deque tokens) {
        if (tokens.size() == 0) {
            throw new UnexpectedEOLException("");
        }
        String token = tokens.removeFirst().toString();
        SList subList = new SList();
        if (token.matches(myRegex.getString("GroupEnd"))) {
            throw new SyntaxException("");
        } else if (token.matches(myRegex.getString("GroupStart"))) {
            while (Objects.nonNull(tokens.peek()) && !tokens.peek().toString().matches(myRegex.getString("GroupEnd"))) {
                subList.add(readTokens(tokens));
            }
            tokens.removeFirst();
            return subList;
        } else {
            return new AtomicList(translator.getOrDefault(token.toUpperCase(), token));
        }
    }

    @Override
    public RecursiveExpression parse(String input) {
        LinkedList<String> tokens = tokenSplit("("+input+")");
        SList temp = new SList(readTokens(tokens));
        System.out.println(temp.toString().substring(1,temp.toString().length()-1));
        return temp;
    }

    public void setLocale(String locale) throws IOException {
        myLocale = ResourceBundle.getBundle(RESOURCES_LOCATION + locale);
        translator = new HashMap<>();
        myLocale.keySet().forEach(e -> {
            for (String key : myLocale.getString(e).split("\\|")) {
                translator.putIfAbsent(key.toUpperCase(), e.toUpperCase());
            }
        });
    }

    static class SyntaxException extends RuntimeException {
        SyntaxException(String s) {
            super("Error parsing syntax: " + s);
        }
    }

    private static class UnexpectedEOLException extends RuntimeException {
        UnexpectedEOLException(String s) {
            super("Unexpected End of Line -- Missing ')' near: " + s);
        }
    }
}
