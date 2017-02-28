//package SLogo.FunctionEvaluate.Functions;
//
//import SLogo.FunctionEvaluate.Environment;
//import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
//import SLogo.FunctionEvaluate.Variables.NumberVariable;
//import SLogo.FunctionEvaluate.Variables.Variable;
//import SLogo.Parse.RecursiveExpression;
//
///**
// * Implements DOTIMES command.
// *
// * @author Stone Mathers
// * Created 2/26/2017
// */
//public class DoTimes extends EnvironmentCommand {
//
//	public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
//
//	public DoTimes(Environment env) {
//		super(env);
//	}
//
//	@Override
//	public Variable invoke(Environment env, Variable[] args, RecursiveExpression... expr) {
//		if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
//            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
//        } else {
//            return operation(args[0], args[1], expr);
//        }
//	}
//
//	private Variable operation(Variable arg1, Variable arg2, RecursiveExpression commands){
//		String varName = arg1.toString();
//		int limit = (int)arg2.toNumber();
//		Environment env = this.getEnvironment();
//		env.addUserVariable(varName, new NumberVariable(1));
//		Variable finalValue = new NumberVariable(0);
//
//		if(limit > 0 && commands.length > 0){
//			for(int i = 1; i <= limit; i++){
//				for(RecursiveExpression expr: commands){
//					try{
//						finalValue = expr.eval(env);
//					}catch(Exception e){
//						throw new RuntimeException(e);
//					}
//				}
//				Variable iteration = env.getVariableByName(varName);
//				env.addUserVariable(varName,  new NumberVariable(1).sum(iteration));
//			}
//		}
//
//		return finalValue;
//	}
//
//}
