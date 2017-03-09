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
            throw new SyntaxException("");
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            subList.add(readGroup(env, tokens));
            return readUntil(env, REGEX.getString("GroupEnd"), subList, tokens);
        } else if (token.matches(REGEX.getString("ListStart"))) {
            return readUntil(env, REGEX.getString("ListEnd"), subList, tokens);
        } else {
            Invokable fn = env.getFunctionByName(getTranslator().get(token));
            if (Objects.nonNull(fn)) {
                subList.add(new AtomicList(getTranslator().get(token)));
                subList.addAll(fn.readArgs(fn.minimumArity(),env,tokens));
                return subList;
            } else {
                return new AtomicList(getTranslator().get(token));
            }
        }
    }

    private Expression readGroup(Environment env, Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            return subList;
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            subList.add(readGroup(env, tokens));
            return readUntil(env, REGEX.getString("GroupEnd"), subList, tokens);
        } else if (token.matches(REGEX.getString("ListStart"))) {
            return readUntil(env, REGEX.getString("ListEnd"), subList, tokens);
        } else {
            return new AtomicList(getTranslator().get(token));
        }
    }

    @Override
    public Expression parse(Environment env, String input) {
        return super.parse(env, "[" + input + "]");
    }

    private Expression readUntil(Environment env, String end, SExpression subList, Deque tokens) {
        while (Objects.nonNull(tokens.peek()) && !tokens.peek().toString().matches(end)) {
            subList.add(readTokens(env, tokens));
        }
        tokens.pollFirst();
        return subList;
    }

}
