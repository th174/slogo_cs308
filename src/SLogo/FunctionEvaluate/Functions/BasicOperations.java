package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.Variable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

/**
 * This probably isn't the right way to hold a ton of functions, but I don't know how else you would do it
 * Created by th174 on 2/16/2017.
 */
public class BasicOperations {
    public static final Accumulator SUM = Variable::sum;
    public static final Accumulator DEFAULT_OPERATION = SUM;
    public static final Accumulator DIFFERENCE = Variable::difference;
    public static final Accumulator PRODUCT = Variable::product;
    public static final Accumulator QUOTIENT = Variable::quotient;
    public static final Accumulator REMAINDER = Variable::remainder;
    public static final Accumulator POWER = Variable::power;
    public static final Accumulator AND = Variable::and;
    public static final Accumulator OR = Variable::or;
    public static final Predicate NOT = Variable::not;
    public static final Predicate MINUS = Variable::negate;
    public static final Predicate SINE = Variable::sine;
    public static final Predicate COSINE = Variable::cosine;
    public static final Predicate TANGENT = Variable::tangent;
    public static final Predicate ARCTANGENT = Variable::atangent;
    public static final Predicate NATURALLOG = Variable::log;
    public static final BooleanTest LESSTHAN = Variable::lessThan;
    public static final BooleanTest GREATERTHAN = Variable::greaterThan;
    public static final BooleanTest EQUAL = Variable::equalTo;
    public static final BooleanTest NOTEQUAL = Variable::notEqualTo;

    /**
     * You should never instantiate this class
     */
    private BasicOperations() {
    }

    public static Collection<Field> getAllCommands() {
        return Arrays.asList(BasicOperations.class.getDeclaredFields());
    }
}
