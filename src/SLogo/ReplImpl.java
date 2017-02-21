package SLogo;

import SLogo.Parse.LispSyntaxParser;
import SLogo.Parse.SList;
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
    private ArrayList<SList> history;
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
        String command = input.nextLine();
        if (command.length() > 0) {
            try {
                history.add(parser.parse(command));
//                try {
//                    System.out.println(history.get(currentIndex).execute());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                print();
                currentIndex++;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void print() {
        //TODO:
    }

    @Override
    public List<SList> getHistory() {
        return history;
    }
}
