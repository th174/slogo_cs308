//package SLogo.FunctionEvaluate.Functions;
//
//import SLogo.FunctionEvaluate.Environment;
//import SLogo.FunctionEvaluate.Functions.Invokable.UnexpectedArgumentException;
//import SLogo.FunctionEvaluate.Variables.NumberVariable;
//import SLogo.FunctionEvaluate.Variables.Variable;
//import SLogo.Parse.RecursiveExpression;
//
///**
// * Implements FOR command.
// *
// * @author Stone Mathers
// * Created 2/26/2017
// */
//public class For extends EnvironmentCommand {
//
//	public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
//
//	public For(Environment env) {
//		super(env);
//	}
//
//	@Override
//	public Variable invoke(Environment env, Variable[] args, RecursiveExpression... expr) {
//		if (args.length != EXPECTED_NUMBER_OF_ARGUMENTS) {
//            throw new UnexpectedArgumentException(EXPECTED_NUMBER_OF_ARGUMENTS, args.length);
//        } else {
//            return operation(args[0], args[1], args[2], args[3], expr);
//        }
//	}
//
//	private Variable operation(Variable arg1, Variable arg2, Variable arg3, Variable arg4, RecursiveExpression commands){
//		String varName = arg1.toString();
//		int start = (int)arg2.toNumber();
//		int finish = (int)arg3.toNumber();
//		int increment = (int)arg4.toNumber();
//		Environment env = this.getEnvironment();
//		env.addUserVariable(varName, new NumberVariable(start));
//		Variable finalValue = new NumberVariable(0);
//
//		if(start <= finish && commands.length > 0){
//			for(int i = start; i <= finish; i+=increment){
//				for(RecursiveExpression expr: commands){
//					try{
//						finalValue = expr.eval(env);
//					}catch(Exception e){
//						throw new RuntimeException(e);
//					}
//				}
//				Variable iteration = env.getVariableByName(varName);
//				env.addUserVariable(varName, new NumberVariable(increment).sum(iteration));
//			}
//		}
//
//		return finalValue;
//	}
//
//}
