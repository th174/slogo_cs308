package SLogo;

import java.util.List;
import java.util.Scanner;

import SLogo.FunctionEvaluate.Environment;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Repl {
    /**
     * Take in user input
     *
     * @param input User input string
     */
    void read(Scanner input) throws Exception;

   /* *//**
     * Give feedback to user based on evaluated results
     *//*
    void print();*/

    /**
     * @return list of all previous commands run
     */
    List<String> getHistory();
    
    /**
     * @param canvas to be contained in the Repl's Environment
     */
    void setCanvas(CanvasView canvas);
    
    /**
     * @return Environment
     */
    Environment getEnvironment();
}
