package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.Invokable;
import SLogo.Parse.AbstractParser;
import SLogo.Parse.AtomicList;
import SLogo.Parse.Expression;
import SLogo.Parse.SExpression;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by th174 on 3/7/2017.
 */
public class SLogoParser extends AbstractParser {
    Environment env;

    public Expression readTokens(Environment env, Deque tokens) {
        this.env = env;
        return readTokens(tokens);
    }

    public Expression readTokens(Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            throw new SyntaxException("");
        } else if (token.matches(REGEX.getString("GroupStart"))) {
            subList.add(readList(tokens));
            return readUntil(REGEX.getString("GroupEnd"), subList, tokens);
        } else if (token.matches(REGEX.getString("ListStart"))) {
            return readUntil(REGEX.getString("ListEnd"), subList, tokens);
        } else {
            Invokable fn = env.getFunctionByName(getTranslator().get(token));
            if (Objects.nonNull(fn)) {
                subList.add(new AtomicList(getTranslator().get(token)));
                return readUntil(fn.minimumArity(), subList, tokens);
            } else {
                return new AtomicList(getTranslator().get(token));
            }
        }
    }


    private Expression readList(Deque tokens) {
        String token = tokens.removeFirst().toString();
        SExpression subList = new SExpression();
        if (token.matches(REGEX.getString("GroupEnd")) | token.matches(REGEX.getString("ListEnd"))) {
            return subList;
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

    private Expression readUntil(int args, SExpression subList, Deque tokens) {
        for (int i = 0; i < args; i++) {
            subList.add(readTokens(tokens));
        }
        return subList;
    }

    public Expression parse(Environment env, String input) {
        LinkedList<String> tokens = tokenSplit(input.replaceAll(REGEX.getString("Comment"), ""));
        Expression temp = readTokens(env, tokens);
//        temp.getCommand(env).invoke(env, tokens);
        temp.eval(env);
        return temp;
    }
}
