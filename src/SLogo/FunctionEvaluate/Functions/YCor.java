package SLogo.FunctionEvaluate.Functions;

import SLogo.FunctionEvaluate.Environment;
import SLogo.FunctionEvaluate.Variables.NumberVariable;
import SLogo.View.CanvasView;

/**
 * Implements YCOR command.
 * 
 * @author Stone Mathers
 * Created 2/25/17
 */
public class YCor extends TurtleSettings {

	public YCor(Environment env) {
		super(env);
	}

	@Override
	public NumberVariable operation() {
		CanvasView canvas = this.getEnvironment().getCanvas();
		return new NumberVariable(canvas.getSpritePosition()[1]);
	}

}