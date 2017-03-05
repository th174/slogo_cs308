package SLogo.Parse;

import java.io.IOException;
import java.util.Deque;
import java.util.Objects;

/**
 * Created by th174 on 2/21/2017.
 */
public final class LispSyntaxParser extends AbstractParser {
    public LispSyntaxParser() throws IOException {
        super();
    }

    public LispSyntaxParser(String locale) {
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
        } else {
            return new AtomicList(getTranslator().get(token));
        }
    }
}
