//package SLogo.FunctionEvaluate.Functions;
//
//import SLogo.FunctionEvaluate.Environment;
//import SLogo.FunctionEvaluate.Variables.NumberVariable;
//import SLogo.FunctionEvaluate.Variables.Variable;
//import SLogo.Turtles.Turtle;
//
///**
// * Implements SETHEADING/SETH command.
// *
// * @author Stone Mathers
// * Created 2/24/17
// */
//
//public class SetHeading extends TurtleMovement {
//
//	public SetHeading(Environment env){
//		super(env);
//	}
//
//	@Override
//	public NumberVariable operation(Variable var1) {
//		NumberVariable num = (NumberVariable)var1;
//		Turtle turt = this.getEnvironment().getTurtle();
//		double degMoved = Math.abs(turt.getHeading() - num.toNumber());
//
//		turt.setHeading(num.toNumber());
//
//		return new NumberVariable(degMoved);
//	}
//
//}