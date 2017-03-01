package SLogo;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Parse.LispSyntaxParser;
import SLogo.Parse.Parser;
import SLogo.Turtles.SLogoTurtle;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by th174 on 2/16/2017.
 */
public class ReplImpl implements Repl {
    private Parser parser;
    private ArrayList<String> history;
    private int currentIndex;
    private Environment globalEnv;
    private Turtle myTurtle;

    public ReplImpl() throws IOException {
        parser = new LispSyntaxParser();
        history = new ArrayList<>();
        currentIndex = 0;
        globalEnv = new EnvironmentImpl();
        myTurtle = new SLogoTurtle();
        globalEnv.setTurtle(myTurtle);
    }

    @Override
    public void read(String input) throws Exception {
        System.out.print("SLogo >>");
        Expression currentCommand = parser.parse(input);
        history.add(currentCommand.toString());
        System.out.println(eval(currentCommand));
        currentIndex++;
    }

    private Variable eval(Expression expression) throws Expression.EvaluationTargetException {
        return expression.eval(globalEnv);
    }


/*    @Override
    public void print() {
        //TODO:
    }*/

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void setCanvas(CanvasView canvas) {
        globalEnv.setCanvas(canvas);
        myTurtle.addObserver(canvas);
    }

    @Override
    public Environment getEnvironment() {
        return globalEnv;
    }
}