package SLogo;

import SLogo.SLogoRead.Command;
import SLogo.SLogoRead.Parser;
import SLogo.SLogoRead.ParserImpl;
import SLogo.SLogoView.SLogoView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by th174 on 2/16/2017.
 */
public class ReplImpl implements Repl {
    public static final String DEFAULT_LANGUAGE = "English";
    public static final String DEFAULT_DELIMITER = " ";
    private Scanner inputScanner;
    private Parser parser;
    private ArrayList<Command> history;
    private SLogoView parentNode;
    private int currentIndex;

    public ReplImpl(InputStream input, SLogoView view) {
        inputScanner = new Scanner(input);
        parser = new ParserImpl(DEFAULT_LANGUAGE, DEFAULT_DELIMITER);
        history = new ArrayList<>();
        currentIndex = 0;
        parentNode = view;
    }

    @Override
    public void read() throws Exception {
        String input = inputScanner.nextLine();
        history.add(parser.parse(input));
    }

    @Override
    public void evaluate() {
        history.get(currentIndex).execute();
    }

    @Override
    public void print() {
        parentNode.update();
        currentIndex++;
    }

    @Override
    public void loop() {
        while (true) {
            try {
                read();
                evaluate();
                print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Command> getHistory() {
        return history;
    }
}
