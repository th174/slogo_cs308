package SLogo;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.RecursiveExpression;
import SLogo.Turtles.SLogoTurtle;
import SLogo.Turtles.Turtle;
import SLogo.Parse.LispSyntaxParser;
import SLogo.Parse.Parser;
import SLogo.View.CanvasView;
import SLogo.View.SLogoGUI;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by th174 on 2/16/2017.
 */
public class ReplImpl implements Repl {
    private Parser parser;
    private ArrayList<String> history;
    private int currentIndex;
    private Environment globalEnv;
    private Turtle myTurtle;
    private CanvasView myCanvas;

    public ReplImpl(InputStream input) throws IOException {
        parser = new LispSyntaxParser();
        history = new ArrayList<>();
        currentIndex = 0;
        globalEnv = new EnvironmentImpl();
        myTurtle = new SLogoTurtle();
        globalEnv.setTurtle(myTurtle);
    }

    @Override
    public void read(Scanner input) throws Exception {
        System.out.print("SLogo >>");
        String line = input.nextLine();
        if (line.length() > 0) {
            try {
                RecursiveExpression currentCommand = parser.parse(line);
                history.add(currentCommand.toString());
                System.out.println(eval(currentCommand));
                currentIndex++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Variable eval(RecursiveExpression expression) throws RecursiveExpression.EvaluationTargetException {
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
    public void setCanvas(CanvasView canvas){
    	myCanvas = canvas;
    	globalEnv.setCanvas(myCanvas);
    	myTurtle.addObserver(myCanvas);
    }

	@Override
	public Environment getEnvironment() {
		return globalEnv;
	}
}
