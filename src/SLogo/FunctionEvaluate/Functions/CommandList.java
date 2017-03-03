package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.LambdaVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.NewTurtle;
import SLogo.View.CanvasView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

/**
 * This probably isn't the right way to hold a ton of functions, but I don't know how else you would do it
 * Created by th174 on 2/16/2017.
 *
 * @author Stone Mathers
 */
public class CommandList {
    //Variable argument Length
    public static final Accumulator LIST = Variable::list;
    public static final Accumulator DEFAULT_OPERATION = LIST;
    public static final Accumulator SUM = Variable::sum;
    public static final Accumulator DIFFERENCE = Variable::difference;
    public static final Accumulator PRODUCT = Variable::product;
    public static final Accumulator QUOTIENT = Variable::quotient;
    public static final Accumulator REMAINDER = Variable::remainder;
    public static final Accumulator POWER = Variable::power;
    public static final ShortCircuit AND = Variable::and;
    public static final ShortCircuit OR = Variable::or;
    public static final Invokable IF = (env, expr) -> {
        if (expr[0].eval(env).toBoolean()) {
            return DEFAULT_OPERATION.invoke(env, Arrays.copyOfRange(expr, 1, expr.length));
        } else {
            return Variable.FALSE;
        }
    };
    public static final Invokable REPEAT = (env, expr) -> {
        double count = expr[0].eval(env).toNumber();
        Variable last = Variable.FALSE;
        while (count-- > 0) {
            last = DEFAULT_OPERATION.invoke(env, Arrays.copyOfRange(expr, 1, expr.length));
        }
        return last;
    };
    public static final Invokable DOTIMES = (env, expr) -> {
        String loopVar = expr[0].getBody().remove(0).toString();
        env.addUserVariable(loopVar, expr[0].getBody().size() > 2 ? expr[0].getBody().remove(0).eval(env) : Variable.newInstance(1));
        Variable limit = expr[0].getBody().remove(0).eval(env);
        Variable last = Variable.newInstance(0);
        while (env.getVariableByName(loopVar).greaterThan(limit) == Variable.FALSE) {
            last = DEFAULT_OPERATION.invoke(env, Arrays.copyOfRange(expr, 1, expr.length));
            env.addUserVariable(loopVar, env.getVariableByName(loopVar).sum(expr[0].getBody().size() > 0 ? expr[0].getBody().get(0).eval(env) : Variable.newInstance(1)));
        }
        return last;
    };
    public static final Invokable FOR = DOTIMES;
    //Fixed argument length (Accepts multiple arguments, but please, don't do it)
    public static final UnaryFunction RANDOM = Variable::random;
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
    public static final TurtleMovement FORWARD = (t, v) -> t.moveForward(v.toNumber());
    public static final TurtleMovement BACKWARD = (t, v) -> t.moveBackward(v.toNumber());
    public static final TurtleMovement LEFT = (t, v) -> t.rotateCCW(v.toNumber());
    public static final TurtleMovement RIGHT = (t, v) -> t.rotateCW(v.negate().toNumber());
    public static final TurtleMovement SETHEADING = (t, v) -> t.setHeading(v.toNumber());
    public static final TurtlePosition SETPOSITION = (t, vx, vy) -> t.setXY(vx.toNumber(), vy.toNumber());
    public static final TurtlePosition SETTOWARDS = (t, vx, vy) -> t.setHeadingTowards(vx.toNumber(), vy.toNumber());
    public static final TurtleProperties HOME = NewTurtle::reset;
    public static final TurtleProperties HEADING = NewTurtle::getHeading;
    public static final TurtleProperties HIDETURTLE = NewTurtle::hideTurtle;
    public static final TurtleProperties SHOWTURTLE = NewTurtle::showTurtle;
    public static final TurtleProperties PENDOWN = NewTurtle::penDown;
    public static final TurtleProperties PENUP = NewTurtle::penUp;
    public static final TurtleProperties ISSHOWING = NewTurtle::isTurtleShow;
    public static final TurtleProperties ISPENDOWN = NewTurtle::penDown;
    public static final TurtleProperties XCOORDINATE = NewTurtle::getX;
    public static final TurtleProperties YCOORDINATE = NewTurtle::getY;
    public static final CanvasProperties CLEARSCREEN = CanvasView::clearScreen;
    public static final IterableInvokable MAKEVARIABLE = new IterableInvokable() {
        @Override
        public int expectedArity() {
            return 2;
        }

        @Override
        public Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
            env.addUserVariable(vargs[0].toString(), vargs[1].eval(env));
            return vargs[0].eval(env);
        }
    };
    public static final Invokable LAMBDA = (env, expr) -> new LambdaVariable(expr[0], Arrays.copyOfRange(expr, 1, expr.length));
    public static final Invokable MAKEUSERINSTRUCTION = (env, expr) -> {
//        env.addUserFunction(expr[0].toString(), new LambdaVariable(expr[1], Arrays.copyOfRange(expr, 2, expr.length)));
        env.addUserVariable(expr[0].toString(), LAMBDA.invoke(env, Arrays.copyOfRange(expr, 1, expr.length)));
        return Variable.TRUE;
    };
    public static final Invokable IFELSE = new IterableInvokable() {
        @Override
        public int expectedArity() {
            return 3;
        }

        @Override
        public Variable operation(Environment env, Expression... vargs) throws Expression.EvaluationTargetException {
            if (vargs[0].eval(env).toBoolean()) {
                return vargs[1].eval(env);
            } else {
                return vargs[2].eval(env);
            }
        }
    };


    /**
     * You should never instantiate this class
     */
    private CommandList() {
    }

    public static Collection<Field> getAllCommands() {
        return Arrays.asList(CommandList.class.getDeclaredFields());
    }
}
