package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.*;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.AtomicList;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This probably isn't the right way to hold a ton of functions, but I don't know how else you would do it.
 *
 * @author Created by th174 on 2/16/2017.
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
            REPEAT = (repl, env, loopParams) -> {
        String loopVar = loopParams.size() >= 2 ? loopParams.remove(0).toString() : ResourceBundle.getBundle("resources/variables/variable").getString("repcount");
        Variable start = loopParams.size() >= 2 ? loopParams.remove(0).eval(repl, env) : Variable.newInstance(1);
        Variable end = loopParams.remove(0).eval(repl, env);
        Expression increment = loopParams.size() >= 1 ? loopParams.remove(0) : new AtomicList("1");
        return new Object[]{loopVar, start, end, increment};
    },
            DOTIMES = REPEAT,   //There's actually only one loop function, it just behaves differently depending on the loop arguments
            FOR = REPEAT,       //There's actually only one loop function, it just behaves differently depending on the loop arguments
            IF = (repl, env, loopParams) -> new Object[]{"$_", Variable.TRUE, Variable.newInstance(loopParams.remove(0).eval(repl, env).toBoolean()), new AtomicList("1")}; //Literally a loop lol
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
    public static final Setting
            SETBACKGROUND = (repl, var1) -> repl.getCanvas().setBackground(var1.toNumber()),
            SETSHAPE = (repl, var1) -> repl.getCanvas().setShape(var1.toNumber()),
            SETPENCOLOR = (repl, var1) -> repl.getCanvas().setPenColor(var1.toNumber()),
            SETPENSIZE = (repl, var1) -> repl.getCanvas().setPenSize(var1.toNumber());
    public static final Property
            TURTLES = repl -> repl.getEnvironment().getAllTurtles().size(),
            CLEARSCREEN = repl -> {
                repl.getCanvas().clearScreen();
                return repl.getEnvironment().clearTurtles();
            },
            GETPENCOLOR = repl -> repl.getCanvas().getPenColor(),
            GETSHAPE = repl -> repl.getCanvas().getShape();
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
            SETPALETTE = new IterableInvokable() {
                @Override
                public int minimumArity() {
                    return 4;
                }

                @Override
                public Variable operation(Repl repl, Environment env, Expression... vargs) {
                    return Variable.newInstance(repl.getCanvas().setPalette(vargs[0].eval(repl, env).toNumber(), vargs[1].eval(repl, env).toNumber(), vargs[2].eval(repl, env).toNumber(), vargs[3].eval(repl, env).toNumber()));
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
            },
            READFILE = new IterableInvokable() {
                @Override
                public int minimumArity() {
                    return 1;
                }

                @Override
                public Variable operation(Repl repl, Environment env, Expression... vargs) throws Exception {
                    return repl.getParser().parse(env, new String(Files.readAllBytes(Paths.get(vargs[0].eval(repl, env).toContentString())))).eval(repl, env);
                }
            },
            USE = new IterableInvokable() {
                @Override
                public int minimumArity() {
                    return 1;
                }

                @Override
                public Variable operation(Repl repl, Environment env, Expression... vargs) throws Exception {
                    Variable v = vargs[0].eval(repl, env);
                    repl.getParser().setLocale(v.toContentString());
                    return v;
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
