//package SLogo.FunctionEvaluate.Functions;
//
//import SLogo.FunctionEvaluate.Environment;
//import SLogo.FunctionEvaluate.Variables.NumberVariable;
//import SLogo.Turtles.Turtle;
//
///**
// * Implements HEADING command.
// *
// * @author Stone Mathers
// * Created 2/25/17
// */
//public class Heading extends TurtleSet {
//
//	public Heading(Environment env) {
//		super(env);
//	}
//
//	@Override
//	public NumberVariable operation() {
//		Turtle turt = this.getEnvironment().getTurtle();
//		return new NumberVariable(turt.getHeading());
//	}
//
//}