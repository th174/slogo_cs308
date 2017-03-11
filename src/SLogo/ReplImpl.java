
package SLogo;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.Parse.Expression;
import SLogo.Parse.Parser;
import SLogo.Parse.PolishParser;
import SLogo.View.CanvasView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Created by th174 on 2/16/2017.
 */
public class ReplImpl implements Repl {
    private List<Expression> myHistory;
    private int currentIndex;
    private Parser parser;
    private Environment userEnv;
    private CanvasView myCanvas;

    public ReplImpl() {
        myHistory = new ArrayList<>();
        userEnv = new EnvironmentImpl(EnvironmentImpl.GLOBAL_ENVIRONMENT, Collections.singletonList(1));
        parser = new PolishParser();
        currentIndex = 0;
    }

    @Override
    public void read(String input) throws Exception {
        Deque<String> tokens = parser.tokenize(input);
        while (!tokens.isEmpty()) {
            Expression current = parser.readTokens(userEnv, tokens);
            current.eval(this, userEnv);
            myHistory.add(current);
            currentIndex++;
        }
    }

    public List<Expression> getHistory() {
        return myHistory;
    }

    public Expression lastCommand() {
        return myHistory.get(currentIndex - 1);
    }

    @Override
    public Environment getEnvironment() {
        return userEnv;
    }

    @Override
    public void setCanvas(CanvasView canvas) {
        this.myCanvas = canvas;
    }

    @Override
    public CanvasView getCanvas() {
        return myCanvas;
    }

    @Override
    public Parser getParser() {
        return parser;
    }
}