package SLogo;

import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Parse.LispSyntaxParser;
import SLogo.Parse.Parser;
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
    private SLogoGUI parentNode;
    private int currentIndex;

    public ReplImpl(InputStream input, SLogoGUI view) throws IOException {
        parser = new LispSyntaxParser();
        history = new ArrayList<>();
        currentIndex = 0;
        parentNode = view;
    }

    @Override
    public void read(Scanner input) throws Exception {
        System.out.print("SLogo >>");
        String line = input.nextLine();
        if (line.length() > 0) {
            try {
                Expression currentCommand = parser.parse(line);
                history.add(currentCommand.toString());
                System.out.println(eval(currentCommand));
                currentIndex++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Variable eval(Expression expression) throws Expression.EvaluationTargetException {
        return expression.eval();
    }


    @Override
    public void print() {
        //TODO:
    }

    @Override
    public List<String> getHistory() {
        return history;
    }
}
