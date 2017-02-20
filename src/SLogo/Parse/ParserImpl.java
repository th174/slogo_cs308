package SLogo.Parse;

import SLogo.FunctionEvaluate.ScopeImpl;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.StringVariable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by th174 on 2/16/2017.
 */
public class ParserImpl implements Parser {
    public static final String DEFAULT_DELIMITER = "\\s+";
    public static final String RESOURCES_LOCATION = "resources.languages/";
    public static final String DEFAULT_LANGUAGE = "English";
    private ResourceBundle myResources;
    private ResourceBundle myRegex;
    private ScopeImpl currentScope;
    private String delimiter;
    private Map<String, String> translator;

    public ParserImpl() throws IOException {
        this(DEFAULT_LANGUAGE, DEFAULT_DELIMITER);
    }

    public ParserImpl(String locale, String delimiter) throws IOException {
        this.delimiter = delimiter;
        setLocale(locale);
        currentScope = new ScopeImpl();
        myRegex = ResourceBundle.getBundle(RESOURCES_LOCATION + "Syntax");
    }

    @Override
    public Expression parse(String command) throws ScopeImpl.VariableNotFoundException, ScopeImpl.FunctionNotFoundException {
        String[] params = command.trim().split(delimiter);
        List<String> flags = new ArrayList<>();
        List<Variable> args = new ArrayList<>();
        for (int i = 1; i < params.length; i++) {
            if (Pattern.matches(myRegex.getString("Flag"), params[i])) {
                flags.add(params[i].substring(1));
            } else if (Pattern.matches(myRegex.getString("Variable"), params[i])) {
                args.add(currentScope.getVariableByName(params[i].substring(1)));
            } else if (Pattern.matches(myRegex.getString("StringLiteral"), params[i])) {
                args.add(new StringVariable(params[i].substring(1, params[i].length() - 1)));
            } else {
                try {
                    args.add(new NumberVariable(params[i]));
                } catch (NumberFormatException e) {
                    throw new IllegalBareWordException(params[i]);
                }
            }
        }
        return new Expression(currentScope.getFunctionByName(translator.getOrDefault(params[0].toUpperCase(), params[0])), flags, args);
    }

    @Override
    public void setLocale(String locale) throws IOException {
        myResources = ResourceBundle.getBundle(RESOURCES_LOCATION + locale);
        translator = new HashMap<>();
        myResources.keySet().forEach(e -> {
            for (String key : myResources.getString(e).split("\\|")) {
                translator.putIfAbsent(key.toUpperCase(), e.toUpperCase());
            }
        });
    }

    private class IllegalBareWordException extends RuntimeException {
        private IllegalBareWordException(String s) {
            super("Illegal Bare Word: " + s);
        }
    }
}
