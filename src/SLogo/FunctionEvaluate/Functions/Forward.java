//package SLogo.FunctionEvaluate.Functions;
//
//import SLogo.FunctionEvaluate.Environment;
//import SLogo.FunctionEvaluate.Variables.NumberVariable;
//import SLogo.FunctionEvaluate.Variables.Variable;
//import SLogo.Turtles.Turtle;
//
///**
// * Implements FORWARD/FD command.
// *
// * @author Stone Mathers
// * Created 2/24/17
// */
//
//public class Forward extends TurtleMovement{
//
//	public Forward(Environment env){
//		super(env);
//	}
//
//	public NumberVariable operation(Variable var1){
//		NumberVariable num = (NumberVariable)var1;
//		Turtle turt = this.getEnvironment().getTurtle();
//		turt.move(num.toNumber());
//		return num;
//	}
//
//}
