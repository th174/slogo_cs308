
package SLogo;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
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
    private Parser parser;
    private Environment userEnv;
    private CanvasView myCanvas;

    public ReplImpl() {
        userEnv = new EnvironmentImpl(EnvironmentImpl.GLOBAL_ENVIRONMENT, Collections.singletonList(1));
        parser = new PolishParser();
    }

    @Override
    public void read(String input) throws Exception {
        parser.parse(this, userEnv, input);
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