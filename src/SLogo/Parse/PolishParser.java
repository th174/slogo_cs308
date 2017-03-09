package SLogo.Parse;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Functions.Invokable;

import java.util.Deque;
import java.util.Objects;

/**
 * Created by th174 on 3/7/2017.
 */
public class PolishParser extends AbstractParser {

    public Expression readTokens(Environment env, Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            throw new SyntaxException(token);
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            return readUntil(env, REGEX.getString("GroupEnd"), subList, tokens, true);
        } else if (token.matches(REGEX.getString("ListStart"))) {
            return readUntil(env, REGEX.getString("ListEnd"), subList, tokens, false);
        } else {
            Invokable fn = env.getFunctionByName(getTranslator().get(token));
            if (Objects.nonNull(fn)) {
                subList.add(new AtomicList(getTranslator().get(token)));
                subList.addAll(fn.readArgs(fn.minimumArity(), env, tokens));
                return subList;
            } else {
                return new AtomicList(getTranslator().get(token));
            }
        }
    }

    private Expression readFirstToken(Environment env, Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            throw new SyntaxException(token);
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            return readUntil(env, REGEX.getString("GroupEnd"), subList, tokens, true);
        } else if (token.matches(REGEX.getString("ListStart"))) {
            return readUntil(env, REGEX.getString("ListEnd"), subList, tokens, false);
        } else {
            return new AtomicList(getTranslator().get(token));
        }
    }

    @Override
    public Expression parse(Environment env, String input) {
        return super.parse(env, "[" + input + "]");
    }

    private Expression readUntil(Environment env, String end, SExpression subList, Deque tokens, boolean isGroup) {
        while (Objects.nonNull(tokens.peek()) && !tokens.peek().toString().matches(end)) {
            if (isGroup) {
                subList.add(readFirstToken(env, tokens));
                isGroup = false;
            }
            subList.add(readTokens(env, tokens));
        }
        tokens.pollFirst();
        return subList;
    }

}
