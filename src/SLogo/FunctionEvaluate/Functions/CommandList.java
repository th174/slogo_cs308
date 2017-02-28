package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Variables.BoolVariable;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.FunctionEvaluate.Variables.Variable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

/**
 * This probably isn't the right way to hold a ton of functions, but I don't know how else you would do it
 * Created by th174 on 2/16/2017.
 */
public class CommandList {
    public static final Accumulator SUM = Variable::sum;
    public static final Accumulator DIFFERENCE = Variable::difference;
    public static final Accumulator PRODUCT = Variable::product;
    public static final Accumulator QUOTIENT = Variable::quotient;
    public static final Accumulator REMAINDER = Variable::remainder;
    public static final Accumulator POWER = Variable::power;
    public static final Accumulator AND = Variable::and;
    public static final Accumulator OR = Variable::or;
    public static final Accumulator LIST = Variable::append;
    public static final UnaryFunction NOT = Variable::not;
    public static final UnaryFunction MINUS = Variable::negate;
    public static final UnaryFunction SINE = Variable::sine;
    public static final UnaryFunction COSINE = Variable::cosine;
    public static final UnaryFunction TANGENT = Variable::tangent;
    public static final UnaryFunction ARCTANGENT = Variable::atangent;
    public static final UnaryFunction NATURALLOG = Variable::log;
    public static final BooleanTest LESSTHAN = Variable::lessThan;
    public static final BooleanTest GREATERTHAN = Variable::greaterThan;
    public static final BooleanTest EQUAL = Variable::equalTo;
    public static final BooleanTest NOTEQUAL = Variable::notEqualTo;
    public static final Invokable MAKEVARIABLE = new DefineVariable();
    public static final Invokable MAKEUSERINSTRUCTION = new DefineFunction();
    public static final Invokable IF = new IfVariable();
    public static final Invokable IFELSE = new IfElseVariable();
    public static final Invokable REPEAT = new Loop();
    public static final TurtleMove FORWARD = (t, v) -> {
        t.move(v.toNumber());
        return new NumberVariable(v.toNumber());
    };
    public static final TurtleMove BACKWARD = (t, v) -> {
        t.move(v.negate().toNumber());
        return new NumberVariable(v.toNumber());
    };
    public static final TurtleMove LEFT = (t, v) -> {
        t.turn(v.toNumber());
        return new NumberVariable(v.toNumber());
    };
    public static final TurtleMove RIGHT = (t, v) -> {
        t.turn(v.negate().toNumber());
        return new NumberVariable(v.toNumber());
    };
    public static final TurtleMove SETHEADING = (t, v) -> {
        t.setHeading(v.toNumber());
        return new NumberVariable(Math.abs(t.getHeading() - v.toNumber()));
    };
    public static final TurtleSet CLEARSCREEN = (t, c) -> {
        double xPos = c.getSpritePosition()[0];
        double yPos = c.getSpritePosition()[1];
        c.clearScreen();
        return new NumberVariable(Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2)));
    };
    public static final TurtleSet HOME = (t, c) -> {
        double xPos = c.getSpritePosition()[0];
        double yPos = c.getSpritePosition()[1];
        t.reset(xPos, yPos);
        return new NumberVariable(Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2)));
    };
    public static final TurtleSet HEADING = (t, c) -> new NumberVariable(t.getHeading());
    public static final TurtleSet HIDETURTLE = (t, c) -> new NumberVariable(t.hide());
    public static final TurtleSet SHOWTURTLE = (t, c) -> new NumberVariable(t.show());
    public static final TurtleSet PENDOWN = (t, c) -> new NumberVariable(t.dropPen());
    public static final TurtleSet PENUP = (t, c) -> new NumberVariable(t.liftPen());
    public static final TurtleSet ISSHOWING = (t, c) -> !t.hidden() ? BoolVariable.TRUE : BoolVariable.FALSE;
    public static final TurtleSet ISPENDOWN = (t, c) -> t.penDown() ? BoolVariable.TRUE : BoolVariable.FALSE;
    public static final TurtleSet XCOORDINATE = (t, c) -> new NumberVariable(c.getSpritePosition()[0]);
    public static final TurtleSet YCOORDINATE = (t, c) -> new NumberVariable(c.getSpritePosition()[1]);

    public static final Accumulator DEFAULT_OPERATION = LIST;

    /**
     * You should never instantiate this class
     */
    private CommandList() {
    }

    public static Collection<Field> getAllCommands() {
        return Arrays.asList(CommandList.class.getDeclaredFields());
    }
}
