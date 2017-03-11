package SLogo;

import SLogo.FunctionEvaluate.Environment;
import SLogo.Parse.Expression;
import SLogo.Parse.Parser;
import SLogo.View.CanvasView;

import java.util.List;

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

    /**
     * @return Environment
     */
    Environment getEnvironment();

    List<Expression> getHistory();

    Expression lastCommand();

    void setCanvas(CanvasView canvas);

    CanvasView getCanvas();

    /**
     * @return Parser
     */
    Parser getParser();
}