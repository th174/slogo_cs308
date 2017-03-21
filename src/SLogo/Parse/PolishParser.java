package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;

import java.util.Deque;
import java.util.Objects;

/**
 * An implementation of AbstractParser designed to parse Polish notation
 *
 * @author Created by th174 on 3/7/2017.
 */
public class PolishParser extends AbstractParser {

    /**
     * Creates an instance of a PolishParser with the default locale
     */
    public PolishParser() {
        super();
    }

    /**
     * Creates instance of PolishParser with the desired locale
     *
     * @param s locale
     */
    public PolishParser(String s) {
        super(s);
    }

    /**
     * Reads tokens in Polish notation
     *
     * @param env    Current local environment
     * @param tokens Deque of Token Strings
     * @return Expression built from token strings
     */
    @Override
    public Expression readTokens(Environment env, Deque<String> tokens) {
        return readTokens(env, tokens, true);
    }

    private Expression readTokens(Environment env, Deque<String> tokens, boolean autoGroup) {
        String token = tokens.removeFirst();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            throw new SyntaxException(token);
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            return readUntil(env, REGEX.getString("GroupEnd"), tokens, false);
        } else if (token.matches(REGEX.getString("ListStart"))) {
            return readUntil(env, REGEX.getString("ListEnd"), tokens, true);
        } else {
            return group(env, tokens, token, autoGroup);
        }
    }

    private Expression group(Environment env, Deque<String> tokens, String token, boolean autoGroup) {
        SExpression subList = new SExpression();
        Invokable fn = env.getFunctionByName(getTranslator().get(token));
        if (Objects.nonNull(fn) && autoGroup) {
            subList.add(new AtomicList(getTranslator().get(token)));
            subList.addAll(fn.readArgs(fn.minimumArity(), env, tokens, this));
            return subList;
        } else {
            return new AtomicList(getTranslator().get(token));
        }
    }

    private Expression readUntil(Environment env, String end, Deque<String> tokens, boolean autoGroup) {
        SExpression subList = new SExpression();
        while (Objects.nonNull(tokens.peek()) && !tokens.peek().matches(end)) {
            subList.add(readTokens(env, tokens, autoGroup));
            autoGroup = true;
        }
        tokens.pollFirst();
        return subList;
    }

}
