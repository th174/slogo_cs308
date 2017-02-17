package SLogo;

import SLogo.SLogoRead.Command;

import java.util.List;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Repl {
    /**
     * Take in user input
     */
    void read() throws Exception;

    /**
     * Invoke method based on input
     */
    void evaluate();

    /**
     * Give feedback to user based on evaluated results
     */
    void print();

    /**
     * Repeat the above
     */
    void loop() throws Exception;

    /**
     * @return list of all previous commands run
     */
    List<Command> getHistory();
}
