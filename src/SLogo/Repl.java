package SLogo;

import java.util.List;

import SLogo.FunctionEvaluate.Environment;
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

    /**
     * @return list of all previous commands run
     */
    List<String> getHistory();
    
    /**
     * @return Environment
     */
    Environment getEnvironment();
}
