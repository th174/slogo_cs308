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

    @Override
    public Expression parse(String input) {
        return super.parse("(" + input + ")");
    }

    protected Expression readTokens(Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            throw new SyntaxException("");
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            return readUntil(REGEX.getString("GroupEnd"), subList, tokens);
        } else if (token.matches(REGEX.getString("ListStart"))) {
            return readUntil(REGEX.getString("ListEnd"), subList, tokens);
        } else {
            return new AtomicList(getTranslator().get(token));
        }
    }

    private Expression readUntil(String end, SExpression subList, Deque tokens) {
        while (Objects.nonNull(tokens.peek()) && !tokens.peek().toString().matches(end)) {
            subList.add(readTokens(tokens));
        }
        tokens.removeFirst();
        return subList;
    }
}
