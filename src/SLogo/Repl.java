package SLogo;

import java.util.List;

import SLogo.FunctionEvaluate.Environment;
import SLogo.Parse.Parser;
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
    void read(String input) throws Exception;

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
    
    /**
     * @return Parser
     */
    Parser getParser();
}
