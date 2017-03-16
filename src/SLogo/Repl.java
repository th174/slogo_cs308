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
     * @throws Exception if error in parsing or evaluating input
     */
    void read(String input) throws Exception;

    /**
     * @return User environment associated with current REPL session
     */
    Environment getUserEnvironment();

    /**
     * @return List of all previous commands in Expression representation, from least to most recent
     */
    List<Expression> getHistory();

    /**
     * @return Last run command in Expression representation
     */
    Expression lastCommand();

    /**
     * @return Canvas associated with current REPL session
     */
    CanvasView getCanvas();

    /**
     * @param canvas Sets the canvas associated with the current REPL session
     */
    void setCanvas(CanvasView canvas);

    /**
     * @return Parser associated with the current REPL session
     */
    Parser getParser();
}