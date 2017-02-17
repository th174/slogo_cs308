package SLogo.SLogoRead;

import SLogo.FunctionEvaluate.Scope;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by th174 on 2/16/2017.
 */
public class ParserImpl implements Parser {
    public static final String DEFAULT_DELIMITER = " ";
    public static final String DEFAULT_LANGUAGE = "English";
    private Scope currentScope;
    private String delimiter;
    private String languageSet;

    public ParserImpl() {
        this(DEFAULT_LANGUAGE, DEFAULT_DELIMITER);
    }

    public ParserImpl(String delimiter) {
        this(DEFAULT_LANGUAGE, delimiter);
    }

    public ParserImpl(String languageSet, String delimiter) {
        this.delimiter = delimiter;
        this.languageSet = languageSet;
    }

    @Override
    public Command parse(String inputString) {
        String[] params = inputString.split(delimiter);
        List<String> flags = new ArrayList<>();
        List<Variable> args = new ArrayList<>();
        for (int i = 1; i < params.length; i++) {
            if (params[i].charAt(0) == '-' || params[i].charAt(0) == '/') {
                flags.add(params[i].substring(1));
            } else {
                args.add(currentScope.getVariableByName(params[i]));
            }
        }
        return new Command(currentScope.getFunctionByName(params[0]), flags, args);
    }
}
