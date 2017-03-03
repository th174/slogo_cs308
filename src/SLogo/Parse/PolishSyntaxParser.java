package SLogo.Parse;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by th174 on 3/3/2017.
 */
public class PolishSyntaxParser implements Parser {
    public static final String RESOURCES_LOCATION = "resources.languages/";
    public static final String DEFAULT_LOCALE = "English";
    public static final String REGEX = "Syntax";
    private ResourceBundle myRegex;
    private ResourceBundle myLocale;
    private Map<String, String> translator;

    public PolishSyntaxParser() throws IOException {
        this(DEFAULT_LOCALE);
    }

    public PolishSyntaxParser(String locale) throws SyntaxException, IOException {
        myRegex = ResourceBundle.getBundle(RESOURCES_LOCATION + REGEX);
        setLocale(locale);
    }

    private LinkedList<String> tokenSplit(String s) {
        Matcher m = Pattern.compile(String.format("(%s|%s|%s|%s)", myRegex.getString("GroupStart"), myRegex.getString("GroupEnd"), myRegex.getString("ListStart"), myRegex.getString("ListEnd"))).matcher(s);
        s = m.replaceAll(" $1 ");
        m = Pattern.compile(myRegex.getString("Token"), Pattern.DOTALL).matcher(s);
        LinkedList<String> tokens = new LinkedList<>();
        while (m.find()) {
            tokens.add(m.group(1));
        }
        return tokens;
    }

    private Expression readTokens(Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(myRegex.getString("GroupEnd")) | token.matches(myRegex.getString("ListEnd"))) {
            throw new SyntaxException("");
        } else if (token.matches(myRegex.getString("GroupStart"))) {
            while (Objects.nonNull(tokens.peek()) && !tokens.peek().toString().matches(myRegex.getString("GroupEnd"))) {
                subList.add(readTokens(tokens));
            }
            tokens.removeFirst();
            return subList;
        } else if (token.matches(myRegex.getString("ListStart"))) {
            while (Objects.nonNull(tokens.peek()) && !tokens.peek().toString().matches(myRegex.getString("ListEnd"))) {
                subList.add(readTokens(tokens));
            }
            tokens.removeFirst();
            return subList;
        } else {
            return new AtomicList(translator.getOrDefault(token.toUpperCase(), token));
        }
    }

    @Override
    public Expression parse(String input) {
        LinkedList<String> tokens = tokenSplit("(" + input.replaceAll(myRegex.getString("Comment"), "") + ")");
        Expression temp = readTokens(tokens);
        System.out.println(temp.toString());
        return temp;
    }

    public void setLocale(String locale) throws IOException {
        myLocale = ResourceBundle.getBundle(RESOURCES_LOCATION + locale);
        translator = new HashMap<>();
        myLocale.keySet().forEach(e -> {
            for (String key : myLocale.getString(e).split("\\|")) {
                translator.putIfAbsent(key.toUpperCase().replace("\\", ""), e.toUpperCase());
            }
        });
    }
}
