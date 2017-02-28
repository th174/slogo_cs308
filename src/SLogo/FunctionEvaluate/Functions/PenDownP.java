//package SLogo.FunctionEvaluate.Functions;
//
//import SLogo.FunctionEvaluate.Environment;
//import SLogo.FunctionEvaluate.Variables.BoolVariable;
//import SLogo.Turtles.Turtle;
//
///**
// * Implements PENDOWNP/PENDOWN? command.
// *
// * @author Stone Mathers
// * Created 2/25/17
// */
//public class PenDownP extends TurtleSet {
//
//	public PenDownP(Environment env) {
//		super(env);
//	}
//
//	@Override
//	public BoolVariable operation() {
//		Turtle turt = this.getEnvironment().getTurtle();
//		if(turt.penDown()){
//			return BoolVariable.TRUE;
//		}
//		else{
//			return BoolVariable.FALSE;
//		}
//	}
//
//}
