package SLogo;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.SLogoParser;
import SLogo.Parse.Expression;
import SLogo.Turtles.ObservableTurtle;
import SLogo.View.CanvasView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by th174 on 2/16/2017.
 */
public class ReplImpl implements Repl {
    private SLogoParser parser;
    private ArrayList<String> history;
    private int currentIndex;
    private Environment userEnv;

    public ReplImpl() throws IOException {

        history = new ArrayList<>();
        currentIndex = 0;
        userEnv = new EnvironmentImpl(EnvironmentImpl.GLOBAL_ENVIRONMENT, Collections.singletonList(new ObservableTurtle(1)));
        parser = new SLogoParser();
    }

    @Override
    public void read(String input) throws Exception {
        Expression currentCommand = parser.parse(userEnv, input);
        history.add(currentCommand.toString());
//        eval(currentCommand);
        currentIndex++;
    }

//    private Variable eval(Expression expression) throws Expression.EvaluationTargetException {
//        return expression.eval(userEnv);
//    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void setCanvas(CanvasView canvas) {
        userEnv.setCanvas(canvas);
        userEnv.getTurtles().forEach(t -> t.addObserver(canvas));
    }

    @Override
    public Environment getEnvironment() {
        return userEnv;
    }
}