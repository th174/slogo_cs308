package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.Variable;

import java.util.Arrays;
import java.util.Collection;

/**
 * This probably isn't the right way to hold a ton of functions, but I don't know how else you would do it
 * Created by th174 on 2/16/2017.
 */
public class BasicCommands {
    public static final Accumulator SUM = Variable::sum;
    public static final Accumulator DIFFERENCE = Variable::difference;
    public static final Accumulator PRODUCT = Variable::product;
    public static final Accumulator QUOTIENT = Variable::quotient;
    public static final Accumulator REMAINDER = Variable::remainder;
    public static final Accumulator POWER = Variable::power;
    public static final Accumulator AND = Variable::and;
    public static final Accumulator OR = Variable::or;
    public static final Predicate NOT = Variable::not;
    public static final Predicate SINE = Variable::sine;
    public static final Predicate COSINE = Variable::cosine;
    public static final Predicate TANGENT = Variable::tangent;
    public static final Predicate ATANGENT = Variable::atangent;
    public static final Predicate LOG = Variable::log;
    public static final BooleanTest LESS_THAN = Variable::lessThan;
    public static final BooleanTest GREATER_THAN = Variable::greaterThan;
    public static final BooleanTest EQUAL_TO = Variable::equalTo;
    public static final BooleanTest NOT_EQUAL_TO = Variable::notEqualTo;

    private BasicCommands() {
    }

    public static Collection<Invokable> getAllCommands() {
        Invokable[] commands = {SUM, DIFFERENCE, PRODUCT, QUOTIENT, REMAINDER, POWER, AND, OR, NOT, SINE, COSINE, TANGENT, ATANGENT, LOG, LESS_THAN, GREATER_THAN, EQUAL_TO, NOT_EQUAL_TO};
        return Arrays.asList(commands);
    }
}
