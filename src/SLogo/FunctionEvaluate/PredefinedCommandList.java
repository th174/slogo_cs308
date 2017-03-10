package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.*;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;
import SLogo.View.CanvasView;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This probably isn't the right way to hold a ton of functions, but I don't know how else you would do it
 * Created by th174 on 2/16/2017.
 *
 * @author Stone Mathers
 */
public final class PredefinedCommandList {
    public static final Accumulator
            LIST = Variable::list,
            $DEFAULT_OPERATION$ = LIST,
            SUM = Variable::sum,
            DIFFERENCE = Variable::difference,
            PRODUCT = Variable::product,
            QUOTIENT = Variable::quotient,
            REMAINDER = Variable::remainder,
            POWER = Variable::power;
    public static final ShortCircuit
            AND = Variable::and,
            OR = Variable::or;
    public static final MultiTurtleSet
            ASKWITH = (repl, env, turtle, expr) -> expr.eval(repl, new EnvironmentImpl(env, Collections.singletonList(turtle.id()))).toBoolean(),
            ASK = (repl, env, turtle, expr) -> expr.getBody().stream().map(e -> Math.round((float) e.eval(repl, env).toNumber())).collect(Collectors.toList()).contains(turtle.id());
    public static final Loop
            REPEAT = (repl, env, expr) -> {
        List<Expression> loopParams = expr[0].getBody();
        int loopArity = loopParams.size();
        String loopVar = loopArity >= 2 ? loopParams.remove(0).toString() : ResourceBundle.getBundle("resources/variables/variable").getString("repcount");
        env.addUserVariable(loopVar, loopArity >= 4 ? loopParams.remove(0).eval(repl, env) : Variable.newInstance(1));
        Variable limit = loopParams.remove(0).eval(repl, env);
        Variable last = Variable.FALSE;
        while (env.getVariableByName(loopVar).greaterThan(limit) == Variable.FALSE) {
            last = $DEFAULT_OPERATION$.eval(repl, env, Arrays.copyOfRange(expr, 1, expr.length));
            env.addUserVariable(loopVar, env.getVariableByName(loopVar).sum(loopArity >= 3 ? loopParams.get(0).eval(repl, env) : Variable.newInstance(1)));
        }
        return last;
    },
            DOTIMES = REPEAT,   //There's actually only one loop function, it just behaves differently depending on the loop arguments
            FOR = REPEAT,       //There's actually only one loop function, it just behaves differently depending on the loop arguments
            IF = REPEAT;        //If statement is actually just a loop, boolean expressions eval to 0 or 1 lol
    public static final Define
            MAKEUSERINSTRUCTION = (repl, env, expr) -> {
        env.addUserFunction(expr[0].toString(), new UserFunction(Arrays.copyOfRange(expr, 1, expr.length)));
        return Variable.TRUE;
    };
    public static final UnaryIterable RANDOM = Variable::random,
            NOT = Variable::not,
            MINUS = Variable::negate,
            SINE = Variable::sine,
            COSINE = Variable::cosine,
            TANGENT = Variable::tangent,
            ARCTANGENT = Variable::atangent,
            NATURALLOG = Variable::log,
            ECHO = var -> {
                System.out.println(var);
                return var;
            },
            EXIT = var -> {
                System.exit((int) var.toNumber());
                return var;
            };
    public static final BinaryIterable
            LESSTHAN = Variable::lessThan,
            GREATERTHAN = Variable::greaterThan,
            EQUAL = Variable::equalTo,
            NOTEQUAL = Variable::notEqualTo;
    public static final TurtleMovement
            FORWARD = Turtle::moveForward,
            BACKWARD = Turtle::moveBackward,
            LEFT = Turtle::rotateCCW,
            RIGHT = Turtle::rotateCW,
            SETHEADING = Turtle::setHeading;
    public static final TurtlePosition
            SETPOSITION = Turtle::setXY,
            SETTOWARDS = Turtle::setHeadingTowards;
    public static final TurtleProperties
            ID = Turtle::id,
            HOME = Turtle::reset,
            HEADING = Turtle::getHeading,
            HIDETURTLE = Turtle::hideTurtle,
            SHOWTURTLE = Turtle::showTurtle,
            PENDOWN = Turtle::penDown,
            PENUP = Turtle::penUp,
            ISSHOWING = Turtle::isTurtleShow,
            ISPENDOWN = Turtle::penDown,
            XCOORDINATE = Turtle::getX,
            YCOORDINATE = Turtle::getY;
    public static final CanvasSetting
            SETBACKGROUND = CanvasView::setBackground,
            SETSHAPE = CanvasView::setShape,
            SETPENCOLOR = CanvasView::setPenColor,
            SETPENSIZE = CanvasView::setPenSize;
    public static final CanvasPalette
            SETPALETTE = CanvasView::setPalette;
    public static final CanvasProperty
            CLEARSCREEN = new CanvasProperty() {
        @Override
        public Object operation(CanvasView canvas) {
//            return canvas.clearScreen();
            return true;
        }

        @Override
        public Variable eval(Repl repl, Environment env, Expression... expr) {
            env.getAllTurtles().clear();
            return Variable.newInstance(operation(repl.getCanvas()));
        }
    },
            GETPENCOLOR = CanvasView::getPenColor,
            GETSHAPE = CanvasView::getShape;
    public static final IterableInvokable
            IFELSE = new IterableInvokable() {
        @Override
        public int minimumArity() {
            return 3;
        }

        @Override
        public Variable operation(Repl repl, Environment env, Expression... vargs) {
            if (vargs[0].eval(repl, env).toBoolean()) {
                return vargs[1].eval(repl, env);
            } else {
                return vargs[2].eval(repl, env);
            }
        }
    },
            TELL = new IterableInvokable() {
                @Override
                public int minimumArity() {
                    return 1;
                }

                @Override
                public Variable operation(Repl repl, Environment env, Expression... vargs) {
                    List<Integer> turtleIDs = vargs[0].getBody().stream().map(e -> Math.round((float) e.eval(repl, env).toNumber())).collect(Collectors.toList());
                    env.selectTurtles(turtleIDs);
                    return Variable.newInstance(turtleIDs.get(turtleIDs.size() - 1));
                }
            },
            MAKEVARIABLE = new IterableInvokable() {
                @Override
                public int minimumArity() {
                    return 2;
                }

                @Override
                public Variable operation(Repl repl, Environment env, Expression... vargs) {
                    env.addUserVariable(vargs[0].toString(), vargs[1].eval(repl, env));
                    return vargs[0].eval(repl, env);
                }
            };


    /**
     * You should never instantiate this class.
     */
    private PredefinedCommandList() {
    }

    /**
     * @return A map of all defined commands, with their instance name as a key
     * @throws IllegalAccessException
     */
    public static Map<String, Invokable> getAllCommands() throws IllegalAccessException {
        return Arrays.stream(PredefinedCommandList.class.getDeclaredFields()).collect(Collectors.toMap(Field::getName, e -> {
            try {
                return (Invokable) e.get(null);
            } catch (IllegalAccessException e1) {
                throw new NullPointerException();
            }
        }));
    }
}
