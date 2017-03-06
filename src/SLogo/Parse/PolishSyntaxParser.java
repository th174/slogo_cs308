package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;

import java.io.IOException;
import java.util.Deque;
import java.util.Objects;

/**
 * Created by th174 on 3/3/2017.
 */
public class PolishSyntaxParser extends AbstractParser {
    private Environment env = EnvironmentImpl.GLOBAL_ENVIRONMENT;

    public PolishSyntaxParser() throws IOException {
        super();
    }

    public PolishSyntaxParser(String locale) {
        super(locale);
    }

    protected Expression readTokens(Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            throw new SyntaxException("");
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            subList.add(new AtomicList(getTranslator().get(tokens.removeFirst().toString())));
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
            subList.add(new AtomicList(getTranslator().get(token)));
            if (getTranslator().get(token).matches("TO") || getTranslator().get(token).matches("MAKE")) {
                subList.add(new AtomicList(tokens.removeFirst().toString()));
            }
            System.out.println(token);
            for (int i = 0; Objects.nonNull(tokens.peek()) && i < getArity(token); i++) {
                subList.add(readTokens(tokens));
            }
            return subList;
        } else {
            return new AtomicList(getTranslator().get(token));
        }
    }

    private int getArity(String token) {
        return env.getFunctionByName(getTranslator().get(token)).minimumArity();
    }
}
