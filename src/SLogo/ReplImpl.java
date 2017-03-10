package SLogo;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.Parse.Expression;
import SLogo.Parse.Parser;
import SLogo.Parse.PolishParser;
import SLogo.View.CanvasView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by th174 on 2/16/2017.
 */
public class ReplImpl implements Repl {
    private PolishParser parser;
    private ArrayList<String> history;
    private int currentIndex;
    private Environment userEnv;
    private CanvasView myCanvas;

    public ReplImpl() {
        history = new ArrayList<>();
        currentIndex = 0;
        userEnv = new EnvironmentImpl(EnvironmentImpl.GLOBAL_ENVIRONMENT, Collections.singletonList(1));
        parser = new PolishParser();
    }

    @Override
    public void read(String input) throws Exception {
        Expression currentCommand = parser.parse(userEnv, input);
        eval(currentCommand);
        history.add(currentCommand.toString().substring(1, currentCommand.toString().length() - 1));
        System.out.println(history.get(currentIndex));
        currentIndex++;
    }

    private void eval(Expression command) {
        command.eval(this, userEnv);
    }

    @Override
    public List<String> getHistory() {
        return history;
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