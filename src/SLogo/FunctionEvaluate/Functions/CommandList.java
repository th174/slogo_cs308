package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.EnvironmentImpl;
import SLogo.FunctionEvaluate.Variables.LambdaVariable;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Turtles.NewTurtle;
import SLogo.View.CanvasView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This probably isn't the right way to hold a ton of functions, but I don't know how else you would do it
 * Created by th174 on 2/16/2017.
 *
 * @author Stone Mathers
 */
public class CommandList {
    //Variable argument Length
    public static final Accumulator LIST = Variable::list;
    public static final Accumulator $DEFAULT_OPERATION$ = LIST;
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
            return $DEFAULT_OPERATION$.invoke(env, Arrays.copyOfRange(expr, 1, expr.length));
        } else {
            return Variable.FALSE;
        }
    };
    public static final Invokable REPEAT = (env, expr) -> {
        List<Expression> loopParams = expr[0].getBody();
        int loopArity = loopParams.size();
        String loopVar = loopArity >= 2 ? loopParams.remove(0).toString() : ResourceBundle.getBundle("resources/variables/variable").getString("repcount");
        env.addUserVariable(loopVar, loopArity >= 4 ? loopParams.remove(0).eval(env) : Variable.newInstance(1));
        Variable limit = loopParams.remove(0).eval(env);
        Variable last = Variable.newInstance(0);
        while (env.getVariableByName(loopVar).greaterThan(limit) == Variable.FALSE) {
            last = $DEFAULT_OPERATION$.invoke(env, Arrays.copyOfRange(expr, 1, expr.length));
            env.addUserVariable(loopVar, env.getVariableByName(loopVar).sum(loopArity >= 3 ? loopParams.get(0).eval(env) : Variable.newInstance(1)));
        }
        return last;
    };
    public static final Invokable DOTIMES = REPEAT; //There's actually only one loop function, it just behaves differently depending on the loop arguments
    public static final Invokable FOR = REPEAT; //There's actually only one loop function, it just behaves differently depending on the loop arguments
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
    public static final Invokable LAMBDA = (env, expr) -> new LambdaVariable(expr);
    public static final Invokable MAKEUSERINSTRUCTION = (env, expr) -> {
        env.addUserFunction(expr[0].toString(), (Invokable) LAMBDA.invoke(env, Arrays.copyOfRange(expr, 1, expr.length)));
        env.addUserVariable(expr[0].toString(), LAMBDA.invoke(env, Arrays.copyOfRange(expr, 1, expr.length)));
        return Variable.TRUE;
    };
    public static final MultiTurtleSet ASK = (env, list, expr) -> $DEFAULT_OPERATION$.invoke(new EnvironmentImpl(env, e -> list.contains(Variable.newInstance(e.id()))), Arrays.copyOfRange(expr, 1, expr.length));
    public static final Invokable ASKWITH = (env, expr) -> $DEFAULT_OPERATION$.invoke(new EnvironmentImpl(env, e -> expr[0].eval(env).toBoolean()), Arrays.copyOfRange(expr, 1, expr.length));
    public static final Invokable PRINT = (env, expr) -> {
        System.out.println($DEFAULT_OPERATION$.invoke(env, expr).toString());
        return Variable.TRUE;
    };
    //Fixed argument length (Accepts multiple arguments, but please don't use them because they're confusing as fuck)
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
    public static final TurtleMovement FORWARD = NewTurtle::moveForward;
    public static final TurtleMovement BACKWARD = NewTurtle::moveBackward;
    public static final TurtleMovement LEFT = NewTurtle::rotateCCW;
    public static final TurtleMovement RIGHT = NewTurtle::rotateCW;
    public static final TurtleMovement SETHEADING = NewTurtle::setHeading;
    public static final TurtlePosition SETPOSITION = NewTurtle::setXY;
    public static final TurtlePosition SETTOWARDS = NewTurtle::setHeadingTowards;
    public static final TurtleProperties ID = NewTurtle::id;
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
    public static final CanvasSetting SETBACKGROUND = CanvasView::setBackground;
    public static final CanvasSetting SETSHAPE = CanvasView::setShape;
    public static final CanvasSetting SETPENCOLOR = CanvasView::setPenColor;
    public static final CanvasSetting SETPENSIZE = CanvasView::setPenSize;
    public static final CanvasPalette SETPALETTE = CanvasView::setPalette;
    public static final CanvasProperty CLEARSCREEN = CanvasView::clearScreen;
    public static final CanvasProperty GETPENCOLOR = CanvasView::getPenColor;
    public static final CanvasProperty GETSHAPE = CanvasView::getShape;
    public static final IterableInvokable IFELSE = new IterableInvokable() {
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
    public static final MultiTurtleSet TELL = (env, list, expr) -> {
        env.filterTurtles(e -> list.contains(Variable.newInstance(e.id())));
        return list;
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
