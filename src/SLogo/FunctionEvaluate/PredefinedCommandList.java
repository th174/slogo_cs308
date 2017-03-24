package SLogo.FunctionEvaluate;

import SLogo.FunctionEvaluate.Functions.*;
import SLogo.FunctionEvaluate.Variables.Variable;
import SLogo.Parse.AtomicList;
import SLogo.Parse.Expression;
import SLogo.Repl;
import SLogo.Turtles.Turtle;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Container class that holds all the pre-defined function implementations.
 * <p>
 * <p>
 * Java doesn't let me have top level functions, so I'm stuck with this.
 * <p>
 * I could make each and every one of these into separate classes, and instantiate each one with reflection, but that leads to way too much repeated code.
 * <p>
 * Not the kind that I can refactor out, but the kind that's just boring Java syntax like imports, class headers, and method headers.
 * <p>
 * I recognize that this is technically a singleton, but there's neither state nor behavior associated with this class. It's more of a library that anything else.
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
            ASKWITH = (repl, env, turtle, expr) -> expr.eval(repl, new EnvironmentImpl(env, Collections.singletonList(turtle.id()))).booleanContext(),
            ASK = (repl, env, turtle, expr) -> expr.getBody().stream().map(e -> Math.round((float) e.eval(repl, env).numericalContext())).collect(Collectors.toList()).contains(turtle.id());
    public static final Loop
            FOR = (repl, env, expr) -> {
        List<Expression> loopParams = expr.getBody();
        String loopVar = loopParams.size() >= 2 ? loopParams.remove(0).toString() : ":" + ResourceBundle.getBundle("resources/variables/variable").getString("repcount");
        Variable start = loopParams.size() >= 2 ? loopParams.remove(0).eval(repl, env) : Variable.newInstance(0);
        Variable end = loopParams.remove(0).eval(repl, env);
        Expression increment = loopParams.size() >= 1 ? loopParams.remove(0) : new AtomicList("1");
        return new Object[]{loopVar, start, end, increment};
    },
            DOTIMES = (repl, env, expr) -> {
                List<Expression> loopParams = expr.getBody();
                String loopVar = loopParams.remove(0).toString();
                Variable end = loopParams.remove(0).eval(repl, env).sum(Variable.newInstance(.00001));
                return new Object[]{loopVar, Variable.newInstance(1), end, new AtomicList("1")};
            },
            REPEAT = (repl, env, expr) -> new Object[]{ResourceBundle.getBundle("resources/variables/variable").getString("repcount"), Variable.newInstance(1), expr.eval(repl, env).sum(Variable.newInstance(.00001)), new AtomicList("1")},
            IF = (repl, env, loopParams) -> new Object[]{"$_", Variable.TRUE, loopParams.eval(repl, env).equalTo(Variable.FALSE), new AtomicList("1")}; //Literally a loop lol
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
                System.exit((int) var.numericalContext());
                return var;
            };
    public static final BinaryIterable
            LESSTHAN = Variable::lessThan,
            GREATERTHAN = Variable::greaterThan,
            EQUAL = Variable::equalTo,
            NOTEQUAL = Variable::notEqualTo,
            WRITE = (var1, var2) -> {
                Files.write(Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + var1.stringContext()), Collections.singletonList(var2.stringContext()), Charset.defaultCharset());
                return var2;
            };
    public static final TurtleUnary
            FORWARD = Turtle::moveForward,
            BACKWARD = Turtle::moveBackward,
            LEFT = Turtle::rotateCCW,
            RIGHT = Turtle::rotateCW,
            SETHEADING = Turtle::setHeading;
    public static final TurtleBinary
            SETPOSITION = Turtle::setXY,
            SETTOWARDS = Turtle::setHeadingTowards;
    public static final TurtleProperty
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
            SETBOUNDSWRAP = (repl, var1) -> repl.getCanvas().setBoundsWrap(var1.booleanContext()),
            SETBACKGROUND = (repl, var1) -> repl.getCanvas().setBackground(var1.numericalContext()),
            SETSHAPE = (repl, var1) -> repl.getCanvas().setShape(var1.numericalContext()),
            SETPENCOLOR = (repl, var1) -> repl.getCanvas().setPenColor(var1.numericalContext()),
            SETPENSIZE = (repl, var1) -> repl.getCanvas().setPenSize(var1.numericalContext()),
            EXECUTE = (repl, var1) -> repl.getParser().parse(repl, repl.getUserEnvironment(), var1.stringContext()),
            CD = (repl, var1) -> {
                String path = var1.stringContext().startsWith(System.getProperty("file.separator")) || var1.stringContext().startsWith("/") ?
                        var1.stringContext() :
                        System.getProperty("user.dir") + System.getProperty("file.separator") + var1.stringContext();
                return System.setProperty("user.dir", Paths.get(path).normalize().toAbsolutePath().toString());
            },
            READFILE = (repl, var1) -> new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + var1.stringContext()))),
            USE = (repl, var1) -> repl.getParser().setLocale(var1.stringContext());
    public static final Property
            GETBOUNDSWRAP = repl -> repl.getCanvas().getBoundsWrap(),
            TURTLES = repl -> repl.getUserEnvironment().getAllTurtles().size(),
            CLEARSCREEN = repl -> {
                repl.getCanvas().clearScreen();
                return repl.getUserEnvironment().clearTurtles();
            },
            GETPENCOLOR = repl -> repl.getCanvas().getPenColor(),
            GETSHAPE = repl -> repl.getCanvas().getShape(),
            GETUSERHISTORY = repl -> "\n" + repl.getHistory().stream().map(Object::toString).collect(Collectors.joining("\n")) + "\n",
            LASTCOMMAND = repl -> repl.lastCommand().toString(),
            PWD = repl -> Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + ".").normalize().toAbsolutePath().toString(),
            LS = repl -> "\n" + String.join("\n", Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + ".").toFile().list()) + "\n";
    public static final IterableInvokable
            IFELSE = new IterableInvokable() {
        @Override
        public int minimumArity() {
            return 3;
        }

        @Override
        public Variable operation(Repl repl, Environment env, Expression... exprs) {
            if (exprs[0].eval(repl, env).booleanContext()) {
                return exprs[1].eval(repl, env);
            } else {
                return exprs[2].eval(repl, env);
            }
        }
    },
            SETPALETTE = new IterableInvokable() {
                @Override
                public int minimumArity() {
                    return 4;
                }

                @Override
                public Variable operation(Repl repl, Environment env, Expression... exprs) {
                    return Variable.newInstance(repl.getCanvas().setPalette(exprs[0].eval(repl, env).numericalContext(), exprs[1].eval(repl, env).numericalContext(), exprs[2].eval(repl, env).numericalContext(), exprs[3].eval(repl, env).numericalContext()));
                }
            },
            TELL = new IterableInvokable() {
                @Override
                public int minimumArity() {
                    return 1;
                }

                @Override
                public Variable operation(Repl repl, Environment env, Expression... exprs) {
                    List<Integer> turtleIDs = exprs[0].getBody().stream().map(e -> Math.round((float) e.eval(repl, env).numericalContext())).collect(Collectors.toList());
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
                public Variable operation(Repl repl, Environment env, Expression... exprs) {
                    env.addUserVariable(exprs[0].toString(), exprs[1].eval(repl, env));
                    return exprs[0].eval(repl, env);
                }
            };

    /**
     * You should never instantiate this class.
     */
    private PredefinedCommandList() {
    }

    static Map<String, Invokable> getAllCommands() {
        return Arrays.stream(PredefinedCommandList.class.getDeclaredFields()).collect(Collectors.toMap(Field::getName, e -> {
            try {
                return (Invokable) e.get(null);
            } catch (IllegalAccessException e1) {
                throw new NullPointerException();
            }
        }));
    }
}
