package SLogo;

import java.util.List;
import java.util.Scanner;

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

    /**
     * Give feedback to user based on evaluated results
     */
    void print();

    /**
     * @return list of all previous commands run
     */
    List<String> getHistory();
}
