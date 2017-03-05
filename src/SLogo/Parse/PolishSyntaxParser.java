package SLogo.Parse;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by th174 on 3/3/2017.
 */
public class PolishSyntaxParser extends AbstractParser {
    public PolishSyntaxParser() throws IOException {
        super();
    }

    public PolishSyntaxParser(String locale)  {
       super(locale);
    }

    protected Expression readTokens(Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            throw new SyntaxException("");
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            while (Objects.nonNull(tokens.peek()) && !tokens.peek().toString().matches(REGEX.getString("GroupEnd"))) {
                subList.add(readTokens(tokens));
            }
            tokens.removeFirst();
            return subList;
        } else if (token.matches(REGEX.getString("ListStart"))) {
            while (Objects.nonNull(tokens.peek()) && !tokens.peek().toString().matches(REGEX.getString("ListEnd"))) {
                subList.add(readTokens(tokens));
            }
            tokens.removeFirst();
            return subList;
        } else if (token.matches(REGEX.getString("Command"))) {
            int expectedArity = getTranslator().getArity(token);
            for (int i = 0; Objects.nonNull(tokens.peek()) && i < expectedArity; i++) {
                subList.add(readTokens(tokens));
            }
            return subList;
        } else {
            return new AtomicList(getTranslator().get(token));
        }
    }
}
